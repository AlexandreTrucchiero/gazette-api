package com.alexandretrucchiero.gazetteapi.domain.message.doubles;

import com.alexandretrucchiero.gazetteapi.domain.message.model.Message;
import com.alexandretrucchiero.gazetteapi.domain.message.spi.MessageRepository;

import java.util.UUID;

// Spy: A Stub that also record some information based on how they were called
public class MessageRepositorySpy implements MessageRepository {
    private Message messagePersiste;
    private Message messageStub;
    private UUID idMessageSupprime;

    @Override
    public void persister(Message message) {
        messagePersiste = message;
    }

    @Override
    public Message getMessage(UUID id) {
        return messageStub;
    }

    @Override
    public void supprimer(UUID idMessage) {
        idMessageSupprime = idMessage;
    }

    public Message getMessagePersiste() {
        return messagePersiste;
    }

    public void setStubMessage(Message message) {
        messageStub = message;
    }

    public UUID getIdMessageSupprime() {
        return idMessageSupprime;
    }
}
