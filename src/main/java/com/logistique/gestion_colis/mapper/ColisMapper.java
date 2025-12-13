package com.logistique.gestion_colis.mapper;

import com.logistique.gestion_colis.dto.ColisRequest;
import com.logistique.gestion_colis.dto.ColisResponse;
import com.logistique.gestion_colis.models.*;
import org.springframework.stereotype.Component;

@Component
public class ColisMapper {

    public Colis toEntity(ColisRequest request) {
        switch (request.getType()) {
            case FRAGILE:
                return new ColisFragile(
                        request.getPoids(),
                        request.getAdresseDestination(),
                        request.getInstructionsManutention()
                );
            case FRIGO:
                return new ColisFrigo(
                        request.getPoids(),
                        request.getAdresseDestination(),
                        request.getTemperatureMin(),
                        request.getTemperatureMax()
                );
            default: // STANDARD
                return new ColisStandard(
                        request.getPoids(),
                        request.getAdresseDestination()
                );
        }
    }

    public ColisResponse toResponse(Colis colis) {
        ColisResponse.ColisResponseBuilder builder = ColisResponse.builder()
                .id(colis.getId())
                .type(colis.getType())
                .poids(colis.getPoids())
                .adresseDestination(colis.getAdresseDestination())
                .statut(colis.getStatut())
                .transporteurId(colis.getTransporteurId());

        if (colis instanceof ColisFragile) {
            builder.instructionsManutention(((ColisFragile) colis).getInstructions_manutention());
        } else if (colis instanceof ColisFrigo) {
            builder.temperatureMin(((ColisFrigo) colis).getTemperature_min());
            builder.temperatureMax(((ColisFrigo) colis).getTemperature_max());
        }

        return builder.build();
    }
}