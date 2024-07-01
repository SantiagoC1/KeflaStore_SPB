package com.keflastore.kfstr.repositories;

import com.keflastore.kfstr.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoicesRepository extends JpaRepository<Invoice,Long> {
}
