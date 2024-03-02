package com.alexandretrucchiero.gazetteapi.domain.adapter;


import com.alexandretrucchiero.gazetteapi.domain.adapter.doubles.NotificateurDouble;
import com.alexandretrucchiero.gazetteapi.domain.message.model.Message;
import com.alexandretrucchiero.gazetteapi.domain.message.spi.MessagePublisher;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessagePublisherAdapterTest {
    @Test
    void doit_notifier_un_message_avec_un_notificateur() {
        NotificateurDouble notificateur = new NotificateurDouble(null, null);
        MessagePublisher messagePublisher = new MessagePublisherAdapter(notificateur);
        Message message = new Message(UUID.randomUUID());
        message.setTitre("News Java Spring");
        message.setTags(List.of("java", "spring"));

        messagePublisher.publier(message);

        assertEquals(message.getTitre(), notificateur.publicationANotifier.getTitre());
        assertEquals(message.getTags(), notificateur.publicationANotifier.getTags());
    }

}
