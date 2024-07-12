package com.keflastore.kfstr.controllers;


import com.keflastore.kfstr.entities.Cart;
import com.keflastore.kfstr.entities.Client;
import com.keflastore.kfstr.entities.Invoice;
import com.keflastore.kfstr.services.ClientsService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/clients")
public class ClientsController {

    @Autowired
    private ClientsService service;
    private static final Logger logger = LoggerFactory.getLogger(ClientsController.class);

    //CREAR
    @PostMapping()
    public Client saveClient(@RequestBody Client client) {

        try {
            return service.save(client);
        }catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("CREATE ERROR ");
        }
    }

    //LEER TODOS
    @GetMapping()
    public List<Client> getAllClients() {
        try {
            return service.findAll();

        }catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("READ ALL ERROR ");

        }
    }

    //LEER POR ID
    @GetMapping("/{id}")
    public Optional<Client> getClient(@PathVariable Long id) {
        try {
            return service.readOneClient(id);
        }catch (Exception e) {
        System.out.println(e);
        throw new RuntimeException("FIND ONE ERROR ");

    }
    }

    //ACTUALIZAR
    @PutMapping("{cid}")
    public ResponseEntity<Client> updateClient(@PathVariable Long cid, @RequestBody Client client) {
        Optional<Client> clientOptional = service.readOneClient(cid);
        if (clientOptional.isPresent()) {
            Client clientu = clientOptional.get();
            clientu.setName(client.getName());
            clientu.setLastname(client.getLastname());
            clientu.setAge(client.getAge());
            clientu.setDocnumber(client.getDocnumber());
            Client clientUpdated = service.save(clientu);
            return ResponseEntity.ok(clientUpdated);
        }else {
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping("/{id}/carts")
    public ResponseEntity<List<Cart>> getCarts(@PathVariable Long id) {
        try {
            List<Cart> carts = service.getCartsByClientId(id);
            return new ResponseEntity<>(carts, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching carts for client with ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/invoices")
    public ResponseEntity<List<Invoice>> getInvoices(@PathVariable Long id) {
        try {
            List<Invoice> invoices = service.getInvoicesByClientId(id);
            return new ResponseEntity<>(invoices, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching invoices for client with ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //ELIMINAR
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        try {
            service.delete(id);
        }catch (Exception e) {
        System.out.println(e);
        throw new RuntimeException("DELETE ERROR ");

        }
    }



}
