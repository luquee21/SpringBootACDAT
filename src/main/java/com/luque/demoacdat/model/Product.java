package com.luque.demoacdat.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "description", nullable = false)
    private String description;

    @JsonIgnoreProperties(value = {"product"}, allowSetters = true)
    @OneToMany(mappedBy = "product", cascade = {CascadeType.MERGE})
    private List<Client_Product> clients = new ArrayList<>();

    @JsonIgnoreProperties(value = {"products"}, allowSetters = true)
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "id_supplier", nullable = false)
    private Supplier supplier;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Client_Product> getClients() {
        return clients;
    }

    public void setClients(List<Client_Product> clients) {
        this.clients = clients;
        for (Client_Product c : clients) {
            c.setProduct(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
        List<Product> products = this.supplier.getProducts();
        if (products == null) {
            products = new ArrayList<>();
        }
        if (!products.contains(this)) {
            products.add(this);
        }
    }
}
