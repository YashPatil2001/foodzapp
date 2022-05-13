package com.MAY.foodzapp.controllers;


import com.MAY.foodzapp.entities.Product;
import com.MAY.foodzapp.services.product.ProductService;
import com.MAY.foodzapp.utils.ResultFlags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResultFlags addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @ResponseBody
    @GetMapping("/allProducts")
    public List<Product> getAllProducts(){
        return  productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long productId){
        return productService.getProductById(productId);
    }
}
