package org.springboot.advanced_jpa.repository;

import com.querydsl.core.types.Predicate;
import org.junit.jupiter.api.Test;
import org.springboot.advanced_jpa.data.entity.Product;
import org.springboot.advanced_jpa.data.entity.QProduct;
import org.springboot.advanced_jpa.data.repository.QProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class QProductRepositoryTest {

    @Autowired
    QProductRepository qProductRepository;

    @Test
    void querydslTest1() {
        Predicate predicate = QProduct.product.name.containsIgnoreCase("펜")
                .and(QProduct.product.price.between(1000, 2500));

        Optional<Product> foundProd = qProductRepository.findOne(predicate);

        if(foundProd.isPresent()){
            Product prod = foundProd.get();
        }
    }

    @Test
    void querydslTest2() {
        QProduct qProduct = QProduct.product;

        Iterable<Product> productList = qProductRepository.findAll(
                qProduct.name.contains("펜")
                        .and(qProduct.price.between(1000, 2500))
        );

    }


}
