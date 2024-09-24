package com.jsl.product_service.utils;

import com.jsl.product_service.services.ProductRepository;
import com.jsl.product_service.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.jsl.product_service.utils.ProductMapper.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImplementation implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productRepository.save(toProduct(productRequest)).getId();
    }

    @Override
    public ProductResponse findById(Integer id) {
        return toProductResponse(productRepository.findById(id).orElseThrow());
    }

    @Override
    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream().map(ProductMapper::toProductResponse).toList();
    }

    @Override
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requests) {
        var productIds = requests
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        var storedProducts = productRepository.findAllByIdInOrderById(productIds);
        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exist");
        }
        var sortedRequest = requests
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = sortedRequest.get(i);
            if (product.getAvailableQuantity() < productRequest.quantity()) {
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID:: " + productRequest.productId());
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchasedProducts.add(toProductPurchaseResponse(product, productRequest.quantity()));
        }
        return purchasedProducts;
    }
}
