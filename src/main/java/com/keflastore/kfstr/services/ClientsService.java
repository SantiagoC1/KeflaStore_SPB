package com.keflastore.kfstr.services;


import com.keflastore.kfstr.entities.Client;
import com.keflastore.kfstr.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
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

    public Client updateClient(Long id, Client clientDetails) throws ConfigDataResourceNotFoundException {
        Optional<Client> clientOptional = readOneClient(id);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();

            if (clientDetails.getName() != null) {
                client.setName(clientDetails.getName());
            }
            if (clientDetails.getLastname() != null) {
                client.setLastname(clientDetails.getLastname());
            }
            if (clientDetails.getAge() != null) {
                client.setAge(clientDetails.getAge());
            }
            if (clientDetails.getEmail() != null) {
                client.setEmail(clientDetails.getEmail());
            }
            if (clientDetails.getPhone() != null) {
                client.setPhone(clientDetails.getPhone());
            }
            if (clientDetails.getAddress() != null) {
                client.setAddress(clientDetails.getAddress());
            }
            if (clientDetails.getCity() != null) {
                client.setCity(clientDetails.getCity());
            }
            if (clientDetails.getCountry() != null) {
                client.setCountry(clientDetails.getCountry());
            }
            if (clientDetails.getDocnumber() != null) {
                client.setDocnumber(clientDetails.getDocnumber());
            }

            return save(client);
        } else {
            throw new RuntimeException("client not found with ID: " + id);
        }
    }

    public boolean delete(Long id) {
        Optional<Client> clientOptional = readOneClient(id);
        if (clientOptional.isPresent()) {
            repository.delete(clientOptional.get());
            return true;
        } else {
            return false;
        }
    }


}
