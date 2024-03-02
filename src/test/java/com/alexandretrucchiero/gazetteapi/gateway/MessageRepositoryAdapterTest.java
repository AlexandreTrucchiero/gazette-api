package com.alexandretrucchiero.gazetteapi.gateway;

import com.alexandretrucchiero.gazetteapi.domain.message.model.Message;
import com.alexandretrucchiero.gazetteapi.gateway.message.MessageRepositoryAdapter;
import com.alexandretrucchiero.gazetteapi.gateway.message.MessageSpringRepository;
import com.alexandretrucchiero.gazetteapi.gateway.message.model.EtatMessageDB;
import com.alexandretrucchiero.gazetteapi.gateway.message.model.MessageDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import static com.alexandretrucchiero.gazetteapi.domain.message.model.EtatMessage.BROUILLON;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@SpringBootTest
class MessageRepositoryAdapterTest {
    private static final String TITRE = "Titre";
    private static final String DESCRIPTION = "Description";
    private static final List<String> TAGS = List.of("java", "quickie");
    private static final UUID ID = UUID.randomUUID();
    private static final OffsetDateTime NOW = OffsetDateTime.now().truncatedTo(ChronoUnit.SECONDS);

    @Autowired
    private MessageSpringRepository messageSpringRepository;

    @Autowired
    private MessageRepositoryAdapter messageRepositoryAdapter;

    @BeforeEach
    void setUp() {
        messageSpringRepository.deleteAll();
    }

    @Test
    void doit_persister_un_message() {
        // Given
        Message message = message();

        // When
        messageRepositoryAdapter.persister(message);

        // Then
        MessageDB messageEnBase = messageSpringRepository.findById(ID).orElseThrow();
        assertThat(messageEnBase.getId()).isEqualTo(ID);
        assertThat(messageEnBase.getTitre()).isEqualTo(TITRE);
        assertThat(messageEnBase.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(messageEnBase.getTags().containsAll(TAGS)).isTrue();
        assertThat(messageEnBase.getEtat()).isEqualTo(EtatMessageDB.BROUILLON);
        assertThat(messageEnBase.getDateCreation()).isEqualTo(NOW);
    }

    @Test
    void doit_recuperer_un_message() {
        // Given
        MessageDB messageDB = messageDB();
        messageSpringRepository.save(messageDB);

        // When
        Message message = messageRepositoryAdapter.getMessage(ID);

        // Then
        assertThat(message.getId()).isEqualTo(ID);
        assertThat(message.getTitre()).isEqualTo(TITRE);
        assertThat(message.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(message.getTags().containsAll(TAGS)).isTrue();
        assertThat(message.getEtat()).isEqualTo(BROUILLON);
        assertThat(message.getDateCreation()).isEqualTo(NOW);
    }

    @Test
    void doit_supprimer_un_message() {
        // Given
        MessageDB messageDB = messageDB();
        messageSpringRepository.save(messageDB);

        // When
        messageRepositoryAdapter.supprimer(ID);

        // Then
        assertThat(messageSpringRepository.findById(ID).isPresent()).isFalse();
    }

    private Message message() {
        Message message = new Message(ID);
        message.setTitre(TITRE);
        message.setDescription(DESCRIPTION);
        message.setTags(TAGS);
        message.setEtat(BROUILLON);
        message.setDateCreation(NOW);
        return message;
    }

    private MessageDB messageDB() {
        MessageDB messageDB = new MessageDB();
        messageDB.setId(ID);
        messageDB.setTitre(TITRE);
        messageDB.setDescription(DESCRIPTION);
        messageDB.setTags(TAGS);
        messageDB.setEtat(EtatMessageDB.BROUILLON);
        messageDB.setDateCreation(NOW);
        return messageDB;
    }
}
