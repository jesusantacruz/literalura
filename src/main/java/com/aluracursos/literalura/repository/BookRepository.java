package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
  boolean existsByTitle(String title);
  List<Book> findByLanguageAcronym(String acronym);
  Book findByTitleContainsIgnoreCase(String title);
  @Query("SELECT b FROM Book b WHERE LOWER(b.author.name) LIKE LOWER(CONCAT('%', :name, '%'))")
  List<Book> findByAuthorName(@Param("name") String name);
}
