package com.alexandretrucchiero.gazetteapi.domain.notification.doubles;


import com.alexandretrucchiero.gazetteapi.domain.notification.spi.ExpediteurDeMail;

public class ExpediteurDeMailDouble implements ExpediteurDeMail {
    public String messageEnvoye;

    @Override
    public void envoyerMail(String mail, String message) {
        messageEnvoye = message;
    }
}
