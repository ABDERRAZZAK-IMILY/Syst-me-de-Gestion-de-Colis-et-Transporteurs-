package com.logistique.gestion_colis.service.impl;

import com.logistique.gestion_colis.dto.ColisRequest;
import com.logistique.gestion_colis.dto.ColisResponse;
import com.logistique.gestion_colis.mapper.ColisMapper;
import com.logistique.gestion_colis.models.Colis;
import com.logistique.gestion_colis.models.Transporteur;
import com.logistique.gestion_colis.models.enums.StatutColis;
import com.logistique.gestion_colis.models.enums.StatutTransporteur;
import com.logistique.gestion_colis.repository.ColisRepository;
import com.logistique.gestion_colis.repository.UserRepository;
import com.logistique.gestion_colis.service.ColisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ColisServiceImpl implements ColisService {

    private final ColisRepository colisRepository;
    private final UserRepository userRepository;
    private final ColisMapper colisMapper;

    @Override
    public ColisResponse createColis(ColisRequest request) {
        Colis colis = colisMapper.toEntity(request);
        colis.setStatut(StatutColis.EN_ATTENTE);
        Colis savedColis = colisRepository.save(colis);
        return colisMapper.toResponse(savedColis);
    }

    @Override
    public Page<ColisResponse> getAllColis(String destination, Pageable pageable) {
        Page<Colis> colisPage;
        if (destination != null && !destination.isEmpty()) {
            colisPage = colisRepository.findByAdresseDestinationContaining(destination, pageable);
        } else {
            colisPage = colisRepository.findAll(pageable);
        }
        return colisPage.map(colisMapper::toResponse);
    }

    @Override
    @Transactional
    public ColisResponse assignColisToTransporteur(String colisId, String transporteurId) {
        Colis colis = colisRepository.findById(colisId)
                .orElseThrow(() -> new RuntimeException("Colis introuvable"));

        Transporteur transporteur = (Transporteur) userRepository.findById(transporteurId)
                .orElseThrow(() -> new RuntimeException("Transporteur introuvable"));

        if (!colis.getType().name().equals(transporteur.getSpecialite().name())) {
            throw new RuntimeException("Specialite incompatible");
        }

        if (transporteur.getStatut() != StatutTransporteur.DISPONIBLE) {
            throw new RuntimeException("Le transporteur ne  pas disponible");
        }

        colis.setTransporteurId(transporteurId);
        colis.setStatut(StatutColis.EN_TRANSIT);

        transporteur.setStatut(StatutTransporteur.EN_LIVRAISON);
        userRepository.save(transporteur);

        return colisMapper.toResponse(colisRepository.save(colis));
    }
    @Override
    public ColisResponse updateStatut(String colisId, StatutColis newStatut) {
        Colis colis = colisRepository.findById(colisId)
                .orElseThrow(() -> new RuntimeException("Colis introuvable"));
        colis.setStatut(newStatut);
        return colisMapper.toResponse(colisRepository.save(colis));
    }

    @Override
    public Page<ColisResponse> getColisByTransporteur(String transporteurId, Pageable pageable) {
        return colisRepository.findByTransporteurId(transporteurId, pageable)
                .map(colisMapper::toResponse);
    }

    @Override
    public void deleteColis(String id) {
        colisRepository.deleteById(id);
    }

    @Override
    public ColisResponse updateColis(String id, ColisRequest request) {
        Colis existingColis = colisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Colis introuvable"));

        existingColis.setPoids(request.getPoids());
        existingColis.setAdresseDestination(request.getAdresseDestination());

        return colisMapper.toResponse(colisRepository.save(existingColis));
    }
}