package com.luque.demoacdat.service;

import com.luque.demoacdat.expections.RecordNotFoundException;
import com.luque.demoacdat.model.Product;
import com.luque.demoacdat.model.Supplier;
import com.luque.demoacdat.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    public List<Product> getAllProducts() {
        List<Product> products = repository.findAll();
        if (products.size() > 0) {
            return products;
        } else {
            return new ArrayList<>();
        }
    }

    public Product getProductsById(Long id) throws RecordNotFoundException {
        Optional<Product> products = repository.findById(id);
        if (products.isPresent()) {
            return products.get();
        } else {
            throw new RecordNotFoundException("No hay producto con ese id", id);
        }
    }

    public Product createProduct(Product product) {
        product = repository.save(product);
        return product;
    }

    public Product updateProduct(Product product) throws RecordNotFoundException {
        if (product.getId() != null) {
            Optional<Product> products = repository.findById(product.getId());
            if (products.isPresent()) {
                Product productt = products.get();
                productt.setSupplier(product.getSupplier());
                productt.setClients(product.getClients());
                productt.setCode(product.getCode());
                productt.setPrice(product.getPrice());
                productt.setDescription(product.getDescription());
                productt.setName(product.getName());
                productt = repository.save(productt);
                return productt;
            } else {
                throw new RecordNotFoundException("producto no encontrado con esa id", product.getId());
            }
        } else {
            throw new RecordNotFoundException("El producto no tiene id", product.getId());
        }
    }

    public void deleteProductById(Long id) throws RecordNotFoundException {
        Optional<Product> products = repository.findById(id);
        if (products.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Producto no encontrado con esa id", id);
        }
    }

    public List<Product> getProductsByName(String name) throws RecordNotFoundException {
        List<Product> products = repository.getProductsByName(name);
        if (products.size() > 0) {
            return products;
        } else {
            return new ArrayList<Product>();
        }
    }

    public Supplier getSupplierByProduct(Long id) throws RecordNotFoundException {
        Optional<Product> product = repository.findById(id);
        if (product.isPresent()) {
            Supplier supplier = repository.getSupplierByProduct(product.get().getId());
            if (supplier != null) {
                return supplier;
            } else {
                throw new RecordNotFoundException("Proveedor no encontrado", id);
            }
        } else {
            throw new RecordNotFoundException("Producto no encontrado con esa id", id);
        }
    }


}
