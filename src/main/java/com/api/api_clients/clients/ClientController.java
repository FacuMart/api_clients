package com.api.api_clients.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> c = clientService.getAllClients();
        return ResponseEntity.ok(c);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Integer id) {
        try {
            Client c = clientService.getClientById(id);
            return ResponseEntity.ok(c);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody Client client) {
        try {
            Client createdClient = clientService.createClient(client);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating client: " + ex.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateClient(@RequestBody Client client) {
        try {
            Client updatedClient = clientService.updateClient(client);
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedClient);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating client: " + ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Integer id) {
        try {
            clientService.deleteClient(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting client: " + ex.getMessage());
        }
    }
}
