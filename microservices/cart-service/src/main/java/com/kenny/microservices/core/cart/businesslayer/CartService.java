package com.kenny.microservices.core.cart.businesslayer;

import com.kenny.microservices.core.cart.datalayer.Cart;
import com.kenny.microservices.core.cart.datalayer.CartDTO;
import com.kenny.microservices.core.cart.datalayer.CartIdLessDTO;

import java.util.List;

public interface CartService {
    public CartDTO addToCart(CartIdLessDTO product);

    List<CartDTO> getTheCart();

    public void deleteCart(String product_id);

    public CartDTO incrementQuantity(CartDTO cart);

    public CartDTO decrementQuantity(CartDTO cart);

}
