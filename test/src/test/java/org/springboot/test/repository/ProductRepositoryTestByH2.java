package org.springboot.test.repository;

import org.junit.jupiter.api.Test;
import org.springboot.test.data.entity.Product;
import org.springboot.test.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
class ProductRepositoryTestByH2 {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveTest(){
        // given
        Product product = new Product();
        product.setName("펜");
        product.setPrice(1000);
        product.setStock(1234);


        //when
        Product savedProduct = productRepository.save(product);

        //then
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getPrice(), savedProduct.getPrice());
        assertEquals(product.getStock(), savedProduct.getStock());
    }

    @Test
    void selectTest() {
        // given
        Product product = new Product();
        product.setName("펜");
        product.setPrice(1000);
        product.setStock(1234);

        // when
        Product savedProduct = productRepository.save(product);
        Product selectedProduct = productRepository.findById(savedProduct.getNumber()).orElse(null);

        // then
        assertEquals(product.getName(), selectedProduct.getName());
        assertEquals(product.getPrice(), selectedProduct.getPrice());
        assertEquals(product.getStock(), selectedProduct.getStock());
    }


}
