package com.aluracursos.literalura.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthorData(
        String name,
        Integer birth_year,
        Integer death_year) {
}
