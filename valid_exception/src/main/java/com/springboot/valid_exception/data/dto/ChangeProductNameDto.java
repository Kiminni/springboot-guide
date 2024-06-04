package com.springboot.valid_exception.data.dto;

import com.springboot.valid_exception.data.entity.Product;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for {@link Product}
 */
@Getter
@Setter
public class ChangeProductNameDto {
    private Long number;
    private String name;
}