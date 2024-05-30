package com.ra.demo9.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDetailDTO {
    private Long productDetailId;

    private Long orderId;

    private Long productId;

    private String color;

    private String size;

    private Integer quantity;
}
