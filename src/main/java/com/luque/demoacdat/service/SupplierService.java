package com.luque.demoacdat.service;

import com.luque.demoacdat.expections.RecordNotFoundException;
import com.luque.demoacdat.model.Product;
import com.luque.demoacdat.model.Supplier;
import com.luque.demoacdat.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository repository;

    public List<Supplier> getAllSupplier() {
        List<Supplier> suppliers = repository.findAll();
        if (suppliers.size() > 0) {
            return suppliers;
        } else {
            return new ArrayList<>();
        }
    }

    public Supplier getSuplierById(Long id) throws RecordNotFoundException {
        Optional<Supplier> supplier = repository.findById(id);
        if (supplier.isPresent()) {
            return supplier.get();
        } else {
            throw new RecordNotFoundException("No hay proveedor con ese id", id);
        }
    }

    public Supplier createSupplier(Supplier supplier) {
        supplier = repository.save(supplier);
        return supplier;
    }

    public Supplier updateSupplier(Supplier supplier) throws RecordNotFoundException {
        if (supplier.getId() != null) {
            Optional<Supplier> suppliers = repository.findById(supplier.getId());
            if (suppliers.isPresent()) {
                Supplier supplierr = suppliers.get();
                supplierr.setCode(supplier.getCode());
                supplierr.setDescription(supplier.getDescription());
                supplierr.setProducts(supplier.getProducts());
                supplierr.setName(supplier.getName());
                supplierr = repository.save(supplierr);
                return supplierr;
            } else {
                throw new RecordNotFoundException("proveedor no encontrado con esa id", supplier.getId());
            }
        } else {
            throw new RecordNotFoundException("El proveedor no tiene id", supplier.getId());
        }
    }

    public void deleteSupplierById(Long id) throws RecordNotFoundException {
        Optional<Supplier> supliers = repository.findById(id);
        if (supliers.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Proveedor no encontrado con esa id", id);
        }
    }

    public List<Supplier> getSupplierByName(String name) throws RecordNotFoundException {
        List<Supplier> suppliers = repository.getSupplierByName(name);
        if (suppliers.size() > 0) {
            return suppliers;
        } else {
            return new ArrayList<Supplier>();
        }
    }

    public List<Product> getProductsBySupplier(Long id) throws RecordNotFoundException {
        Optional<Supplier> supplier = repository.findById(id);
        if (supplier.isPresent()) {
            List<Product> products = repository.getProductsBySupplier(id);
            if (products != null && !products.isEmpty()) {
                return products;
            } else {
                throw new RecordNotFoundException("productos no encontrados de este proveedor", id);
            }
        } else {
            throw new RecordNotFoundException("Proveedor no encontrado con esa id", id);
        }
    }
}
