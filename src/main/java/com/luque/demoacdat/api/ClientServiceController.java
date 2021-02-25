package com.luque.demoacdat.api;

import com.luque.demoacdat.expections.RecordNotFoundException;
import com.luque.demoacdat.model.Client;
import com.luque.demoacdat.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientServiceController {

    @Autowired
    ClientService service;

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> list = service.getAllClients();
        return new ResponseEntity<List<Client>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long id) throws RecordNotFoundException {
        Client client = service.getClientById(id);
        return new ResponseEntity<Client>(client, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@Valid @RequestBody Client client) {
        Client clientt = service.createClient(client);
        return new ResponseEntity<Client>(clientt, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Client> updateClient(@Valid @RequestBody Client client) throws RecordNotFoundException {
        Client clientt = service.updateClient(client);
        return new ResponseEntity<Client>(clientt, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteClient(@PathVariable("id") Long id) throws RecordNotFoundException {
        service.deleteClientById(id);
        return HttpStatus.FORBIDDEN;
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<Client>> getClientByName(@PathVariable("name") String name) throws RecordNotFoundException {
        List<Client> clients = service.getClientsByName(name);
        return new ResponseEntity<List<Client>>(clients, new HttpHeaders(), HttpStatus.OK);
    }

}
