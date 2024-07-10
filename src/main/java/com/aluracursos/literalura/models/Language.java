package com.aluracursos.literalura.models;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "language")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String acronym;
    @OneToMany(mappedBy = "language", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Book> books;

    public Language() {}

    public Language(String language) {
        this.acronym = language;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String siglas) {
        this.acronym = siglas;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        books.forEach(e -> e.setLanguage(this));
        this.books = books;
    }

    @Override
    public String toString() {
        return "Idioma:" +
               "\n\tSigla: " + acronym +
               "\n\tLibros: " + books;
    }
}
