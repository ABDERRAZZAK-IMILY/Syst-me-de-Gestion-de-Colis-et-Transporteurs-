package com.logistique.gestion_colis.dto;

import com.logistique.gestion_colis.models.enums.Specialite;
import com.logistique.gestion_colis.models.enums.StatutTransporteur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransporteurRequest {
    private String login;
    private String password;
    private Specialite specialite;
    private StatutTransporteur statut;
}