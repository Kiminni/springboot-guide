package org.springboot.test.data.dto;

import lombok.Getter;
import lombok.Setter;
import org.springboot.test.data.entity.Product;

/**
 * DTO for {@link Product}
 */
@Getter
@Setter
public class ChangeProductNameDto {
    private Long number;
    private String name;
}