package com.jsl.product_service.services;

import com.jsl.product_service.utils.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductService {
    Integer createProduct(ProductRequest productRequest);
    ProductResponse findById(Integer id);
    List<ProductResponse> findAll();
    @Transactional(rollbackFor = ProductPurchaseException.class)
    List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requests);
}
