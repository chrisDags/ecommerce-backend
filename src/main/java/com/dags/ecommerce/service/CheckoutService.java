package com.dags.ecommerce.service;

import com.dags.ecommerce.dto.PurchaseDto;
import com.dags.ecommerce.dto.PurchaseResponseDto;

public interface CheckoutService {
    PurchaseResponseDto placeOrder(PurchaseDto purchaseDto);
}
