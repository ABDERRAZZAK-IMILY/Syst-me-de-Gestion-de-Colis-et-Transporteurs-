package com.logistique.gestion_colis.service;

import com.logistique.gestion_colis.dto.ColisRequest;
import com.logistique.gestion_colis.dto.ColisResponse;
import com.logistique.gestion_colis.models.enums.StatutColis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ColisService {
    ColisResponse createColis(ColisRequest request);
    Page<ColisResponse> getAllColis(String destination, Pageable pageable);
    ColisResponse assignColisToTransporteur(String colisId, String transporteurId);
    ColisResponse updateStatut(String colisId, StatutColis newStatut);
    Page<ColisResponse> getColisByTransporteur(String transporteurId, Pageable pageable);
    ColisResponse updateColis(String id, ColisRequest request);
    void deleteColis(String id);
}