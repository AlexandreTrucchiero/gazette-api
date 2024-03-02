package com.alexandretrucchiero.gazetteapi.domain.notification.doubles;


import com.alexandretrucchiero.gazetteapi.domain.notification.model.Abonne;
import com.alexandretrucchiero.gazetteapi.domain.notification.spi.AbonneRepository;

import java.util.List;

public class AbonneRepositoryDouble implements AbonneRepository {
    @Override
    public List<Abonne> recupererAbonnesAuxTags(List<String> tags) {
        return List.of(new Abonne("example@example.com"));
    }
}
