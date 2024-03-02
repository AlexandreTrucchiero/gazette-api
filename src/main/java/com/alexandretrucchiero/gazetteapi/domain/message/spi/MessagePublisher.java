package com.alexandretrucchiero.gazetteapi.domain.message.spi;


import com.alexandretrucchiero.gazetteapi.domain.message.model.Message;

public interface MessagePublisher {
    void publier(Message message);
}
