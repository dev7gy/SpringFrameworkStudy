package com.dev7gy.core.order.service;

import com.dev7gy.core.order.Order;
import org.springframework.stereotype.Component;


public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
