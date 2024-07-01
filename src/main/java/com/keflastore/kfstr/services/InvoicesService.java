package com.keflastore.kfstr.services;

import com.keflastore.kfstr.entities.Invoice;
import com.keflastore.kfstr.repositories.InvoicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoicesService {
    @Autowired
    private InvoicesRepository invoicesRepository;

    public Invoice save(Invoice invoice) {
        return invoicesRepository.save(invoice);
    }

    public List<Invoice> findAll(){
        return invoicesRepository.findAll();
    }

    public Optional<Invoice> readOneCart(Long id) {
        return invoicesRepository.findById(id);
    }

    public void delete(Long id) {
        invoicesRepository.deleteById(id);
    }
}
