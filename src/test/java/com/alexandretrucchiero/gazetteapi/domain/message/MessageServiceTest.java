package com.alexandretrucchiero.gazetteapi.domain.message;


import com.alexandretrucchiero.gazetteapi.domain.message.doubles.DateServiceStub;
import com.alexandretrucchiero.gazetteapi.domain.message.doubles.MessagePublisherSpy;
import com.alexandretrucchiero.gazetteapi.domain.message.doubles.MessageRepositorySpy;
import com.alexandretrucchiero.gazetteapi.domain.message.model.EtatMessage;
import com.alexandretrucchiero.gazetteapi.domain.message.model.Message;
import com.alexandretrucchiero.gazetteapi.domain.message.model.MessageDejaPublieException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static com.alexandretrucchiero.gazetteapi.domain.message.doubles.DateServiceStub.NOW;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MessageServiceTest {

    private static final String TITRE = "Titre";
    private static final String DESCRIPTION = "Description";
    private static final List<String> TAGS = List.of("java", "quickie");
    private static final String NOUVEAU_TITRE = "Nouveau titre";
    private static final String NOUVELLE_DESCRIPTION = "Nouvelle description";
    private static final List<String> NOUVEAUX_TAGS = List.of("js", "svelte");
    private static final String MESSAGE_ERREUR_MODIFICATION_MESSAGE_DEJA_PUBLIE = "Vous ne pouvez pas modifier un message déjà publié";
    private final UUID idMessagePersiste = UUID.randomUUID();
    private MessageRepositorySpy messageRepository;
    private MessagePublisherSpy messagePublisher;

    private MessageService messageService;

    @BeforeEach
    void setUp() {
        messageRepository = new MessageRepositorySpy();
        messagePublisher = new MessagePublisherSpy();
        messageService = new MessageService(messageRepository, messagePublisher, new DateServiceStub());
    }

    @Test
    void doit_creer_un_message() {
        // Given
        Message message = new Message();
        message.setTitre(TITRE);
        message.setDescription(DESCRIPTION);
        message.setTags(TAGS);

        // When
        messageService.creer(message);

        // Then
        Message messagePersiste = messageRepository.getMessagePersiste();
        assertThat(messagePersiste).isEqualTo(message);
        assertThat(messagePersiste.getId()).isNotNull();
        assertThat(messagePersiste.getDateCreation()).isEqualTo(NOW);
        assertThat(messagePersiste.getEtat()).isEqualTo(EtatMessage.BROUILLON);
    }

    @Test
    void doit_valider_un_message() {
        // Given
        UUID idMessage = UUID.randomUUID();
        Message message = new Message(idMessage);
        message.setTitre(TITRE);
        message.setDescription(DESCRIPTION);
        message.setTags(TAGS);
        messageRepository.setStubMessage(message);

        // When
        messageService.valider(idMessage);

        // Then
        assertThat(messageRepository.getMessagePersiste()).isEqualTo(message);
        Message messagePublie = messagePublisher.getMessagePublie();
        assertThat(messagePublie).isEqualTo(message);
        assertThat(messagePublie.getEtat()).isEqualTo(EtatMessage.PUBLIE);
    }

    @Test
    void doit_modifier_un_brouillon_de_message() {
        // Given
        stubMessageRepositoryAvecUnMessage(false);
        Message messageModifie = new Message(idMessagePersiste);
        messageModifie.setTitre(NOUVEAU_TITRE);
        messageModifie.setDescription(NOUVELLE_DESCRIPTION);
        messageModifie.setTags(NOUVEAUX_TAGS);

        // When
        messageService.modifier(messageModifie);

        // Then
        Message messagePersiste = messageRepository.getMessagePersiste();
        assertThat(messagePersiste).isEqualTo(messageModifie);
        // On ne veut pas que la modification redéfinisse la date de création
        assertThat(messagePersiste.getDateCreation()).isNull();
        assertThat(messagePersiste.getEtat()).isEqualTo(EtatMessage.BROUILLON);
    }

    @Test
    void doit_remonter_une_erreur_si_modification_de_message_publie() {
        // Given
        stubMessageRepositoryAvecUnMessage(true);
        Message messageModifie = new Message(idMessagePersiste);
        messageModifie.setTitre(NOUVEAU_TITRE);
        messageModifie.setDescription(NOUVELLE_DESCRIPTION);
        messageModifie.setTags(NOUVEAUX_TAGS);

        // Then
        assertThatThrownBy(() -> messageService.modifier(messageModifie))
                .isInstanceOf(MessageDejaPublieException.class)
                .hasMessage(MESSAGE_ERREUR_MODIFICATION_MESSAGE_DEJA_PUBLIE);
    }

    @Test
    void doit_supprimer_un_message() {
        // Given
        stubMessageRepositoryAvecUnMessage(false);

        // When
        messageService.supprimer(idMessagePersiste);

        // Then
        assertThat(messageRepository.getIdMessageSupprime()).isEqualTo(idMessagePersiste);
    }

    @Test
    void doit_remonter_une_erreur_si_suppression_de_message_publie() {
        // Given
        stubMessageRepositoryAvecUnMessage(true);

        // Then
        assertThatThrownBy(() -> messageService.supprimer(idMessagePersiste))
                .isInstanceOf(MessageDejaPublieException.class)
                .hasMessage(MESSAGE_ERREUR_MODIFICATION_MESSAGE_DEJA_PUBLIE);
    }

    private void stubMessageRepositoryAvecUnMessage(boolean publie) {
        Message messagePersiste = new Message(idMessagePersiste);
        messagePersiste.setTitre(TITRE);
        messagePersiste.setDescription(DESCRIPTION);
        messagePersiste.setTags(TAGS);
        if (publie)
            messagePersiste.setEtat(EtatMessage.PUBLIE);
        messageRepository.setStubMessage(messagePersiste);
    }
}
