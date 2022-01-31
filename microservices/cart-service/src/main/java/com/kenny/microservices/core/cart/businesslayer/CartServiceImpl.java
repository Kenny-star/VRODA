package com.kenny.microservices.core.cart.businesslayer;

import com.kenny.microservices.core.cart.datalayer.Cart;
import com.kenny.microservices.core.cart.datalayer.CartDTO;
import com.kenny.microservices.core.cart.datalayer.CartIdLessDTO;
import com.kenny.microservices.core.cart.datalayer.CartRepository;
import com.kenny.microservices.core.cart.utils.exceptions.InvalidInputException;
import org.springframework.dao.DuplicateKeyException;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
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

//    @Override
//    public CartDTO addToCart(CartIdLessDTO cart){
//
//        try{
//            Cart cartEntity = mapper.CartIdLessDtoToEntity(cart);
//            log.info("Calling product repo to create a product with productCategory: {}", cart.getCategoryId());
//            Cart createdEntity = cartRepository.save(cartEntity);
//
//            return mapper.EntityToModelDTO(createdEntity);
//
////            List<Product> productCartList = new ArrayList<>();
////            productCartList.add(productEntity);
////
////            return productCartList;
//
//        }
//        catch(DuplicateKeyException dke){
//            throw new InvalidInputException("Duplicate productId.", dke);
//        }
//
//    }

    @Override
    public List<CartDTO> getTheCart() {
        List<Cart> carts = cartRepository.findAll();
        List<CartDTO> dtos = mapper.entityToModelList(carts);

        return dtos;
    }

    @Override
    public void deleteCart(String product_id){
        Cart product = cartRepository.findProductByProductId(UUID.fromString(product_id)).orElse(new Cart());
        if(product.getProductId() != null)
            cartRepository.delete(product);
        LOG.debug("Product of ID: " + product_id + "has been deleted.");
    }

    @Override
    public CartDTO addToCart(MultipartFile file, String title, int categoryId, double price, int quantity, String description) {
        try{
            CartIdLessDTO product = new CartIdLessDTO();
//            product.setProductId(randomUUID());
            product.setCategoryId(categoryId);
            product.setDescription(description);
            product.setPrice(price);
            product.setTitle(title);

            String filename = StringUtils.cleanPath(file.getOriginalFilename());

            if(filename.contains("..")){
                log.info("Incorrect file format. Try a valid image format");
            }

            product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));

            Cart productEntity = mapper.CartIdLessDtoToEntity(product);
            log.info("Calling product repo to create a product with productCategory: {}", product.getCategoryId());
            Cart createdEntity = cartRepository.save(productEntity);


            return mapper.EntityToModelDTO(createdEntity);

        }
        catch(DuplicateKeyException | IOException dke){
            throw new InvalidInputException("Duplicate productId.", dke);
        }
    }



}
