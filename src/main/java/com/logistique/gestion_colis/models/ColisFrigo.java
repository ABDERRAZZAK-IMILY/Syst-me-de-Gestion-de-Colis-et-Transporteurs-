package com.logistique.gestion_colis.models;


import com.logistique.gestion_colis.models.enums.TypeColis;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
@EqualsAndHashCode(callSuper = true)
public class ColisFrigo extends Colis{
    private double temperature_min;
    private double temperature_max;

    public ColisFrigo(double poids, String adresse_destination, double tempMin, double tempMax) {
        super(TypeColis.FRIGO, poids, adresse_destination);
        this.temperature_min = tempMin;
        this.temperature_max = tempMax;
    }
}
