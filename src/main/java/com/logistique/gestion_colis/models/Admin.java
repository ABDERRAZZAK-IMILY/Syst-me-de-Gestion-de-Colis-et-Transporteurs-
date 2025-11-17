package com.logistique.gestion_colis.models;


import com.logistique.gestion_colis.models.enums.Role;
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
public class Admin extends User {

    public Admin(String login, String password, boolean active) {
        super(null, login, password, Role.ADMIN, active);
    }

}
