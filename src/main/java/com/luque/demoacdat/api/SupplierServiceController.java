package com.luque.demoacdat.api;

import com.luque.demoacdat.expections.RecordNotFoundException;
import com.luque.demoacdat.model.Product;
import com.luque.demoacdat.model.Supplier;
import com.luque.demoacdat.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;
import java.util.List;

@SpringBootApplication
@EnableSwagger2
@RestController
@RequestMapping("/supplier")
public class SupplierServiceController {

    @Autowired
    SupplierService service;

    @GetMapping
    public ResponseEntity<List<Supplier>> getAllSupplier() {
        List<Supplier> list = service.getAllSupplier();
        return new ResponseEntity<List<Supplier>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getAllSupplier(@PathVariable("id") Long id) throws RecordNotFoundException {
        Supplier supplier = service.getSuplierById(id);
        return new ResponseEntity<Supplier>(supplier, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Supplier> createSupplier(@Valid @RequestBody Supplier supplier) {
        Supplier supplierr = service.createSupplier(supplier);
        return new ResponseEntity<Supplier>(supplierr, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Supplier> updateSupplier(@Valid @RequestBody Supplier supplier) throws RecordNotFoundException {
        Supplier supplierr = service.updateSupplier(supplier);
        return new ResponseEntity<Supplier>(supplierr, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteSupplier(@PathVariable("id") Long id) throws RecordNotFoundException {
        service.deleteSupplierById(id);
        return HttpStatus.FORBIDDEN;
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<Supplier>> getSupplierByName(@PathVariable("name") String name) throws RecordNotFoundException {
        List<Supplier> suppliers = service.getSupplierByName(name);
        return new ResponseEntity<List<Supplier>>(suppliers, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/supplier/{id}")
    public ResponseEntity<List<Product>> getProductsBySupplier(@PathVariable("id") Long id) throws RecordNotFoundException {
        List<Product> products = service.getProductsBySupplier(id);
        return new ResponseEntity<List<Product>>(products, new HttpHeaders(), HttpStatus.OK);
    }

}
