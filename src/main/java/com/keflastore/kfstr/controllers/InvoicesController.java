package com.keflastore.kfstr.controllers;

import com.keflastore.kfstr.entities.Invoice;
import com.keflastore.kfstr.services.InvoicesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/invoices")
@Tag(name = "Invoice routes", description = "CRUD of invoices")
public class InvoicesController {

    @Autowired
    private InvoicesService invoiceService;

    @Operation(summary = "Generate an invoice",
            description = "Generate an invoice for the client's cart with total amount")
    @ApiResponse(responseCode = "200",description = "Invoices generated successfully"
            ,content = @Content(mediaType = "application/json",schema = @Schema(implementation = Invoice.class)))
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"Invalid client ID format\" }")))
    @ApiResponse(responseCode = "404", description = "Client invoice not found",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"Client invoice not found\" }")))
    @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"An unexpected error occurred\" }")))
    @PostMapping
    public ResponseEntity<Invoice>generateInvoice(@RequestParam Long clientId){
        try {
            Invoice invoice= invoiceService.generateInvoice(clientId);
            return ResponseEntity.status(HttpStatus.CREATED).body(invoice);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @Operation(summary = "get invoice by client ID",
            description = "returns the last invoice for the specified client")
    @ApiResponse(responseCode = "200",description = "Invoices retrived successfully"
            ,content = @Content(mediaType = "application/json",schema = @Schema(implementation = Invoice.class)))
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"Invalid client ID format\" }")))
    @ApiResponse(responseCode = "404", description = "Client invoice not found",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"Client invoice not found\" }")))
    @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"An unexpected error occurred\" }")))
    @GetMapping("/{clientId}")
    public ResponseEntity<Invoice> getInvoiceByClientId(@PathVariable Long clientId){
        try {
            Invoice invoice = invoiceService.getInvoiceByClientId(clientId);
            return ResponseEntity.ok(invoice);
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}