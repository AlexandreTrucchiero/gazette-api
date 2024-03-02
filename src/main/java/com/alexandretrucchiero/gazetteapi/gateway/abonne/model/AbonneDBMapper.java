package com.alexandretrucchiero.gazetteapi.gateway.abonne.model;

import com.alexandretrucchiero.gazetteapi.domain.notification.model.Abonne;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AbonneDBMapper {

    public AbonneDB mapToAbonneDB(Abonne abonne) {
        AbonneDB abonneDB = new AbonneDB();
        abonneDB.setId(UUID.randomUUID());
        abonneDB.setMail(abonne.getMail());
        return abonneDB;
    }

    public Abonne mapToAbonne(AbonneDB abonneDB) {
        return new Abonne(abonneDB.getMail());
    }

}
