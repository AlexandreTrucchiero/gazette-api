package com.alexandretrucchiero.gazetteapi.controller;


import com.alexandretrucchiero.gazetteapi.domain.message.DateService;
import com.alexandretrucchiero.gazetteapi.domain.message.MessageService;
import com.alexandretrucchiero.gazetteapi.domain.message.model.Message;
import com.alexandretrucchiero.gazetteapi.domain.message.spi.MessagePublisher;
import com.alexandretrucchiero.gazetteapi.domain.message.spi.MessageRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Cette doublure de test est injectée à la place de MessageService grâce à @Primary
 * Tous les beans seront construits mais celle annoté avec Primary sera injectée
 * en cas de plusieurs candidats
 */
@Service
@Primary
public class MessageServiceDouble extends MessageService {

    private UUID idMessageCree;
    private Message messageCree;

    public MessageServiceDouble(MessageRepository messageRepository, MessagePublisher messagePublisher,
                                DateService dateService) {
        super(messageRepository, messagePublisher, dateService);
    }

    @Override
    public UUID creer(Message message) {
        this.messageCree = message;
        return idMessageCree;
    }

    @Override
    public void modifier(Message message) {

    }

    @Override
    public void valider(UUID id) {

    }

    @Override
    public void supprimer(UUID idMessage) {

    }

    public void setIdMessageCree(UUID idMessageCree) {
        this.idMessageCree = idMessageCree;
    }

    public Message getMessageCree() {
        return messageCree;
    }
}
