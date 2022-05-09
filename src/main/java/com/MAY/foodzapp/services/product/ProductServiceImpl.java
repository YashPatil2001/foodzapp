package com.MAY.foodzapp.services.product;

import com.MAY.foodzapp.entities.Product;
import com.MAY.foodzapp.repositories.ProductRepository;
import com.MAY.foodzapp.repositories.ProfileRepository;
import com.MAY.foodzapp.utils.ResultFlags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{


    @Autowired
    private ProductRepository productRepository;
    @Override
    public ResultFlags addProduct(Product product) {
        // TODO: 09-05-2022 validation pending
        productRepository.save(product);

        return ResultFlags.SUCCESS;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long productId) {
        // TODO: 09-05-2022 pending : handle the case whene there should be product 
        return productRepository.findById(productId).get();
    }
}
