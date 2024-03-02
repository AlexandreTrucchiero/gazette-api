package com.alexandretrucchiero.gazetteapi.domain.notification.spi;

public interface ExpediteurDeMail {

    void envoyerMail(String mail, String message);
}
