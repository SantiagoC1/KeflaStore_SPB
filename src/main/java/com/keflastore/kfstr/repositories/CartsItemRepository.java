package com.keflastore.kfstr.repositories;

import com.keflastore.kfstr.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartsItemRepository extends JpaRepository<CartItem, Long> {
}