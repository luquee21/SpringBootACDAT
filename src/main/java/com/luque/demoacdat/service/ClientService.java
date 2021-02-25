package com.luque.demoacdat.service;

import com.luque.demoacdat.expections.RecordNotFoundException;
import com.luque.demoacdat.model.Client;
import com.luque.demoacdat.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository repository;

    public List<Client> getAllClients() {
        List<Client> clients = repository.findAll();
        if (clients.size() > 0) {
            return clients;
        } else {
            return new ArrayList<>();
        }
    }

    public Client getClientById(Long id) throws RecordNotFoundException {
        Optional<Client> clients = repository.findById(id);
        if (clients.isPresent()) {
            return clients.get();
        } else {
            throw new RecordNotFoundException("No hay cliente con ese id", id);
        }
    }

    public Client createClient(Client client) {
        client = repository.save(client);
        return client;
    }

    public Client updateClient(Client client) throws RecordNotFoundException {
        if (client.getId() != null) {
            Optional<Client> clients = repository.findById(client.getId());
            if (clients.isPresent()) {
                Client clientt = clients.get();
                clientt.setClients(client.getClients());
                clientt.setAddress(client.getAddress());
                clientt.setName(client.getName());
                clientt.setPhone(client.getPhone());
                clientt.setSurnames(client.getSurnames());
                clientt = repository.save(clientt);
                return clientt;
            } else {
                throw new RecordNotFoundException("Cliente no encontrado con esa id", client.getId());
            }
        } else {
            throw new RecordNotFoundException("El cliente no tiene id", client.getId());
        }
    }

    public void deleteClientById(Long id) throws RecordNotFoundException {
        Optional<Client> clients = repository.findById(id);
        if (clients.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Cliente no encontrado con esa id", id);
        }
    }

    public List<Client> getClientsByName(String name) throws RecordNotFoundException {
        List<Client> clients = repository.getClientByName(name);

        if (clients.size() > 0) {
            return clients;
        } else {
            return new ArrayList<Client>();
        }
    }

}
