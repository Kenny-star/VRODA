
package com.kenny.domainclientlayer;

import com.kenny.dtos.Cart;
import com.kenny.dtos.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CartServiceClient {

    private final WebClient.Builder webClientBuilder;
    private String hostname;

    @Autowired
    public CartServiceClient(
            WebClient.Builder webClientBuilder,
            @Value("${app.cart-service.host}") String catalogcompositeHost,
            @Value("${app.cart-service.port}") String catalogcompositePort
    ) {
        this.webClientBuilder = webClientBuilder;
        hostname = "http://" + catalogcompositeHost + ":" + catalogcompositePort;
    }

    public Mono<Cart> addToCart(Cart cart){
        return webClientBuilder.build()
                .post()
                .uri(hostname+ "/cart/addToCart")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(cart), Cart.class)
                .retrieve()
                .bodyToMono(Cart.class);
    }

    public Flux<Cart> getTheCart() {
        return webClientBuilder.build().get()
                .uri(hostname + "/cart")
                .retrieve()
                .bodyToFlux(Cart.class);
    }

    public Mono<Void> deleteCart(final String product_id){
        return webClientBuilder.build()
                .delete()
                .uri(hostname + "/cart/delete/{product_id}", product_id)
                .retrieve()
                .bodyToMono(Void.class);
    }

    public Mono<Cart> incrementQuantity(Cart cart){
        return webClientBuilder.build()
                .put()
                .uri(hostname + "/cart/increment/{product_id}", cart.getProductId())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(cart), Cart.class)
                .retrieve()
                .bodyToMono(Cart.class);
    }

    public Mono<Cart> decrementQuantity(Cart cart){
        return webClientBuilder.build()
                .put()
                .uri(hostname + "/cart/decrement/{product_id}", cart.getProductId())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(cart), Cart.class)
                .retrieve()
                .bodyToMono(Cart.class);
    }
}