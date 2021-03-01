package com.luque.demoacdat.api;

import com.luque.demoacdat.expections.RecordNotFoundException;
import com.luque.demoacdat.model.Product;
import com.luque.demoacdat.model.Supplier;
import com.luque.demoacdat.service.ProductService;
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
@RequestMapping("/product")
public class ProductServiceController {

    @Autowired
    ProductService service;


    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> list = service.getAllProducts();
        return new ResponseEntity<List<Product>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws RecordNotFoundException {
        Product product = service.getProductsById(id);
        return new ResponseEntity<Product>(product, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        Product productt = service.createProduct(product);
        return new ResponseEntity<Product>(productt, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product) throws RecordNotFoundException {
        Product productt = service.updateProduct(product);
        return new ResponseEntity<Product>(productt, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteProduct(@PathVariable("id") Long id) throws RecordNotFoundException {
        service.deleteProductById(id);
        return HttpStatus.FORBIDDEN;
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<Product>> getProductsByName(@PathVariable("name") String name) throws RecordNotFoundException {
        List<Product> products = service.getProductsByName(name);
        return new ResponseEntity<List<Product>>(products, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/supplier/{id}")
    public ResponseEntity<Supplier> getSupplierByProduct(@PathVariable("id") Long id) throws RecordNotFoundException {
        Supplier supplier = service.getSupplierByProduct(id);
        return new ResponseEntity<Supplier>(supplier, new HttpHeaders(), HttpStatus.OK);
    }


}
