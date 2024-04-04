package org.springboot.advanced_jpa.data.dto;

import lombok.*;

@Builder
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String name;
    private int price;
    private int stock;
}
