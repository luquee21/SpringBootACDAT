package com.luque.demoacdat.api;

import com.luque.demoacdat.expections.RecordNotFoundException;
import com.luque.demoacdat.model.Client;
import com.luque.demoacdat.model.Client_Product;
import com.luque.demoacdat.model.Product;
import com.luque.demoacdat.service.Client_ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/client_product")
public class Client_ProductServiceController {

    @Autowired
    Client_ProductService service;

    @PostMapping
    public ResponseEntity<Client_Product> createClientProduct(@Valid @RequestBody Client_Product client_product) {
        Client_Product entity = service.createProductService(client_product);
        return new ResponseEntity<Client_Product>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteProductServiceById(@PathVariable("id") Long id) throws RecordNotFoundException {
        service.deleteProductServiceById(id);
        return HttpStatus.FORBIDDEN;
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<List<Product>> getProductsByClient(@PathVariable("id") Long id) {
        List<Product> products = service.getProductsByClient(id);
        return new ResponseEntity<List<Product>>(products, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<List<Client>> getClientsByProducts(@PathVariable("id") Long id) {
        List<Client> clients = service.getClientsByProducts(id);
        return new ResponseEntity<List<Client>>(clients, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Product>> getProductsByDate(@PathVariable("date") String date) {
        List<Product> products = service.getProductsByDate(date);
        return new ResponseEntity<List<Product>>(products, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/client/total_purchased_products/{id}")
    public ResponseEntity<Integer> getTotalPurchasedProductsOfClient(@PathVariable("id") Long id) {
        int amount = service.getTotalPurchasedProductsOfClient(id);
        return new ResponseEntity<Integer>(amount, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/client/total_purchased_products/{id}/product/{id_product}")
    public ResponseEntity<Integer> getTotalPurchasedProductOfClient(@PathVariable("id") Long id, @PathVariable("id_product") Long id_product) {
        int amount = service.getTotalPurchasedProductOfClient(id_product, id);
        return new ResponseEntity<Integer>(amount, new HttpHeaders(), HttpStatus.OK);
    }

}
