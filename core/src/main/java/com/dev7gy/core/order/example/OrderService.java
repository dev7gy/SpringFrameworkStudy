package com.dev7gy.core.order.example;

import com.dev7gy.core.order.Order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
