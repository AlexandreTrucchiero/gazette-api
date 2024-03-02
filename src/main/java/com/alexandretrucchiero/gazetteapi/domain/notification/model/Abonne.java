package com.alexandretrucchiero.gazetteapi.domain.notification.model;

public class Abonne {

    private final String mail;

    public Abonne(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }
}
