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

    @PostMapping("/products")
    public void addProduct(@RequestBody Product prod){
        service.addProduct(prod);
    }

    @GetMapping("/product/{prodId}")
    public void getProductById(int prodId){
        service.getProductById(prodId);
    }

    @PutMapping("/updateProduct")
    public void updateProduct(@RequestBody Product prod){
        service.updateProduct(prod);
    }

    @DeleteMapping("/deleteProduct")
    public void deleteProduct(int prodId){
        service.deleteProduct(prodId);
    }


}
