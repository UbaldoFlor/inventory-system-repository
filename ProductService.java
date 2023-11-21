package com.task1.inventorysystem.service;

import com.task1.inventorysystem.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    void saveProduct(Product product);
    void deleteProduct(Long id);
    Product getProductById(Long id);
    void updateProduct(Long id, Product product);
}