package com.keflastore.kfstr.controllers;

import com.keflastore.kfstr.entities.Invoice;
import com.keflastore.kfstr.services.InvoicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoicesController {

    @Autowired
    private InvoicesService invoiceService;


    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        return ResponseEntity.ok(invoices);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Invoice>> getInvoiceById(@PathVariable Long id) {
        Optional<Invoice> invoice = invoiceService.getInvoiceById(id);
        if (invoice.isPresent()) {
            return ResponseEntity.ok(invoice);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}