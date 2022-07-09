package com.kupercy.service;

import com.kupercy.dto.CreateOrderRequest;
import com.kupercy.dto.OrderQueryParams;
import com.kupercy.model.Order;

import java.util.List;

public interface OrderService {

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);
}
