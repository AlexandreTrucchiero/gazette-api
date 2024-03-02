package com.alexandretrucchiero.gazetteapi.gateway.abonne.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class AbonneDB {
    @Id
    private UUID id;
    private String mail;
    private String tags; // for the search
}
