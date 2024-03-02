package com.alexandretrucchiero.gazetteapi.gateway.message;

import com.alexandretrucchiero.gazetteapi.gateway.message.model.MessageDB;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MessageSpringRepository extends CrudRepository<MessageDB, UUID> {
}
