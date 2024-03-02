package com.alexandretrucchiero.gazetteapi.api.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.Objects;


public class Tag {
    @JsonProperty("nom")
    private String nom;

    @JsonProperty("priorite")
    private Integer priorite;

    @JsonProperty("dateCreation")
    @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dateCreation;

    @JsonProperty("statistiques")
    private TagStatistiques statistiques;

    public Tag nom(String nom) {
        this.nom = nom;
        return this;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Tag priorite(Integer priorite) {
        this.priorite = priorite;
        return this;
    }

    /**
     * Priorité du tag (sert à ordonner les tags lorsqu'on les affiche sur une publication ou un abonnement)
     * minimum: 1
     * maximum: 10
     *
     * @return priorite
     */
    public Integer getPriorite() {
        return priorite;
    }

    public void setPriorite(Integer priorite) {
        this.priorite = priorite;
    }

    public Tag dateCreation(OffsetDateTime dateCreation) {
        this.dateCreation = dateCreation;
        return this;
    }

    /**
     * Date de création du tag
     *
     * @return dateCreation
     */

    public OffsetDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(OffsetDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Tag statistiques(TagStatistiques statistiques) {
        this.statistiques = statistiques;
        return this;
    }

    /**
     * Get statistiques
     *
     * @return statistiques
     */

    public TagStatistiques getStatistiques() {
        return statistiques;
    }

    public void setStatistiques(TagStatistiques statistiques) {
        this.statistiques = statistiques;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tag tag = (Tag) o;
        return Objects.equals(this.nom, tag.nom) &&
                Objects.equals(this.priorite, tag.priorite) &&
                Objects.equals(this.dateCreation, tag.dateCreation) &&
                Objects.equals(this.statistiques, tag.statistiques);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, priorite, dateCreation, statistiques);
    }

    @Override
    public String toString() {

        String sb = "class Tag {\n" +
                "    nom: " + toIndentedString(nom) + "\n" +
                "    priorite: " + toIndentedString(priorite) + "\n" +
                "    dateCreation: " + toIndentedString(dateCreation) + "\n" +
                "    statistiques: " + toIndentedString(statistiques) + "\n" +
                "}";
        return sb;
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

