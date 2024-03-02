package com.alexandretrucchiero.gazetteapi.domain.message.spi;


import com.alexandretrucchiero.gazetteapi.domain.message.model.Message;

import java.util.UUID;

public interface MessageRepository {

    void persister(Message message);

    Message getMessage(UUID id);

    void supprimer(UUID idMessage);
}
