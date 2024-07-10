package com.aluracursos.literalura.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Integer apiId;
    @Column(unique = true)
    private String title;
    @ManyToOne(cascade = CascadeType.ALL)
    private Author author;
    @ManyToOne(cascade = CascadeType.ALL)
    private Language language;
    private Long downloads;

    public Book() {}

    public Book(JsonBook jsonBook) {
        this.apiId = jsonBook.id();
        this.title = jsonBook.title();
        this.downloads = jsonBook.downloads();
        if (!jsonBook.languages().isEmpty()) {
            this.language = new Language(jsonBook.languages().get(0));
        } else {
            this.language = null;
        }
        if (jsonBook.authors() != null && !jsonBook.authors().isEmpty()) {
            this.author = new Author(jsonBook.authors().get(0));
        } else {
            this.author = null;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Long getDownloads() {
        return downloads;
    }

    public void setDownloads(Long downloads) {
        this.downloads = downloads;
    }

    @Override
    public String toString() {
        String string = "====================Libro========================";
        string += "\n\tID API: " + (apiId != null ? apiId : "N/A") +
                "\n\tTitulo: " + (title != null ? title : "N/A") +
                "\n\tAutores: " + (author != null ? author.getName() : "N/A") +
                "\n\tIdiomas: " + (language != null ? language.getAcronym() : "N/A") +
                "\n\tNumero de descargas: " + (downloads != null ? downloads : "N/A");

        return string;
    }
}
