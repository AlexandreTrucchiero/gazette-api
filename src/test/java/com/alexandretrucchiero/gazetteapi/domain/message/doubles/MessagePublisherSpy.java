package com.alexandretrucchiero.gazetteapi.domain.message.doubles;


import com.alexandretrucchiero.gazetteapi.domain.message.model.Message;
import com.alexandretrucchiero.gazetteapi.domain.message.spi.MessagePublisher;

// Spy: A Stub that also record some information based on how they were called
public class MessagePublisherSpy implements MessagePublisher {
    private Message messagePublie;

    @Override
    public void publier(Message message) {
        messagePublie = message;
    }

    public Message getMessagePublie() {
        return messagePublie;
    }
}
