package com.kenny.microservices.composite.catalog.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, String> {
}
