package com.firstapp.crud.repository;

import com.firstapp.crud.model.Client;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends ListCrudRepository<Client, String> {
    Optional<Client> findByEmail(String email);
}
