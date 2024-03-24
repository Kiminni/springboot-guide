package org.springboot.jpa.data.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO for {@link org.springboot.jpa.data.entity.Product}
 */
@Getter
@Setter
public class ChangeProductNameDto {
    private Long number;
    private String name;
}