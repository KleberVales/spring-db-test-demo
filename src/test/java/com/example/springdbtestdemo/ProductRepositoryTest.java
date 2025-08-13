package com.example.springdbtestdemo;

import com.example.springdbtestdemo.entity.Product;
import com.example.springdbtestdemo.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Transactional
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldLoadInitialDataFromSql() {
        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(2);
    }

    @Test
    @Rollback
    void shouldInsertNewProductAndRollback() {
        Product p = new Product();
        p.setName("Tablet");
        p.setPrice(1500);
        productRepository.save(p);

        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(3);
    }

    @Test
    void shouldVerifyRollbackIsolation() {
        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(2); // Garante que rollback do teste anterior funcionou
    }
}
