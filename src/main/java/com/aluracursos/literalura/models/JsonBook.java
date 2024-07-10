package com.aluracursos.literalura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record JsonBook(
        @JsonAlias("id") Integer id,
        String title,
        List<String> languages,
        List<AuthorData> authors,
        @JsonAlias("download_count") Long downloads) {
}