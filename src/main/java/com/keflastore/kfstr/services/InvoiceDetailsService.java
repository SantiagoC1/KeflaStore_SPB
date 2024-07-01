package com.keflastore.kfstr.services;

import com.keflastore.kfstr.controllers.InvoiceDetailsController;
import com.keflastore.kfstr.entities.Cart;
import com.keflastore.kfstr.entities.InvoiceDetail;
import com.keflastore.kfstr.repositories.InvoicesDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceDetailsService {
    @Autowired
    private InvoicesDetailsRepository invoicesDetailsRepository;

    public InvoiceDetail save(InvoiceDetail invoiceDetail) {
        return invoicesDetailsRepository.save(invoiceDetail);
    }

    public List<InvoiceDetail> findAll(){
        return invoicesDetailsRepository.findAll();
    }

    public Optional<InvoiceDetail> readOneCart(Long id) {
        return invoicesDetailsRepository.findById(id);
    }

    public void delete(Long id) {
        invoicesDetailsRepository.deleteById(id);
    }
}
