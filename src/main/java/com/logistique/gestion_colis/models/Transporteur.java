package com.logistique.gestion_colis.models;


import com.logistique.gestion_colis.models.enums.Role;
import com.logistique.gestion_colis.models.enums.Specialite;
import com.logistique.gestion_colis.models.enums.StatutTransporteur;
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
public class Transporteur extends  User {

    private StatutTransporteur statut;
    private Specialite specialite;

    public Transporteur(String login, String password, boolean active, StatutTransporteur statut, Specialite specialite) {
        super(null, login, password, Role.TRANSPORTEUR, active);
        this.statut = statut;
        this.specialite = specialite;
    }

}
