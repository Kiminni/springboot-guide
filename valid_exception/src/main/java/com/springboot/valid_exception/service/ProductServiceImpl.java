package com.springboot.valid_exception.service;


import com.springboot.valid_exception.data.dto.ProductDto;
import com.springboot.valid_exception.data.dto.ProductResponseDto;
import com.springboot.valid_exception.data.entity.Product;
import com.springboot.valid_exception.data.repository.ProductRepository;
import com.springboot.valid_exception.service.impl.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    private  final ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto getProduct(Long number) {
        LOGGER.info("[getProduct] input number: {}", number);
        Product product = productRepository.findById(number).get();

        LOGGER.info("[getProduct] product number : {}, name : {}", product.getNumber(), product.getName());

        return ProductResponseDto.builder()
                .number(product.getNumber())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }

    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {
        LOGGER.info("[saveProduct] input productDto: {}", productDto);
        Product product = Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .stock(productDto.getStock())
                .build();
        productRepository.save(product);

        return ProductResponseDto.builder()
                .number(product.getNumber())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();

    }

    public ProductResponseDto changeProductName(Long number, String name) {
        Product foundProduct = productRepository.findById(number).get();
        foundProduct.setName(name);
        Product changeProduct = productRepository.save(foundProduct);

        return ProductResponseDto.builder()
                .number(changeProduct.getNumber())
                .name(changeProduct.getName())
                .price(changeProduct.getPrice())
                .build();
    }

    @Override
    public void deleteProduct(Long number) {
        productRepository.deleteById(number);
    }
}
