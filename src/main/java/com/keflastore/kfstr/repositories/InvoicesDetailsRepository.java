package com.keflastore.kfstr.repositories;

import com.keflastore.kfstr.entities.InvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoicesDetailsRepository extends JpaRepository<InvoiceDetail,Long> {
}
