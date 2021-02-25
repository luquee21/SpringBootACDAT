package com.luque.demoacdat.service;

import com.luque.demoacdat.expections.RecordNotFoundException;
import com.luque.demoacdat.model.Client;
import com.luque.demoacdat.model.Client_Product;
import com.luque.demoacdat.model.Product;
import com.luque.demoacdat.repositories.Client_ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Client_ProductService {
    @Autowired
    Client_ProductRepository repository;

    public Client_Product createProductService(Client_Product client_product) {
        client_product = repository.save(client_product);
        return client_product;
    }


    public void deleteProductServiceById(Long id) throws RecordNotFoundException {
        Optional<Client_Product> client_product = repository.findById(id);
        if (client_product.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Producto y cliente no encontrado con esa id", id);
        }
    }


    public List<Product> getProductsByClient(Long id) {
        List<Product> products = repository.getProductsByClient(id);
        if (products.size() > 0) {
            return products;
        } else {
            return new ArrayList<>();
        }
    }


    public List<Client> getClientsByProducts(Long id) {
        List<Client> clients = repository.getClientsByProducts(id);
        if (clients.size() > 0) {
            return clients;
        } else {
            return new ArrayList<>();
        }
    }

    public List<Product> getProductsByDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        List<Product> products = repository.getProductsByDate(formatter.format(date));
        if (products.size() > 0) {
            return products;
        } else {
            return new ArrayList<>();
        }
    }

    public int getTotalPurchasedProductsOfClient(Long id) {
        int total = repository.getTotalPurchasedProductsOfClient(id);
        return total;
    }

    public int getTotalPurchasedProductOfClient(Long id_product, Long id_client) {
        int total = repository.getTotalPurchasedProductOfClient(id_product, id_client);
        return total;
    }

}
