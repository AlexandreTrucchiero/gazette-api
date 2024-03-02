package com.alexandretrucchiero.gazetteapi.api.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.Objects;

public class TagStatistiques {
    @JsonProperty("nombreAbonnes")
    private Integer nombreAbonnes;

    @JsonProperty("dateDernierUsage")
    @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dateDernierUsage;

    public TagStatistiques nombreAbonnes(Integer nombreAbonnes) {
        this.nombreAbonnes = nombreAbonnes;
        return this;
    }

    /**
     * Nombre d'utilisateurs ayant au moins un abonnement qui inclut ce tag
     *
     * @return nombreAbonnes
     */


    public Integer getNombreAbonnes() {
        return nombreAbonnes;
    }

    public void setNombreAbonnes(Integer nombreAbonnes) {
        this.nombreAbonnes = nombreAbonnes;
    }

    public TagStatistiques dateDernierUsage(OffsetDateTime dateDernierUsage) {
        this.dateDernierUsage = dateDernierUsage;
        return this;
    }

    /**
     * Date de l'usage le plus récent de ce tag dans une publication
     *
     * @return dateDernierUsage
     */

    public OffsetDateTime getDateDernierUsage() {
        return dateDernierUsage;
    }

    public void setDateDernierUsage(OffsetDateTime dateDernierUsage) {
        this.dateDernierUsage = dateDernierUsage;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TagStatistiques tagStatistiques = (TagStatistiques) o;
        return Objects.equals(this.nombreAbonnes, tagStatistiques.nombreAbonnes) &&
                Objects.equals(this.dateDernierUsage, tagStatistiques.dateDernierUsage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreAbonnes, dateDernierUsage);
    }

    @Override
    public String toString() {

        String sb = "class TagStatistiques {\n" +
                "    nombreAbonnes: " + toIndentedString(nombreAbonnes) + "\n" +
                "    dateDernierUsage: " + toIndentedString(dateDernierUsage) + "\n" +
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

