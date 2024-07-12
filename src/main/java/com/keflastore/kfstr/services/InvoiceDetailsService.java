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
    private InvoicesDetailsRepository invoiceDetailRepository;

    public List<InvoiceDetail> getAllInvoiceDetails() {
        return invoiceDetailRepository.findAll();
    }

    public Optional<InvoiceDetail> getInvoiceDetailById(Long id) {
        return invoiceDetailRepository.findById(id);
    }

    public InvoiceDetail saveInvoiceDetail(InvoiceDetail invoiceDetail) {
        return invoiceDetailRepository.save(invoiceDetail);
    }

    public void deleteInvoiceDetail(Long id) {
        invoiceDetailRepository.deleteById(id);
    }
}
