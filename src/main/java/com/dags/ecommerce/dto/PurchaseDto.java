package com.dags.ecommerce.dto;

import com.dags.ecommerce.entity.Address;
import com.dags.ecommerce.entity.Customer;
import com.dags.ecommerce.entity.Order;
import com.dags.ecommerce.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseDto {
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private List<OrderItem> orderItems;
}
