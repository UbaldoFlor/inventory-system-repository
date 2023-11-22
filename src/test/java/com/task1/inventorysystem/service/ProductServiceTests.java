package com.task1.inventorysystem.service;

import com.task1.inventorysystem.entity.Product;
import com.task1.inventorysystem.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServiceTests {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");
        product1.setDescription("Description for product 1");
        product1.setPrice(10.0);
        product1.setQuantity(5);

        product2 = new Product();
        product2.setId(2L);
        product2.setName("Product 2");
        product2.setDescription("Description for product 2");
        product2.setPrice(20.0);
        product2.setQuantity(10);
    }

    @Test
    void testGetAllProducts() {
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.getAllProducts();

        assertThat(products).isNotNull();
        assertThat(products).hasSize(2);
        assertThat(products.get(0).getName()).isEqualTo("Product 1");
        assertThat(products.get(1).getName()).isEqualTo("Product 2");
    }

    @Test
    void testGetProductById() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));
        when(productRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<Product> foundProduct = Optional.ofNullable(productService.getProductById(1L));
        assertThat(foundProduct).isPresent().isEqualTo(Optional.of(product1));

        Optional<Product> notFoundProduct = Optional.ofNullable(productService.getProductById(2L));
        assertThat(notFoundProduct).isEmpty();
    }
}