package com.alexandretrucchiero.gazetteapi.gateway.message;

import com.alexandretrucchiero.gazetteapi.domain.message.model.Message;
import com.alexandretrucchiero.gazetteapi.domain.message.spi.MessageRepository;
import com.alexandretrucchiero.gazetteapi.gateway.message.model.MessageDBMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class MessageRepositoryAdapter implements MessageRepository {

    @Autowired
    private MessageSpringRepository messageSpringRepository;

    @Autowired
    private MessageDBMapper messageDBMapper;

    @Override
    public void persister(Message message) {
        messageSpringRepository.save(messageDBMapper.mapToMessageDB(message));
    }

    @Override
    public Message getMessage(UUID id) {
        return messageDBMapper.mapToMessage(messageSpringRepository.findById(id).orElseThrow());
    }

    @Override
    public void supprimer(UUID idMessage) {
        messageSpringRepository.deleteById(idMessage);
    }
}
