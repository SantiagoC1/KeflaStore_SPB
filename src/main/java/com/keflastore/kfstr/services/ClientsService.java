package com.keflastore.kfstr.services;

import com.keflastore.kfstr.entities.Cart;
import com.keflastore.kfstr.entities.Client;
import com.keflastore.kfstr.entities.Invoice;
import com.keflastore.kfstr.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientsService {
    @Autowired
    private ClientsRepository repository;

    public Client save(Client client) {
        return repository.save(client);
    }

    public List<Client> findAll() {
        return repository.findAll();
    }


    public Optional<Client> readOneClient(Long id) {
        //devuelve la clase client si la encuentra
        //deveuelve null si no la encuentra
        return repository.findById(id);
    }
    public void delete(Long id) {
        repository.deleteById(id);
    }
    public List<Cart> getCartsByClientId(Long clientId) {
        Optional<Client> client = readOneClient(clientId);
        return client.isPresent() ? client.get().getCarts() : null;
    }

    public List<Invoice> getInvoicesByClientId(Long clientId) {
        Optional<Client> client = readOneClient(clientId);
        return client.isPresent() ? client.get().getInvoices() : null;
    }

}
