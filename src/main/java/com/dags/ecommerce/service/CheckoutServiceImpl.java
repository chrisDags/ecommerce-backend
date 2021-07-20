package com.dags.ecommerce.service;

import com.dags.ecommerce.dto.PurchaseDto;
import com.dags.ecommerce.dto.PurchaseResponseDto;
import com.dags.ecommerce.entity.Customer;
import com.dags.ecommerce.entity.Order;
import com.dags.ecommerce.entity.OrderItem;
import com.dags.ecommerce.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {

    private final CustomerRepository customerRepository;

    /*
        Saves Order and Customer then returns
        a PurchaseResponseDto containing the order tracking number
     */
    @Override
    @Transactional
    public PurchaseResponseDto placeOrder(PurchaseDto purchaseDto) {

        Order order = purchaseDto.getOrder();

        String orderTrackingNumber = generateOrderTrackingNumber(order);
        order.setOrderTrackingNumber(orderTrackingNumber);

        // add order items
        List<OrderItem> orderItems = purchaseDto.getOrderItems();
        orderItems.forEach(order::addOrderItem);

        order.setBillingAddress(purchaseDto.getBillingAddress());
        order.setShippingAddress(purchaseDto.getShippingAddress());

        // add Order to Customer
        Customer customer = purchaseDto.getCustomer();
        customer.addOrder(order);

        customerRepository.save(customer);

        return new PurchaseResponseDto(orderTrackingNumber);
    }

    //Generate UUID
    private String generateOrderTrackingNumber(Order order) {
       return UUID.randomUUID().toString();
    }
}
