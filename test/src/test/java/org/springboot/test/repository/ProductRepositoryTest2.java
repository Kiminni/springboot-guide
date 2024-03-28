package org.springboot.test.repository;

import org.junit.jupiter.api.Test;
import org.springboot.test.data.entity.Product;
import org.springboot.test.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class ProductRepositoryTest2 {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void basicCRUDTest() {
        // Create

        // given
        Product givenProduct = Product.builder()
                .name("노트")
                .price(1000)
                .stock(500)
                .build();
        // when
        Product savedProduct = productRepository.save(givenProduct);

        // then
        assertThat(savedProduct.getNumber()).isEqualTo(givenProduct.getNumber());
        assertThat(savedProduct.getName()).isEqualTo(givenProduct.getName());
        assertThat(savedProduct.getPrice()).isEqualTo(givenProduct.getPrice());
        assertThat(savedProduct.getStock()).isEqualTo(givenProduct.getStock());

        // Read
        // when
        Product selectedProduct = productRepository.findById(savedProduct.getNumber())
                .orElseThrow(RuntimeException::new);

        // then
        assertThat(selectedProduct.getNumber()).isEqualTo(savedProduct.getNumber());
        assertThat(selectedProduct.getName()).isEqualTo(savedProduct.getName());
        assertThat(selectedProduct.getPrice()).isEqualTo(savedProduct.getPrice());
        assertThat(selectedProduct.getStock()).isEqualTo(savedProduct.getStock());

        // update
        Product foundProduct = productRepository.findById(selectedProduct.getNumber())
                .orElseThrow(RuntimeException::new);

        foundProduct.setName("장난감");

        Product updatedProduct = productRepository.save(foundProduct);

        // then
        assertEquals(updatedProduct.getName(), "장난감");

        // delete
        // when
        productRepository.delete(updatedProduct);

        //then
        assertFalse(productRepository.findById(selectedProduct.getNumber()).isPresent());

    }
}
