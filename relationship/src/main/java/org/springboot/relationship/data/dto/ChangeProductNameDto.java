package org.springboot.relationship.data.dto;

import lombok.Getter;
import lombok.Setter;
import org.springboot.relationship.data.entity.Product;

/**
 * DTO for {@link Product}
 */
@Getter
@Setter
public class ChangeProductNameDto {
    private Long number;
    private String name;
}