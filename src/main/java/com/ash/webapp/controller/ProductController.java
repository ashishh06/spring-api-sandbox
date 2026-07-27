package com.ash.webapp.controller;

import com.ash.webapp.model.Product;
import com.ash.webapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping("/products")
    public List<Product> getProduct(){
        return service.getAllProducts();
    }

    @PostMapping("/addProduct")
    public void addProduct(Product prod){
        service.addProduct(prod);
    }


}
