package com.api.api_clients.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Integer id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + id));
    }

    /*public Client createClient(Client c) {

        if (c.getName() == null || c.getName().isEmpty())
            throw new IllegalArgumentException("The name must not be empty.");

        if (c.getEmail() == null || c.getEmail().isEmpty())
            throw new IllegalArgumentException("The email must not be empty.");

        if (c.getPhone() == null || c.getPhone().isEmpty())
            throw new IllegalArgumentException("The phone must not be empty.");

        c.setId(null);

        return clientRepository.save(c);
    }*/

    public Client createClient(Client c) {
        c.setId(null);
        return clientRepository.save(c);
    }

    public Client updateClient(Client c) {

        if (c.getName() == null || c.getName().isEmpty())
            throw new IllegalArgumentException("The name must not be empty.");

        if (c.getEmail() == null || c.getEmail().isEmpty())
            throw new IllegalArgumentException("The email must not be empty.");

        if (c.getPhone() == null || c.getPhone().isEmpty())
            throw new IllegalArgumentException("The phone must not be empty.");

        Client oC = clientRepository.findById(c.getId()) // oldClient
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + c.getId()));

        if (c.getName().equals(oC.getName()) && c.getEmail().equals(oC.getEmail()) && c.getPhone().equals(oC.getPhone()))
            throw new IllegalArgumentException("You have to modify at least one field.");

        return clientRepository.save(c);
    }

    public void deleteClient(Integer id) {
        Client c = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + id));

        clientRepository.deleteById(c.getId());
    }
}
