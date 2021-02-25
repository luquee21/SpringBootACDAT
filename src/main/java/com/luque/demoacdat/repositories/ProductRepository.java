package com.luque.demoacdat.repositories;

import com.luque.demoacdat.model.Product;
import com.luque.demoacdat.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM product WHERE name LIKE %?1%", nativeQuery = true)
    List<Product> getProductsByName(String name);

    @Query(value = "SELECT * FROM supplier as s " +
            " INNER JOIN product as p ON p.id_supplier = s.id" +
            "WHERE p.id=?1", nativeQuery = true)
    Supplier getSupplierByProduct(Long id);
}
