package org.springboot.advanced_jpa.data.dto;

import lombok.Getter;
import lombok.Setter;
import org.springboot.advanced_jpa.data.entity.Product;

/**
 * DTO for {@link Product}
 */
@Getter
@Setter
public class ChangeProductNameDto {
    private Long number;
    private String name;
}