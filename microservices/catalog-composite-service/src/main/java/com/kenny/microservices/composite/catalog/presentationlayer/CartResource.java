package com.kenny.microservices.composite.catalog.presentationlayer;

import com.kenny.microservices.composite.catalog.businesslayer.CartService;
import com.kenny.microservices.composite.catalog.datalayer.CartDTO;
import com.kenny.microservices.composite.catalog.datalayer.CartIdLessDTO;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@Slf4j
@Timed("kenny.composite.catalog")
public class CartResource {

    private final CartService cartService;

    public CartResource(CartService cartService){
        this.cartService = cartService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping( "/cart/addToCart")
    @ResponseStatus(HttpStatus.CREATED)
    public CartDTO addToCart(@Valid @RequestBody CartIdLessDTO cart) {
        return cartService.addToCart(cart);
    }
}
