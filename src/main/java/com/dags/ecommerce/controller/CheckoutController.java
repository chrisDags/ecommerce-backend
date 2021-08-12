package com.dags.ecommerce.controller;

import com.dags.ecommerce.dto.PurchaseDto;
import com.dags.ecommerce.dto.PurchaseResponseDto;
import com.dags.ecommerce.service.CheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkout")
@RequiredArgsConstructor
public class CheckoutController {
    private final CheckoutService checkoutService;

    @PostMapping("/purchase")
    public ResponseEntity<PurchaseResponseDto> placeOrder(@RequestBody PurchaseDto purchaseDto){
        PurchaseResponseDto purchaseResponseDto = checkoutService.placeOrder(purchaseDto);
        return new ResponseEntity<>(purchaseResponseDto, HttpStatus.CREATED);
    }
}
