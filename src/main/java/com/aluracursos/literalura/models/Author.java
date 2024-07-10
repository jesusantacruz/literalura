package com.aluracursos.literalura.models;

import jakarta.persistence.*;
import java.util.List;

// Tabla Autor
@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer birthYear;
    private Integer deathYear;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> books;

    public Author() {}

    public Author(AuthorData authorData) {
        this.name = authorData.name();
        this.birthYear = authorData.birth_year();
        this.deathYear = authorData.death_year();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        books.forEach(l -> l.setAuthor(this));
        this.books = books;
    }

    @Override
    public String toString() {
        return "Autor" +
                "\n\t Nombre: " + name +
                "\n\t Fecha de Nacimiento: " + birthYear +
                "\n\t Fecha de Fallecimiento: " + deathYear;
    }
}
