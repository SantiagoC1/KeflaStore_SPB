package com.keflastore.kfstr.repositories;

import com.keflastore.kfstr.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartsRepository extends JpaRepository<Cart,Long> {
    List<Cart> findAllByClientId(Long clientId);
}
