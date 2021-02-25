package com.luque.demoacdat.repositories;

import com.luque.demoacdat.model.Product;
import com.luque.demoacdat.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query(value = "SELECT * FROM supplier WHERE name LIKE %?1%", nativeQuery = true)
    List<Supplier> getSupplierByName(String name);

    @Query(value = "SELECT * FROM product as p " +
            " INNER JOIN supplier as s ON p.id_supplier = s.id" +
            "WHERE s.id=?1", nativeQuery = true)
    List<Product> getProductsBySupplier(Long id);

}
