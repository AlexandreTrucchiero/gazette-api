package com.alexandretrucchiero.gazetteapi.domain.notification.model;

import java.util.List;

public class PublicationANotifier {

    private final String titre;
    private final List<String> tags;

    public PublicationANotifier(String titre, List<String> tags) {
        this.titre = titre;
        this.tags = tags;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getTitre() {
        return titre;
    }
}
