package com.example.sales_system.service;

import com.example.sales_system.entity.Product;
import com.example.sales_system.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        Product p1 = new Product();
        p1.setId(1L);
        p1.setName("Product 1");

        Product p2 = new Product();
        p2.setId(2L);
        p2.setName("Product 2");

        when(productRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Product> products = productService.getAllProducts();
        assertEquals(2, products.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        product.setName("New Product");

        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> {
            Product p = invocation.getArgument(0);
            p.setId(1L);
            return p;
        });

        Product created = productService.createProduct(product);

        assertNotNull(created.getCreationDate());
        assertEquals("New Product", created.getName());
        assertEquals(1L, created.getId());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void testUpdateProduct_whenProductExists() {
        Product existingProduct = new Product();
        existingProduct.setId(1L);
        existingProduct.setName("Old Name");
        existingProduct.setDescription("Old Desc");
        existingProduct.setCategory("Old Category");

        Product updatedProduct = new Product();
        updatedProduct.setName("New Name");
        updatedProduct.setDescription("New Desc");
        updatedProduct.setCategory("New Category");

        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(existingProduct);

        Product result = productService.updateProduct(1L, updatedProduct);

        assertEquals("New Name", result.getName());
        assertEquals("New Desc", result.getDescription());
        assertEquals("New Category", result.getCategory());
        verify(productRepository).findById(1L);
        verify(productRepository).save(existingProduct);
    }

    @Test
    void testUpdateProduct_whenProductNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        Product updatedProduct = new Product();
        updatedProduct.setName("New Name");

        Exception exception = assertThrows(RuntimeException.class, () -> {
            productService.updateProduct(1L, updatedProduct);
        });

        assertEquals("Product not found", exception.getMessage());
        verify(productRepository).findById(1L);
    }
}
