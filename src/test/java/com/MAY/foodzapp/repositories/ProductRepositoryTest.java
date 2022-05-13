package com.MAY.foodzapp.repositories;

import com.MAY.foodzapp.entities.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
class ProductRepositoryTest {

    // TODO: 09-05-2022 ProductRepository Test : SUCCESS
    @Autowired
    private ProductRepository productRepository;


    @Test
    public void addProductsTest(){
        Product product1 = Product.builder()
                .title("Neapolitan Pizza")
                .description("This delicious pie dates all the way back to 18th century in Naples, Italy. ")
                .price(200L)
                .imageUrl("https://st.depositphotos.com/1003814/5052/i/950/depositphotos_50523105-stock-photo-pizza-with-tomatoes.jpg")
                .build();
        Product product2 = Product.builder()
                .title("Chicago Pizza")
                .description("Chicago pizza, also commonly referred to as deep-dish pizza")
                .price(300L)
                .imageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQemRdqv0dvKtgmcKMbixSY32CsUXrrXKQYRw&usqp=CAU")
                .build();

        Product product3 = Product.builder()
                .title("New York-Style Pizza")
                .description("New York-style pizza usually features tomato sauce and mozzarella cheese.")
                .price(400L)
                .imageUrl("https://st.depositphotos.com/1003814/5052/i/950/depositphotos_50523105-stock-photo-pizza-with-tomatoes.jpg")
                .build();


        Product product4 = Product.builder()
                .title("Sicilian Pizza")
                .description("Sicilian pizzas are often topped with bits of tomato, onion, anchovies, and herbs.")
                .price(250L)
                .imageUrl("https://cdnimg.webstaurantstore.com/uploads/buying_guide/2014/11/pizzatypes-margherita-.jpg")
                .build();


        Product product5 = Product.builder()
                .title("Greek Pizza")
                .description("Greek pizza is usually heavier on the sauce than the cheese.")
                .price(199L)
                .imageUrl("https://cdnimg.webstaurantstore.com/uploads/buying_guide/2014/11/pizzatypes-deepdish.jpg")
                .build();

        Product product6 = Product.builder()
                .title("California Pizza")
                .description("our choice to use either thin or thick crust will determine how you will bake your pizza")
                .price(199L)
                .imageUrl("https://cdnimg.webstaurantstore.com/uploads/blog/2016/8/rectangle.jpg")
                .build();

        Product product7 = Product.builder()
                .title("Detroit Pizza")
                .description("Detroit pizza traditionally features pepperoni, brick cheese")
                .price(399L)
                .imageUrl("https://cdnimg.webstaurantstore.com/uploads/blog/2016/8/rectangle.jpg")
                .build();

        Product product8 = Product.builder()
                .title("St. Louis Pizza")
                .description("St. Louis pizza features Provel cheese and a sweeter tomato sauce with a hefty dosage of oregano.")
                .price(499L)
                .imageUrl("https://cdnimg.webstaurantstore.com/uploads/blog/2016/8/onions.jpg")
                .build();
        Product[] products = new Product[]{
                product1,
                product2,
                product3,
                product4,
                product5,
                product6,
                product7,
                product8
        };

        productRepository.saveAll(Arrays.asList(products));
//        productRepository.save(product1);

    }

    @Test
    public void printAllProductTest(){
        List<Product> productList = productRepository.findAll();
        System.out.println(productList);
    }

    @Test
    public void deleteProductTest(){
        List<Long> ids = new ArrayList<>();

//        for(int i=10;i<=35;i++){
//            if(i!=18)
//            ids.add((long) i);
//        }
        productRepository.deleteAll();
//        productRepository.deleteById(27L);
    }
}