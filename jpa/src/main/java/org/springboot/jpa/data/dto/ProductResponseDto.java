package org.springboot.jpa.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductResponseDto {
    private Long number;
    private String name;
    private int price;
}
