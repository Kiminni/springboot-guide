package org.springboot.jpa.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductDto {
    private String name;
    private int price;
    private int stock;

}
