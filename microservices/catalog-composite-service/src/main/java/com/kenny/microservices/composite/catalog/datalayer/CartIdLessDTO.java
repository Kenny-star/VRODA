package com.kenny.microservices.composite.catalog.datalayer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartIdLessDTO {
    private int categoryId;
    private String title;
    private double price;
    private int quantity;
    private String description;
}