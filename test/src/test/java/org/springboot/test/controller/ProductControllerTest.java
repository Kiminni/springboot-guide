package org.springboot.test.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springboot.test.data.dto.ProductDto;
import org.springboot.test.data.dto.ProductResponseDto;
import org.springboot.test.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductServiceImpl productService;

    @Test
    @DisplayName("MockMvc를 이용한 Product 데이터 가져오기 테스트")
    void getProductTest() throws Exception{
        // given
        given(productService.getProduct(123L)).willReturn(
                new ProductResponseDto(123L, "pen", 5000, 100));

        String productId = "123";

        // then

        mockMvc.perform(get("/api/v1/product?number=" + productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.stock").exists())
                .andDo(print());
        verify(productService).getProduct(123L);
    }

    @Test
    @DisplayName("Product 데이터 생성 테스트")
    void createProductTest() throws Exception{
        // Mock 객체에서 메소드 실행 -> 실제 return 줄 수 없음

        // given
        given(productService.saveProduct(new ProductDto("pen", 5000, 100)))
                .willReturn(new ProductResponseDto(12315L, "pen", 5000, 500));

        ProductDto productDto = ProductDto.builder()
                .name("pen")
                .price(5000)
                .stock(100)
                .build();
        Gson gson = new Gson();
        String content = gson.toJson(productDto);

        mockMvc.perform(
                    post("/api/v1/product")
                        .content(content)
                        .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.number").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.stock").exists())
                .andDo(print());

        verify(productService).saveProduct(new ProductDto("pen", 5000, 100));

    }
}
