package com.alexandretrucchiero.gazetteapi.domain.notification;

import com.alexandretrucchiero.gazetteapi.domain.notification.doubles.AbonneRepositoryDouble;
import com.alexandretrucchiero.gazetteapi.domain.notification.doubles.ExpediteurDeMailDouble;
import com.alexandretrucchiero.gazetteapi.domain.notification.model.PublicationANotifier;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotificateurTest {

    private static final String TITRE = "News Java Spring";

    @Test
    void doit_envoyer_un_mail_aux_utilisateurs_abonnes() {
        ExpediteurDeMailDouble expediteurMail = new ExpediteurDeMailDouble();
        Notificateur notificateur = new Notificateur(expediteurMail, new AbonneRepositoryDouble());
        List<String> tags = List.of("java", "spring");

        notificateur.notifier(new PublicationANotifier(TITRE, tags));

        assertEquals("Un nouveau message " + TITRE + " a été publié.", expediteurMail.messageEnvoye);
    }

}
