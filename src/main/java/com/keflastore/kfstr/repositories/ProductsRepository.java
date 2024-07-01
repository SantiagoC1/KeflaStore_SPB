package com.keflastore.kfstr.repositories;


import com.keflastore.kfstr.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository  extends JpaRepository<Product,Long> {

}
