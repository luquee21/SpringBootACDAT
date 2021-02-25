package com.luque.demoacdat.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client_product")
public class Client_Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @JsonIgnoreProperties(value = {"clients"}, allowSetters = true)
    @ManyToOne(cascade = {CascadeType.MERGE}, optional = false)
    @JoinColumn(name = "id_client")
    private Client client;

    @JsonIgnoreProperties(value = {"products"}, allowSetters = true)
    @ManyToOne(cascade = {CascadeType.MERGE}, optional = false)
    @JoinColumn(name = "id_product")
    private Product product;

    @Column(name = "date_purchase", nullable = false)
    private Timestamp date_purchase;

    @Column(name = "amount", nullable = false)
    private int amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
        List<Client_Product> clients = this.client.getClients();
        if (clients == null) {
            clients = new ArrayList<>();
        }
        if (!clients.contains(this)) {
            clients.add(this);
        }
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        List<Client_Product> products = this.product.getProducts();
        if (products == null) {
            products = new ArrayList<>();
        }
        if (!products.contains(this)) {
            products.add(this);
        }
    }

    public Timestamp getDate_purchase() {
        return date_purchase;
    }

    public void setDate_purchase(Timestamp date_purchase) {
        this.date_purchase = date_purchase;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
