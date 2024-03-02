package com.alexandretrucchiero.gazetteapi.domain.adapter;


import com.alexandretrucchiero.gazetteapi.domain.message.model.Message;
import com.alexandretrucchiero.gazetteapi.domain.message.spi.MessagePublisher;
import com.alexandretrucchiero.gazetteapi.domain.notification.Notificateur;
import com.alexandretrucchiero.gazetteapi.domain.notification.model.PublicationANotifier;

public class MessagePublisherAdapter implements MessagePublisher {
    private final Notificateur notificateur;

    public MessagePublisherAdapter(Notificateur notificateur) {
        this.notificateur = notificateur;
    }

    @Override
    public void publier(Message message) {
        notificateur.notifier(new PublicationANotifier(message.getTitre(), message.getTags()));
    }
}
