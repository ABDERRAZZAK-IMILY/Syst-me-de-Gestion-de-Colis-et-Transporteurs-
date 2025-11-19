package com.logistique.gestion_colis.repository;

import com.logistique.gestion_colis.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByLogin(String login);

    boolean existsByLogin(String login);
}