package com.alexandretrucchiero.gazetteapi.gateway.message.model;

import com.alexandretrucchiero.gazetteapi.domain.message.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageDBMapper {

    public MessageDB mapToMessageDB(Message message) {
        MessageDB messageDB = new MessageDB();
        messageDB.setId(message.getId());
        messageDB.setTitre(message.getTitre());
        messageDB.setDescription(message.getDescription());
        message.getTags().forEach(tag -> messageDB.getTags().add(tag));
        messageDB.setDateCreation(message.getDateCreation());
        messageDB.setEtat(EtatMessageDB.mapEtatToString(message.getEtat()));
        return messageDB;
    }

    public Message mapToMessage(MessageDB messageDB) {
        Message message = new Message(messageDB.getId());
        message.setTitre(messageDB.getTitre());
        message.setDescription(messageDB.getDescription());
        log.info("Message DB{}", messageDB);
        messageDB.getTags().forEach(tag -> message.getTags().add(tag));
        message.setDateCreation(messageDB.getDateCreation());
        message.setEtat(messageDB.getEtat().getEtatMessage());
        return message;
    }

}
