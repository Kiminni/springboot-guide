package org.springboot.jpa.service;

import org.springboot.jpa.data.dao.impl.ProductDAO;
import org.springboot.jpa.data.dto.ProductDto;
import org.springboot.jpa.data.dto.ProductResponseDto;
import org.springboot.jpa.data.entity.Product;
import org.springboot.jpa.service.impl.ProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    public ProductServiceImpl(ProductDAO productDAO){
        this.productDAO = productDAO;
    }

    @Override
    public ProductResponseDto getProduct(Long number) {
        Product product = productDAO.selectProduct(number);


        return ProductResponseDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .number(product.getNumber())
                .build();
    }

    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {
        new Product();
        Product product = Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .stock(productDto.getStock())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Product savedProduct = productDAO.insertProduct(product);

        return ProductResponseDto.builder()
                .name(savedProduct.getName())
                .price(savedProduct.getPrice())
                .number(savedProduct.getNumber())
                .build();

    }
    @Override
    public ProductResponseDto changeProductName(Long number, String name) throws Exception{
        Product changedProduct = productDAO.updateProductName(number, name);

        return ProductResponseDto.builder()
                .name(changedProduct.getName())
                .price(changedProduct.getPrice())
                .number(changedProduct.getNumber())
                .build();
    }

    @Override
    public void deleteProduct(Long number) throws Exception {

    }
}
