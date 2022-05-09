package com.MAY.foodzapp.services.product;

import com.MAY.foodzapp.entities.Product;
import com.MAY.foodzapp.utils.ResultFlags;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService{
    ResultFlags addProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(Long productId);
}
