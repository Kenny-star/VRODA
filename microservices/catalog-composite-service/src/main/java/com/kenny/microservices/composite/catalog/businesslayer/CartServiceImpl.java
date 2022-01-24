package com.kenny.microservices.composite.catalog.businesslayer;

import com.kenny.microservices.composite.catalog.datalayer.Cart;
import com.kenny.microservices.composite.catalog.datalayer.CartDTO;
import com.kenny.microservices.composite.catalog.datalayer.CartIdLessDTO;
import com.kenny.microservices.composite.catalog.datalayer.CartRepository;
import com.kenny.microservices.composite.catalog.utils.exceptions.InvalidInputException;
import org.springframework.dao.DuplicateKeyException;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.UUID;

@Service
@Slf4j
public class CartServiceImpl implements CartService{

    private static final Logger LOG = LoggerFactory.getLogger(CartServiceImpl.class);

    private final CartRepository cartRepository;

    private final CartMapper mapper;

    public CartServiceImpl(CartRepository repo, CartMapper mapper) {
        this.cartRepository = repo;
        this.mapper = mapper;
    }

    @Override
    public CartDTO addToCart(CartIdLessDTO cart){

        try{
            Cart cartEntity = mapper.CartIdLessDtoToEntity(cart);
            log.info("Calling product repo to create a product with productCategory: {}", cart.getCategoryId());
            Cart createdEntity = cartRepository.save(cartEntity);

            return mapper.EntityToModelDTO(createdEntity);

//            List<Product> productCartList = new ArrayList<>();
//            productCartList.add(productEntity);
//
//            return productCartList;

        }
        catch(DuplicateKeyException dke){
            throw new InvalidInputException("Duplicate productId.", dke);
        }

    }
}
