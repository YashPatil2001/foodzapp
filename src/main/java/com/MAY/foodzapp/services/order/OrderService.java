package com.MAY.foodzapp.services.order;

import com.MAY.foodzapp.entities.Order;
import com.MAY.foodzapp.models.PlaceOrderModel;

import java.util.List;

public interface OrderService {
    Order placeOrder(PlaceOrderModel order);

    List<Order> getAllOrdersByUserId(Long userId);

    List<Order> getAllOrders();
}
