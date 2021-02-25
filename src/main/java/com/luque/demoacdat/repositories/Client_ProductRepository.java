package com.luque.demoacdat.repositories;

import com.luque.demoacdat.model.Client;
import com.luque.demoacdat.model.Client_Product;
import com.luque.demoacdat.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Client_ProductRepository extends JpaRepository<Client_Product, Long> {

    @Query(value = "SELECT * FROM product as p" +
            " INNER JOIN client_product as cp ON cp.id = p.id" +
            " INNER JOIN client as c ON c.id = cp.id " +
            " WHERE c.id = ?1", nativeQuery = true)
    List<Product> getProductsByClient(Long id);

    @Query(value = "SELECT * FROM client as c" +
            " INNER JOIN product as p ON c.id = cp.id " +
            " INNER JOIN client_product as cp ON cp.id = p.id" +
            " WHERE p.id = ?1", nativeQuery = true)
    List<Client> getClientsByProducts(Long id);

    @Query(value = "SELECT * FROM product as p" +
            " INNER JOIN client_product as cp ON cp.id = p.id" +
            " WHERE p.date_purchase = ?1", nativeQuery = true)
    List<Product> getProductsByDate(String date);

    @Query(value = "SELECT SUM(amount) FROM client_product WHERE id_client = ?1", nativeQuery = true)
    int getTotalPurchasedProductsOfClient(Long id);


    @Query(value = "SELECT SUM(amount) FROM client_product WHERE id_product = ?1 AND id_client =?2", nativeQuery = true)
    int getTotalPurchasedProductOfClient(Long id_product, Long id_client);
}


