package com.alexandretrucchiero.gazetteapi.domain.message.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
public class Message {
    private final UUID id;
    private String titre;
    private String description;
    private List<String> tags = new ArrayList<>();
    private OffsetDateTime dateCreation;
    private EtatMessage etat;

    public Message() {
        this.id = UUID.randomUUID();
        this.etat = EtatMessage.BROUILLON;
    }

    public Message(UUID id) {
        this.id = id;
        this.etat = EtatMessage.BROUILLON;
    }

}
