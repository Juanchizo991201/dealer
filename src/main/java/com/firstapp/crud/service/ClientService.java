package com.firstapp.crud.service;

import com.firstapp.crud.model.Client;
import com.firstapp.crud.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public boolean exists(String id) {
        return clientRepository.existsById(id);
    }

    public List<Client> getClients() {
        return clientRepository.findAll();    }

    public Client getClientById(String id) {
        return clientRepository.findById(id).orElse(null);
    }

    public Client getClientByEmail(String email) {
        return clientRepository.findByEmail(email).orElse(null);
    }

    public void save(Client client) {
        clientRepository.save(client);
    }

    public void delete(Client client) {
        clientRepository.delete(client);
    }
}
