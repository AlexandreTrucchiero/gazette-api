package com.alexandretrucchiero.gazetteapi.gateway.abonne;

import com.alexandretrucchiero.gazetteapi.domain.notification.model.Abonne;
import com.alexandretrucchiero.gazetteapi.domain.notification.spi.AbonneRepository;
import com.alexandretrucchiero.gazetteapi.gateway.abonne.model.AbonneDBMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AbonneRepositoryAdapter implements AbonneRepository {

    @Autowired
    private AbonneSpringRepository abonneSpringRepository;

    @Autowired
    private AbonneDBMapper abonneDBMapper;

    @Override
    public List<Abonne> recupererAbonnesAuxTags(List<String> tags) {
        return abonneSpringRepository
                .findAllByTags(String.join(",", tags))
                .stream()
                .map(abonneDB -> abonneDBMapper.mapToAbonne(abonneDB))
                .collect(Collectors.toList());
    }
}
