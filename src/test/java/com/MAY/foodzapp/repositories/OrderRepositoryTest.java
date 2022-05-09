package com.MAY.foodzapp.repositories;

import com.MAY.foodzapp.entities.Order;
import com.MAY.foodzapp.entities.Product;
import com.MAY.foodzapp.entities.Profile;
import com.MAY.foodzapp.utils.UserRoles;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderRepositoryTest {

    // TODO: 09-05-2022 OrderRepositoryTest:SUCCESS 
    @Autowired
    private OrderRepository orderRepository;


    @Test
    public void addOrdersTest(){

        Profile profile = Profile.builder()
                .firstName("Aryya")
                .lastName("Walke")
                .email("aryya@ryk.com")
                .password("12332")
                .role(UserRoles.USER)
                .build();

        Product product = Product.builder()
                .title("testing tile pizza")
                .imageUrl("https://st.depositphotos.com/1003814/5052/i/950/depositphotos_50523105-stock-photo-pizza-with-tomatoes.jpg")
                .price(200L)
                .description("Delicision food ever")
                .build();

        Order order = Order.builder()
                .product(product)
                .profile(profile)
                .orderDate(LocalDateTime.now())
                .shipAddress("CS dept,RYK college,college road,nashik")
                .quantity(2L).build();

        orderRepository.save(order);
    }

    @Test
    public void printAllOrdersTest(){
        List<Order> orderList = orderRepository.findAll();
        System.out.println(orderList);
    }

    @Test
    public void deleteAllOrderTest(){
        orderRepository.deleteAll();
    }
}