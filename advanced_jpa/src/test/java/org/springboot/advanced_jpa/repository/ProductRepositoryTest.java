package org.springboot.advanced_jpa.repository;

import com.querydsl.jpa.impl.JPAQuery;
import org.junit.jupiter.api.Test;
import org.springboot.advanced_jpa.data.entity.Product;
import org.springboot.advanced_jpa.data.entity.QProduct;
import org.springboot.advanced_jpa.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @PersistenceContext
    EntityManager entityManager;

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

    @Test
    void queryDslTest() {
        JPAQuery<Product> query = new JPAQuery<>(entityManager);
        QProduct qProduct = QProduct.product;

        List<Product> productList = query
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (Product product : productList) {
            System.out.println("---------");
            System.out.println(product.getNumber());
            System.out.println(product.getName());
            System.out.println(product.getPrice());
            System.out.println(product.getStock());
            System.out.println("---------");
        }




    }
}