package com.firstapp.crud.controller;

import com.firstapp.crud.model.Client;
import com.firstapp.crud.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.text.MessageFormat;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Client>> getClients() {
        try {
            return ResponseEntity.ok().body(this.clientService.getClients());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable String id) {
        try {
            if (this.clientService.exists(id)) {
                Client client = this.clientService.getClientById(id);
                return ResponseEntity.ok().body(client);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            // Log the exception or handle it as appropriate for your application
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/")
    public ResponseEntity<String> addClient(@RequestBody Client client) {
        try {
            if (client.getId() != null && !this.clientService.exists(String.valueOf(client.getId()))) {
                if (this.clientService.getClientByEmail(client.getEmail()) == null) {
                    clientService.save(client);
                    return ResponseEntity.ok().body(MessageFormat.format("Client with id {0} added", client.getId()));
                } else {
                    return ResponseEntity.badRequest().body(MessageFormat.format("Client with email {0} already exists", client.getEmail()));
                }
            } else {
                return ResponseEntity.badRequest().body(MessageFormat.format("Client with id {0} already exists", client.getId()));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/")
    public ResponseEntity<String> updateClient(@RequestBody Client client) {
        try {
            if (client.getId() != null && this.clientService.exists(String.valueOf(client.getId()))) {
                clientService.save(client);
                return ResponseEntity.ok().body(MessageFormat.format("Client with id {0} updated", client.getId()));
            } else {
                return ResponseEntity.badRequest().body(MessageFormat.format("Client with id {0} does not exist", client.getId()));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable String id) {
        try {
            if (this.clientService.exists(id)) {
                Client client = this.clientService.getClientById(id);
                clientService.delete(client);
                return ResponseEntity.ok().body(MessageFormat.format("Client with id {0} deleted", id));
            } else {
                return ResponseEntity.badRequest().body(MessageFormat.format("Client with id {0} does not exist", id));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
