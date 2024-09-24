package com.jsl.order_service.product;

import java.util.List;

public interface ProductClient {
    List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requests);
}
