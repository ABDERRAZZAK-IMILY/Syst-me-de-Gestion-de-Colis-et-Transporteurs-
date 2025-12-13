package com.logistique.gestion_colis.repository;

import com.logistique.gestion_colis.models.Colis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ColisRepository extends MongoRepository<Colis, String> {

    Page<Colis> findByAdresseDestinationContaining(String adresseDestination, Pageable pageable);

    Page<Colis> findByTransporteurId(String transporteurId, Pageable pageable);
}