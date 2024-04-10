package org.springboot.relationship.data.repository;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springboot.relationship.data.entity.Product;
import org.springboot.relationship.data.entity.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProviderRepositoryTest {

    private static final Logger log = LoggerFactory.getLogger(ProviderRepositoryTest.class);
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProviderRepository providerRepository;

    @Test
    void relationTest1() {
        Provider provider = Provider.builder()
                .name("쿠팡")
                .build();

        providerRepository.save(provider);

        Product product = Product.builder()
                .name("스프링 부트 JPA")
                .price(5000)
                .stock(500)
                .provider(provider)
                .build();

        providerRepository.save(provider);

        log.info("savedProvider: {}", productRepository.findById(1L));
        log.info("savedProduct: {}", productRepository.findById(1L));
    }

    @Test
    void relationTest2() {
        Provider provider = Provider.builder()
                .name("asdf")
                .build();

        providerRepository.save(provider);

        Product prod1 = Product.builder()
                .name("펜")
                .price(5000)
                .stock(500)
                .provider(provider)
                .build();

        Product prod2 = Product.builder()
                .name("가방")
                .price(500)
                .stock(50)
                .provider(provider)
                .build();

        Product prod3 = Product.builder()
                .name("책")
                .price(3000)
                .stock(1000)
                .build();

        productRepository.save(prod1);
        productRepository.save(prod2);
        productRepository.save(prod3);

        List<Product> products = providerRepository.findById(provider.getId()).get().getProductList();

        for(Product product : products){
            log.info("product: {}", product);
        }

    }

}
