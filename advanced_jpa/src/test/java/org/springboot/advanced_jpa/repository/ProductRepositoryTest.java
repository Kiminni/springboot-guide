package org.springboot.advanced_jpa.repository;

import org.junit.jupiter.api.Test;
import org.springboot.advanced_jpa.data.entity.Product;
import org.springboot.advanced_jpa.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

//    public ProductRepositoryTest(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }

    @Test
    void sortingAndPagingTest() {

        Product product1 = Product.builder()
                .name("펜")
                .price(1000)
                .stock(100)
                .build();

        Product product2 = Product.builder()
                .name("펜")
                .price(5000)
                .stock(10)
                .build();

        Product product3 = Product.builder()
                .name("펜")
                .price(500)
                .stock(50)
                .build();

        Product savedProduct1 = productRepository.save(product1);
        Product savedProduct2 = productRepository.save(product2);
        Product savedProduct3 = productRepository.save(product3);

        System.out.println(productRepository.findByName("펜", getsort()));
        // System.out.println(productRepository.findByName("펜", Sort.by(Sort.Order.asc("price"), Sort.Order.asc("stock"))));


    }
    private Sort getsort() {
        return Sort.by(Sort.Order.asc("price"), Sort.Order.desc("stock"));
    }
}