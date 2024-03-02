package com.alexandretrucchiero.gazetteapi.gateway.abonne;

import com.alexandretrucchiero.gazetteapi.gateway.abonne.model.AbonneDB;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface AbonneSpringRepository extends CrudRepository<AbonneDB, UUID> {
    List<AbonneDB> findAllByTags(String tags);
}
