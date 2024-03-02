package com.alexandretrucchiero.gazetteapi.domain.adapter.doubles;

import com.alexandretrucchiero.gazetteapi.domain.notification.Notificateur;
import com.alexandretrucchiero.gazetteapi.domain.notification.model.PublicationANotifier;
import com.alexandretrucchiero.gazetteapi.domain.notification.spi.AbonneRepository;
import com.alexandretrucchiero.gazetteapi.domain.notification.spi.ExpediteurDeMail;

public class NotificateurDouble extends Notificateur {
    public PublicationANotifier publicationANotifier;

    public NotificateurDouble(ExpediteurDeMail expediteurDeMail, AbonneRepository abonneRepository) {
        super(expediteurDeMail, abonneRepository);
    }

    @Override
    public void notifier(PublicationANotifier publicationANotifier) {
        this.publicationANotifier = publicationANotifier;
    }
}
