package com.kupercy.service;

import com.kupercy.dto.CreateOrderRequest;
import com.kupercy.model.Order;

public interface OrderService {

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
