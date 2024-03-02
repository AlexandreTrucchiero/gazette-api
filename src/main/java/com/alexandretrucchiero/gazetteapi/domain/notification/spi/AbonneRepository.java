package com.alexandretrucchiero.gazetteapi.domain.notification.spi;


import com.alexandretrucchiero.gazetteapi.domain.notification.model.Abonne;

import java.util.List;

public interface AbonneRepository {

    List<Abonne> recupererAbonnesAuxTags(List<String> tags);
}
