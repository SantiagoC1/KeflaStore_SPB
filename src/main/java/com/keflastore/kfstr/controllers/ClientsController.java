package com.keflastore.kfstr.controllers;


import com.keflastore.kfstr.entities.Cart;
import com.keflastore.kfstr.entities.Client;
import com.keflastore.kfstr.entities.Invoice;
import com.keflastore.kfstr.services.ClientsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/clients")
@Tag(name = "User routes", description = "CRUD of users")
public class ClientsController {

    @Autowired
    private ClientsService service;


    //CREAR
    @Operation(summary = "generate a client",
            description = "Generate a  client")
    @ApiResponse(responseCode = "201", description = "client generated successfully"
            , content = @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class)))
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"Invalid body format\" }")))
    @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"An unexpected error occurred\" }")))
    @PostMapping()
    public ResponseEntity<Client> saveClient(@RequestBody Client client) {
        try {
            Client cli = service.save(client);
            return ResponseEntity.status(HttpStatus.CREATED).body(cli);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //LEER TODOS
    @Operation(summary = "get list of clients",
            description = "returns a list of clients")
    @ApiResponse(responseCode = "200", description = "clients retrived successfully"
            ,content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Client.class))))
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"Invalid format\" }")))
    @ApiResponse(responseCode = "404", description = "Clients not found",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"list of clients not found\" }")))
    @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"An unexpected error occurred\" }")))
    @GetMapping()
    public ResponseEntity<List<Client>> getAllClients() {
        try {
            List<Client> clients = service.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(clients);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //LEER POR ID
    @Operation(summary = "get a client by id",
            description = "returns the client with the given ID")
    @ApiResponse(responseCode = "200", description = "client retrived successfully"
            , content = @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class)))
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"Invalid ID format\" }")))
    @ApiResponse(responseCode = "404", description = "Client or product not found",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"Client not found\" }")))
    @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"An unexpected error occurred\" }")))
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Client>> getClient(@PathVariable Long id) {
        try {
            Optional<Client> client = service.readOneClient(id);
            return ResponseEntity.status(HttpStatus.OK).body(client);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    //ACTUALIZAR
    @Operation(summary = "update client by ID",
            description = "returns the client updated")
    @ApiResponse(responseCode = "201", description = "client updated successfully"
            , content = @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class)))
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"Invalid ID or body format\" }")))
    @ApiResponse(responseCode = "404", description = "ID not found",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"Client ID not found\" }")))
    @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"An unexpected error occurred\" }")))
    @PutMapping("{cid}")
    public ResponseEntity<Client> updateClient(@PathVariable Long cid, @RequestBody Client client) {
        try {
            Client updatedClient = service.updateClient(cid, client);
            return ResponseEntity.ok(updatedClient);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    //ELIMINAR
    @Operation(summary = "delete a client by ID",
            description = "Deletes the client with the given ID")
    @ApiResponse(responseCode = "200", description = "Client deleted successfully",
            content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"Invalid ID format\" }")))
    @ApiResponse(responseCode = "404", description = "Client not found",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"Client not found\" }")))
    @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"An unexpected error occurred\" }")))
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        try {
            boolean deleted = service.delete(id);
            if (deleted) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}