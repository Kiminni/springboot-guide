package org.springboot.test.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springboot.test.data.dto.ProductDto;
import org.springboot.test.data.dto.ProductResponseDto;
import org.springboot.test.data.entity.Product;
import org.springboot.test.data.repository.ProductRepository;
import org.springboot.test.service.ProductServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

//@ExtendWith(SpringExtension.class)
//@Import({ProductServiceImpl.class})
class ProductServiceTest {

//    @MockBean
//    ProductRepository productRepository;
    private final ProductRepository productRepository = Mockito.mock(ProductRepository.class);
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUpTest() {
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void getProductTest() {
        Product givenProduct = new Product();
        givenProduct.setNumber(123L);
        givenProduct.setName("pen");
        givenProduct.setPrice(5000);
        givenProduct.setStock(100);

        Mockito.when(productRepository.findById(123L))
                .thenReturn(Optional.of(givenProduct));

        ProductResponseDto productResponseDto = productService.getProduct(123L);

        assertEquals(productResponseDto.getNumber(), givenProduct.getNumber());
        assertEquals(productResponseDto.getName(), givenProduct.getName());
        assertEquals(productResponseDto.getStock(), givenProduct.getStock());
        assertEquals(productResponseDto.getPrice(), givenProduct.getPrice());

        verify(productRepository).findById(123L);
    }

    @Test
    void saveProductTest() {
        Mockito.when(productRepository.save(any(Product.class)))
                .then(returnsFirstArg());

        ProductResponseDto productResponseDto = productService.saveProduct(
                new ProductDto("펜", 1000, 1234));

        assertEquals("펜",productResponseDto.getName());
        assertEquals(1000, productResponseDto.getPrice());
        assertEquals(1234, productResponseDto.getStock());

        verify(productRepository).save(any());
    }
}
