package com.alexandretrucchiero.gazetteapi.controller;

import com.alexandretrucchiero.gazetteapi.api.PublicationApi;
import com.alexandretrucchiero.gazetteapi.api.data.Publication;
import com.alexandretrucchiero.gazetteapi.domain.message.MessageService;
import com.alexandretrucchiero.gazetteapi.domain.message.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class PublicationController implements PublicationApi {

    @Autowired
    private MessageService messageService;

    @Override
    public ResponseEntity<Publication> createPublication(Publication publication) {
        UUID idMessageCree = messageService.creer(mapToMessage(publication));
        publication.setId(idMessageCree.toString());
        return ResponseEntity.ok(publication);
    }

    @Override
    public ResponseEntity<Void> deletePublication(String id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Publication>> searchPublication(Boolean abonnement, String text) {
        return null;
    }

    @Override
    public ResponseEntity<Publication> updatePublication(String id, Publication publication) {
        return null;
    }

    @Override
    public ResponseEntity<Void> validatePublication(String id) {
        messageService.valider(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

    private Message mapToMessage(Publication publication) {
        Message message = new Message();
        message.setTitre(publication.getTitre());
        message.setDescription(publication.getDescription());
        publication.getTags().forEach(tag -> message.getTags().add(tag.getNom()));
        return message;
    }
}
