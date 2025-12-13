package com.logistique.gestion_colis.dto;

import com.logistique.gestion_colis.models.enums.TypeColis;
import lombok.Data;

@Data
public class ColisRequest {
    private TypeColis type;
    private double poids;
    private String adresseDestination;

    private String instructionsManutention;
    private Double temperatureMin;
    private Double temperatureMax;
}