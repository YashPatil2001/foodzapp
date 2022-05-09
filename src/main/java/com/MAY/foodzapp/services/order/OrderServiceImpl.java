package com.MAY.foodzapp.services.order;


import com.MAY.foodzapp.entities.Order;
import com.MAY.foodzapp.entities.Product;
import com.MAY.foodzapp.entities.Profile;
import com.MAY.foodzapp.models.PlaceOrderModel;
import com.MAY.foodzapp.repositories.OrderRepository;
import com.MAY.foodzapp.repositories.ProductRepository;
import com.MAY.foodzapp.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order placeOrder(PlaceOrderModel order) {


        Profile profile = profileRepository.getById(order.getUserId());
        Product product = productRepository.getById(order.getProductId());

        Order orderDB = Order.builder()
                .shipAddress(order.getShipAddress())
                .quantity(order.getQuantity())
                .orderDate(LocalDateTime.now())
//                .product(product)       // TODO: 09-05-2022 :exception while getting product
                .profile(profile)
                .totalPrice(order.getTotalPrice())
                .build();

        order.setOrderDate(LocalDateTime.now());

        Order placedOrder = orderRepository.save(orderDB);
        return placedOrder;
    }

    @Override
    public List<Order> getAllOrdersByUserId(Long userId) {
        // TODO: 09-05-2022 : have to write custom query for it
//        return orderRepository;
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
