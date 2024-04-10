package org.springboot.relationship.data.repository;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springboot.relationship.data.entity.Product;
import org.springboot.relationship.data.entity.ProductDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductDetailRepositoryTest {

    private static final Logger log = LoggerFactory.getLogger(ProductDetailRepositoryTest.class);

    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    ProductRepository productRepository;



    @Test
    void saveAndReadTest1() {
        Product prod = Product.builder()
                .name("스프링 부트 JPA")
                .price(5000)
                .stock(500)
                .build();

        productRepository.save(prod);

        ProductDetail productDetail = ProductDetail.builder()
                .product(prod)
                .description("스프링 부트 JPA 책입니다.")
                .build();

        productDetailRepository.save(productDetail);

        log.info("savedProduct: {}", productDetailRepository.findById(productDetail.getId()).get());
        log.info("savedProductDetail: {}", productRepository.findById(productDetail.getId()).get());

    }
}
