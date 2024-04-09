package org.springboot.advanced_jpa.repository.support;

import org.junit.jupiter.api.Test;
import org.springboot.advanced_jpa.data.entity.Product;
import org.springboot.advanced_jpa.data.repository.support.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductRepositoryTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    ProductRepository productRepository;



    @Test
    void findByNameTest() {
        Product prod = Product.builder()
                .name("펜")
                .price(1000)
                .stock(100)
                .build();
        productRepository.save(prod);
        List<Product> productList = productRepository.findByName("펜");

        for(Product product : productList){
            System.out.println(product);
        }
    }
}
