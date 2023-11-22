package com.task1.inventorysystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task1.inventorysystem.entity.Product;
import com.task1.inventorysystem.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(ProductController.class)
class ProductControllerTests {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

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

        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetAllProducts() throws Exception {
        when(productService.getAllProducts()).thenReturn(Arrays.asList(product1, product2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("Product 1"))
                .andExpect(jsonPath("$[1].name").value("Product 2"))
                .andDo(print());
    }

    @Test
    void testGetProductById() throws Exception {
        doReturn(Optional.of(product1)).when(productService.getProductById(1L));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Product 1"))
                .andDo(print());
    }

    @Test
    void testCreateNewProduct() throws Exception {
        Product newProduct = new Product();
        newProduct.setName("New Product");
        newProduct.setDescription("Description for new product");
        newProduct.setPrice(5.0);
        newProduct.setQuantity(8);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newProduct)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void testUpdateProduct() throws Exception {
        doReturn(Optional.of(product1)).when(productService.getProductById(1L));

        Product updatedProduct = new Product();
        updatedProduct.setId(1L);
        updatedProduct.setName("Updated Product");
        updatedProduct.setDescription("Description for updated product");
        updatedProduct.setPrice(15.0);
        updatedProduct.setQuantity(10);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedProduct)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Product"))
                .andDo(print());
    }

    @Test
    void testDeleteProduct() throws Exception {
        doReturn(Optional.of(product1)).when(productService.getProductById(1L));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/products/1"))
                .andExpect(status().isOk())
                .andReturn();

        String responseString = mvcResult.getResponse().getContentAsString();
        assertThat(responseString).isEqualTo("Product with ID 1 deleted successfully");
    }
}