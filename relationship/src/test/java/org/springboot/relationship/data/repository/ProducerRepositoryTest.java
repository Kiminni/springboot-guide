package org.springboot.relationship.data.repository;


import org.junit.jupiter.api.Test;
import org.springboot.relationship.data.entity.Producer;
import org.springboot.relationship.data.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
class ProducerRepositoryTest {


    @Autowired
    ProducerRepository producerRepository;

    @Autowired
    ProductRepository productRepository;



    @Test
    @Transactional
    void relationshipTest() {
        Product product1 = saveProduct("스프링 부트 JPA", 5000, 500);
        Product product2 = saveProduct("펜", 200, 100);
        Product product3 = saveProduct("가방", 10000, 1000);

        Producer producer1 = saveProducer("쿠팡");
        Producer producer2 = saveProducer("11번가");

        producer1.addProduct(product1);
        producer1.addProduct(product2);

        producer2.addProduct(product2);
        producer2.addProduct(product3);

        producerRepository.saveAll(List.of(producer1, producer2));

        System.out.println("producer1: " + producerRepository.findById(producer1.getId()).get());

    }

    private Product saveProduct(String name, int price, int stock) {
        Product product = Product.builder()
                .name(name)
                .price(price)
                .stock(stock)
                .build();
        return productRepository.save(product);
    }

    private Producer saveProducer(String name) {
        Producer producer = new Producer();
        producer.setName(name);

        return producerRepository.save(producer);
    }
}
