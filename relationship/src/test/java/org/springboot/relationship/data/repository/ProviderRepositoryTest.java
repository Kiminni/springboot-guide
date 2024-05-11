package org.springboot.relationship.data.repository;

import org.assertj.core.util.Lists;
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

    @Test
    void cascadeTest() {
        Provider provider = new Provider();
        provider.setName("쿠팡");

        Product prod1 = new Product();
        prod1.setName("펜");
        prod1.setPrice(5000);
        prod1.setStock(500);

        Product prod2 = new Product();
        prod2.setName("가방");
        prod2.setPrice(500);
        prod2.setStock(50);

        Product prod3 = new Product();
        prod3.setName("책");
        prod3.setPrice(3000);
        prod3.setStock(1000);


        prod1.setProvider(provider);
        prod2.setProvider(provider);
        prod3.setProvider(provider);

        provider.getProductList().addAll(Lists.newArrayList(prod1, prod2, prod3));

        providerRepository.save(provider);
    }

}
