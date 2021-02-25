package com.luque.demoacdat.repositories;

import com.luque.demoacdat.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "SELECT * FROM client WHERE name LIKE %?1%", nativeQuery = true)
    List<Client> getClientByName(String name);


}
