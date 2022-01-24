package com.kenny.microservices.composite.catalog.businesslayer;

import com.kenny.microservices.composite.catalog.datalayer.CartDTO;
import com.kenny.microservices.composite.catalog.datalayer.CartIdLessDTO;

public interface CartService {
    public CartDTO addToCart(CartIdLessDTO product);
}
