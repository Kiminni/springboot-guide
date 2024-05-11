package org.springboot.relationship.data.repository;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springboot.relationship.data.entity.Category;
import org.springboot.relationship.data.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CategoryRepositoryTest {

    private static final Logger log = LoggerFactory.getLogger(CategoryRepositoryTest.class);
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void relationTest() {
        Product prod = Product.builder()
                .name("펜")
                .price(200)
                .stock(100)
                .build();

        productRepository.save(prod);

        Category category = new Category();
        category.setCode("C1");
        category.setName("문구");
        category.getProducts().add(prod);

        categoryRepository.save(category);

        //테스트 코드
        List<Product> prods = categoryRepository.findById(1L).get().getProducts();

        for(Product p : prods) {
            log.info("product: {}", p);
        }
    }
}
