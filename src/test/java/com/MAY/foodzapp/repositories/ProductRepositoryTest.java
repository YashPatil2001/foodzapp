package com.MAY.foodzapp.repositories;

import com.MAY.foodzapp.entities.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
class ProductRepositoryTest {

    // TODO: 09-05-2022 ProductRepository Test : SUCCESS
    @Autowired
    private ProductRepository productRepository;


    @Test
    public void addProductsTest(){
        Product product = Product.builder()
                .title("Pizza 1")
                .description("tasty pizzaaa")
                .price(200L)
                .imageUrl("https://st.depositphotos.com/1003814/5052/i/950/depositphotos_50523105-stock-photo-pizza-with-tomatoes.jpg")
                .build();
        productRepository.save(product);

    }

    @Test
    public void printAllProductTest(){
        List<Product> productList = productRepository.findAll();
        System.out.println(productList);
    }
}