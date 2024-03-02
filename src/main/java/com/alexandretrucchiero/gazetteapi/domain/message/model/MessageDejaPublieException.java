package com.alexandretrucchiero.gazetteapi.domain.message.model;

public class MessageDejaPublieException extends RuntimeException {

    private static final String MESSAGE_D_ERREUR = "Vous ne pouvez pas modifier un message déjà publié";

    public MessageDejaPublieException() {
        super(MESSAGE_D_ERREUR);
    }
}
