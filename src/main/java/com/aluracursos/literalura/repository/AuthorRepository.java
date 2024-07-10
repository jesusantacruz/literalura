package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT a FROM Author a WHERE (a.deathYear >= :year OR a.deathYear IS NULL) AND a.birthYear <= :year")
    List<Author> findAuthorsByYear(@Param("year") int year);
}
