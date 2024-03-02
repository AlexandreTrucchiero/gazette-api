package com.alexandretrucchiero.gazetteapi.gateway.message.model;


import com.alexandretrucchiero.gazetteapi.domain.message.model.EtatMessage;

import java.util.Arrays;

public enum EtatMessageDB {
    PUBLIE(EtatMessage.PUBLIE),
    BROUILLON(EtatMessage.BROUILLON);

    private final EtatMessage etatMessage;

    EtatMessageDB(EtatMessage etatMessage) {
        this.etatMessage = etatMessage;
    }

    static EtatMessageDB mapEtatToString(EtatMessage etat) {
        return Arrays.stream(EtatMessageDB.values())
                .filter(etatMessageDB -> etatMessageDB.getEtatMessage().equals(etat))
                .findFirst()
                .orElseThrow();
    }

    public EtatMessage getEtatMessage() {
        return etatMessage;
    }
}
