package com.springboot.valid_exception.controller;


import com.springboot.valid_exception.data.dto.ChangeProductNameDto;
import com.springboot.valid_exception.data.dto.ProductDto;
import com.springboot.valid_exception.data.dto.ProductResponseDto;
import com.springboot.valid_exception.service.impl.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;


    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<ProductResponseDto> getProduct(Long number){
        ProductResponseDto productResponseDto = productService.getProduct(number);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @PostMapping()
    public ResponseEntity<ProductResponseDto> saveProduct(@RequestBody ProductDto productDto){
        ProductResponseDto productResponseDto = productService.saveProduct(productDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(productResponseDto);
    }

    @PutMapping()
    public ResponseEntity<ProductResponseDto> changeProductName(@RequestBody ChangeProductNameDto changeProductNameDto) throws Exception {
        ProductResponseDto productResponseDto = productService.changeProductName(
                changeProductNameDto.getNumber(),
                changeProductNameDto.getName());

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteProduct(Long number) throws Exception {
        productService.deleteProduct(number);

        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }

}
