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
@EqualsAndHashCode(callSuper = true)
@Document
public class ColisFragile extends Colis {

    private String instructions_manutention;

    public ColisFragile(double poids, String adresse_destination, String instructions) {
        super(TypeColis.FRAGILE, poids, adresse_destination);
        this.instructions_manutention = instructions;
    }


}
