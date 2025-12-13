package com.logistique.gestion_colis.dto;

import com.logistique.gestion_colis.models.enums.StatutColis;
import com.logistique.gestion_colis.models.enums.TypeColis;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ColisResponse {
    private String id;
    private TypeColis type;
    private double poids;
    private String adresseDestination;
    private StatutColis statut;
    private String transporteurId;

    private String instructionsManutention;
    private Double temperatureMin;
    private Double temperatureMax;
}