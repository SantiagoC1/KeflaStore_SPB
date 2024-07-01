package com.keflastore.kfstr.repositories;

import com.keflastore.kfstr.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartsRepository extends JpaRepository<Cart,Long> {
}
