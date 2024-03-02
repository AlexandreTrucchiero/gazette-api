package com.alexandretrucchiero.gazetteapi.domain.message;


import com.alexandretrucchiero.gazetteapi.domain.message.model.EtatMessage;
import com.alexandretrucchiero.gazetteapi.domain.message.model.Message;
import com.alexandretrucchiero.gazetteapi.domain.message.model.MessageDejaPublieException;
import com.alexandretrucchiero.gazetteapi.domain.message.spi.MessagePublisher;
import com.alexandretrucchiero.gazetteapi.domain.message.spi.MessageRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessagePublisher messagePublisher;
    private final DateService dateService;

    public UUID creer(Message message) {
        message.setDateCreation(dateService.now());
        messageRepository.persister(message);
        return message.getId();
    }

    public void valider(UUID id) {
        Message message = messageRepository.getMessage(id);
        message.setEtat(EtatMessage.PUBLIE);
        messageRepository.persister(message);
        messagePublisher.publier(message);
    }

    /**
     * @throws MessageDejaPublieException si le message a déjà été publié
     */
    public void modifier(Message message) throws MessageDejaPublieException {
        exceptionSiMessageDejaPublie(message.getId());
        messageRepository.persister(message);
    }

    /**
     * @throws MessageDejaPublieException si le message a déjà été publié
     */
    public void supprimer(UUID idMessage) throws MessageDejaPublieException {
        exceptionSiMessageDejaPublie(idMessage);
        messageRepository.supprimer(idMessage);
    }

    private void exceptionSiMessageDejaPublie(UUID idMessage) {
        if (messageRepository.getMessage(idMessage).getEtat() == EtatMessage.PUBLIE)
            throw new MessageDejaPublieException();
    }

}
