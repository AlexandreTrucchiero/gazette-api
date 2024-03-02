package com.alexandretrucchiero.gazetteapi.controller;

import com.alexandretrucchiero.gazetteapi.api.data.Publication;
import com.alexandretrucchiero.gazetteapi.api.data.Tag;
import com.alexandretrucchiero.gazetteapi.domain.message.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class PublicationControllerTest {
    private static final String API_BASE_URL = "http://localhost:8080/api/publication/";
    private static final String TITRE = "Message 1";
    private static final String DESCRIPTION = "Description message 1";
    private static final String TAG1_NOM = "java";
    private static final String TAG2_NOM = "quickie";
    private static final String ID_PUBLICATION = "9ab5ff2c-c44e-4ea6-8bd1-d29cebd2b9b2";

    private ObjectMapper objectMapper;
    private UUID idMessageCree;
    private HttpClient httpClient;

    @Autowired
    private MessageServiceDouble messageServiceDouble;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        idMessageCree = UUID.randomUUID();
        messageServiceDouble.setIdMessageCree(idMessageCree);
        httpClient = HttpClient.newBuilder().build();
    }

    @Test
    void createPublication() throws Exception {
        Publication publication = publication();

        HttpResponse<String> response = postPublication(publication);

        assertThat(response.statusCode()).isEqualTo(200);

        Publication publicationRecuperee = objectMapper.readValue(response.body(), Publication.class);
        assertThat(publicationRecuperee.getId()).isEqualTo(idMessageCree.toString());
        assertThat(publicationRecuperee.getTitre()).isEqualTo(TITRE);
        assertThat(publicationRecuperee.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(publicationRecuperee.getTags().stream().map(Tag::getNom).collect(Collectors.toList())
                .containsAll(List.of(TAG1_NOM, TAG2_NOM))).isTrue();

        Message messageCree = messageServiceDouble.getMessageCree();
        assertThat(messageCree.getTitre()).isEqualTo(TITRE);
        assertThat(messageCree.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(messageCree.getTags().containsAll(List.of(TAG1_NOM, TAG2_NOM))).isTrue();
    }

    @Test
    void validatePublication() throws Exception {
        HttpResponse<String> response = putValidationDePublication();
        assertThat(response.statusCode()).isEqualTo(204);
    }

    @Test
    void updatePublication() {
    }

    @Test
    void deletePublication() {
    }

    private HttpResponse<String> postPublication(Publication publication)
            throws URISyntaxException, java.io.IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API_BASE_URL))
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(publication)))
                .header("Content-Type", "application/json")
                .build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private HttpResponse<String> putValidationDePublication()
            throws URISyntaxException, java.io.IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API_BASE_URL + ID_PUBLICATION + "/valider"))
                .PUT(HttpRequest.BodyPublishers.noBody())
                .build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private Publication publication() {
        Publication publication = new Publication();
        publication.setTitre(TITRE);
        publication.setDescription(DESCRIPTION);
        Tag tag1 = new Tag();
        tag1.setNom(TAG1_NOM);
        Tag tag2 = new Tag();
        tag2.setNom(TAG2_NOM);
        publication.setTags(List.of(tag1, tag2));
        return publication;
    }

}
