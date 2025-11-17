package com.logistique.gestion_colis.models;

import com.logistique.gestion_colis.models.enums.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public abstract class User {

    @Id
    private String id;

    private String login;
    private String password;
    private Role role;
    private boolean active;

}