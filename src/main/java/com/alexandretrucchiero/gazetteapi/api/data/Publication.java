package com.alexandretrucchiero.gazetteapi.api.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Publication {

    @JsonProperty("id")
    private String id;

    @JsonProperty("nature")
    private String nature;

    @JsonProperty("statut")
    private String statut;

    @JsonProperty("createur")
    private String createur;

    @JsonProperty("dateCreation")
    @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dateCreation;

    @JsonProperty("datePublication")
    @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime datePublication;

    @JsonProperty("tags")
    private List<Tag> tags = null;

    @JsonProperty("titre")
    private String titre;

    @JsonProperty("description")
    private String description;

    public Publication id(String id) {
        this.id = id;
        return this;
    }

    /**
     * Identifiant de la publication (ignoré lors d'une création)
     *
     * @return id
     */


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Publication nature(String nature) {
        this.nature = nature;
        return this;
    }

    /**
     * Nature de la publication, 'evenement' ou 'message'
     *
     * @return nature
     */


    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public Publication statut(String statut) {
        this.statut = statut;
        return this;
    }

    /**
     * Statut de la publication, 'brouillon' or 'publié'
     *
     * @return statut
     */


    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Publication createur(String createur) {
        this.createur = createur;
        return this;
    }

    /**
     * uid du créateur
     *
     * @return createur
     */


    public String getCreateur() {
        return createur;
    }

    public void setCreateur(String createur) {
        this.createur = createur;
    }

    public Publication dateCreation(OffsetDateTime dateCreation) {
        this.dateCreation = dateCreation;
        return this;
    }

    /**
     * Date de création de la publication
     *
     * @return dateCreation
     */

    public OffsetDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(OffsetDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Publication datePublication(OffsetDateTime datePublication) {
        this.datePublication = datePublication;
        return this;
    }

    /**
     * Date de publication
     *
     * @return datePublication
     */

    public OffsetDateTime getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(OffsetDateTime datePublication) {
        this.datePublication = datePublication;
    }

    public Publication tags(List<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public Publication addTagsItem(Tag tagsItem) {
        if (this.tags == null) {
            this.tags = new ArrayList<>();
        }
        this.tags.add(tagsItem);
        return this;
    }

    /**
     * Get tags
     *
     * @return tags
     */

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Publication titre(String titre) {
        this.titre = titre;
        return this;
    }

    /**
     * Titre de la publication
     *
     * @return titre
     */

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Publication description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Contenu de la publication, dans un format texte riche (html ?)
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Publication publication = (Publication) o;
        return Objects.equals(this.id, publication.id) &&
                Objects.equals(this.nature, publication.nature) &&
                Objects.equals(this.statut, publication.statut) &&
                Objects.equals(this.createur, publication.createur) &&
                Objects.equals(this.dateCreation, publication.dateCreation) &&
                Objects.equals(this.datePublication, publication.datePublication) &&
                Objects.equals(this.tags, publication.tags) &&
                Objects.equals(this.titre, publication.titre) &&
                Objects.equals(this.description, publication.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nature, statut, createur, dateCreation, datePublication, tags, titre, description);
    }

    @Override
    public String toString() {
        return "class Publication {\n" +
                "    id: " + toIndentedString(id) + "\n" +
                "    nature: " + toIndentedString(nature) + "\n" +
                "    statut: " + toIndentedString(statut) + "\n" +
                "    createur: " + toIndentedString(createur) + "\n" +
                "    dateCreation: " + toIndentedString(dateCreation) + "\n" +
                "    datePublication: " + toIndentedString(datePublication) + "\n" +
                "    tags: " + toIndentedString(tags) + "\n" +
                "    titre: " + toIndentedString(titre) + "\n" +
                "    description: " + toIndentedString(description) + "\n" +
                "}";
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

