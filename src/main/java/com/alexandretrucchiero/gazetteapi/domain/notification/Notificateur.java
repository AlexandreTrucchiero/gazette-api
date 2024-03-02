package com.alexandretrucchiero.gazetteapi.domain.notification;

import com.alexandretrucchiero.gazetteapi.domain.notification.model.Abonne;
import com.alexandretrucchiero.gazetteapi.domain.notification.model.PublicationANotifier;
import com.alexandretrucchiero.gazetteapi.domain.notification.spi.AbonneRepository;
import com.alexandretrucchiero.gazetteapi.domain.notification.spi.ExpediteurDeMail;

import java.util.function.Consumer;

public class Notificateur {

    private final ExpediteurDeMail expediteurDeMail;
    private final AbonneRepository abonneRepository;

    public Notificateur(ExpediteurDeMail expediteurDeMail, AbonneRepository abonneRepository) {
        this.expediteurDeMail = expediteurDeMail;
        this.abonneRepository = abonneRepository;
    }

    public void notifier(PublicationANotifier publicationANotifier) {
        abonneRepository.recupererAbonnesAuxTags(publicationANotifier.getTags())
                .forEach(envoyerMail(publicationANotifier));
    }

    private Consumer<Abonne> envoyerMail(PublicationANotifier publicationANotifier) {
        return abonne -> expediteurDeMail.envoyerMail(abonne.getMail(),
                "Un nouveau message " + publicationANotifier.getTitre() + " a été publié.");
    }
}
