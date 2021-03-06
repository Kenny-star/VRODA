package com.kenny.microservices.core.product.datalayer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductIdLessDTO {
    private int categoryId;
    private String title;
    private double price;
    private int quantity;
    private String description;
    private String image;
}