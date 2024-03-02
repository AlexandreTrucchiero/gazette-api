package com.alexandretrucchiero.gazetteapi.configuration;

import com.alexandretrucchiero.gazetteapi.domain.adapter.MessagePublisherAdapter;
import com.alexandretrucchiero.gazetteapi.domain.message.DateService;
import com.alexandretrucchiero.gazetteapi.domain.message.MessageService;
import com.alexandretrucchiero.gazetteapi.domain.message.spi.MessagePublisher;
import com.alexandretrucchiero.gazetteapi.domain.notification.Notificateur;
import com.alexandretrucchiero.gazetteapi.domain.notification.spi.ExpediteurDeMail;
import com.alexandretrucchiero.gazetteapi.gateway.abonne.AbonneRepositoryAdapter;
import com.alexandretrucchiero.gazetteapi.gateway.message.MessageRepositoryAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class PublicationConfiguration {

    @Bean
    public MessageService messageService(MessageRepositoryAdapter mra, MessagePublisher mp, DateService ds) {
        return new MessageService(mra, mp, ds);
    }

    @Bean
    public DateService dateService() {
        return new DateService();
    }

    @Bean
    public MessagePublisher messagePublisher(Notificateur notificateur) {
        return new MessagePublisherAdapter(notificateur);
    }

    @Bean
    public Notificateur notificateur(ExpediteurDeMail em, AbonneRepositoryAdapter ar) {
        return new Notificateur(em, ar);
    }

    @Bean
    public ExpediteurDeMail expediteurDeMail() {
        return (mail, message) -> log.info("Mail envoyé par le serveur {}, {}", mail, message);
    }

}
