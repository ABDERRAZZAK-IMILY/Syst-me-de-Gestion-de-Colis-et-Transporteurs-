package com.logistique.gestion_colis.models;


import com.logistique.gestion_colis.models.enums.TypeColis;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
@EqualsAndHashCode(callSuper = true)
public class ColisStandard extends Colis {

    public ColisStandard(double poids, String adresse_destination) {
        super(TypeColis.STANDARD, poids, adresse_destination);
    }
}
