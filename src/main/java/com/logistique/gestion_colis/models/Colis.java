package com.logistique.gestion_colis.models;


import com.logistique.gestion_colis.models.enums.StatutColis;
import com.logistique.gestion_colis.models.enums.TypeColis;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collation = "colis")
public abstract class Colis {

    @Id
    private String id;

    private TypeColis type;
    private double poids;
    private String adresse_destination;
    private StatutColis statut;


    public Colis(TypeColis type, double poids, String adresse_destination) {
        this.type = type;
        this.poids = poids;
        this.adresse_destination = adresse_destination;
        this.statut = StatutColis.EN_ATTENTE;
    }


}
