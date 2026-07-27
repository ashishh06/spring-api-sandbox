package com.ash.webapp.service;

import com.ash.webapp.model.Product;
import com.ash.webapp.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<Product> getAllProducts() {

        return repo.findAll();

    }

    public Product getProductById(int id) {
        return repo.findById(id).orElse(null);
    }
    public Product addProduct(Product product){
        return repo.save(product);
    }

    public Product updateProduct(Product product){
        return repo.save(product);
    }

    public void deleteProduct(int prodid) {
        repo.deleteById(prodid);
    }



}
