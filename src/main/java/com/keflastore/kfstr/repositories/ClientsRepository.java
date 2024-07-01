package com.keflastore.kfstr.repositories;

import com.keflastore.kfstr.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepository extends JpaRepository<Client,Long> {

}
