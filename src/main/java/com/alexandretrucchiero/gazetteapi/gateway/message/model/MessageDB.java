package com.alexandretrucchiero.gazetteapi.gateway.message.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class MessageDB {
    @Id
    private UUID id;
    private String titre;
    private String description;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> tags = new ArrayList<>();
    private OffsetDateTime dateCreation;
    private EtatMessageDB etat;
}
