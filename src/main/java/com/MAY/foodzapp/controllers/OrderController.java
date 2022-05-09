package com.MAY.foodzapp.controllers;


import com.MAY.foodzapp.entities.Order;
import com.MAY.foodzapp.models.PlaceOrderModel;
import com.MAY.foodzapp.services.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public Order placeOrder(@RequestBody PlaceOrderModel order){
        return orderService.placeOrder(order);
    }

    @GetMapping("/user/{user_id}")
    public List<Order> getOrderByUserId(@PathVariable("user_id") Long userId){
        return orderService.getAllOrdersByUserId(userId);
    }

    @GetMapping
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }
}
