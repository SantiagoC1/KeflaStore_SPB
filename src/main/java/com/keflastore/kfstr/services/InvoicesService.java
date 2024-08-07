package com.keflastore.kfstr.services;

import com.keflastore.kfstr.entities.Cart;
import com.keflastore.kfstr.entities.Client;
import com.keflastore.kfstr.entities.Invoice;

import com.keflastore.kfstr.repositories.CartsRepository;
import com.keflastore.kfstr.repositories.ClientsRepository;
import com.keflastore.kfstr.repositories.InvoicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InvoicesService {
    @Autowired
    private InvoicesRepository invoiceRepository;
    @Autowired
    private ClientsRepository clientsRepository;
    @Autowired
    private CartsRepository cartsRepository;

    public Invoice generateInvoice(Long clientId){
        Optional<Client> client = clientsRepository.findById(clientId);
        if (client.isPresent()){
            List<Cart> carts = cartsRepository.findByClientIdAndState(clientId,false);
            if (carts.isEmpty()){
                throw new RuntimeException("not founds products on cart");
            }else {
                Client foundClient = client.get();
                Invoice invoice=new Invoice();
                invoice.setClient(foundClient);
                invoice.setDate(LocalDateTime.now());
                double total=0.0;
                for (Cart cart:carts){
                    total+=cart.getQuantity()*cart.getPrice();
                    cart.setState(true);
                }
                invoice.setTotalAmount(total);
                return invoiceRepository.save(invoice);
            }
        }else {
            throw new RuntimeException("Client not found");
        }
    }

    public Invoice getInvoiceByClientId(Long clientId){
        Optional<Client> client = clientsRepository.findById(clientId);
        if (client.isPresent()){
            List<Invoice> invoices=client.get().getInvoices();
            if (invoices.isEmpty()){
                throw new RuntimeException("No invoices found for the client");
            }
            Invoice lastinvoice= invoices.get(invoices.size()-1);
            return lastinvoice;
        }else {
            throw new RuntimeException("Client not found");
        }
    }

}
