package com.aluracursos.literalura.main;

import com.aluracursos.literalura.models.*;
import com.aluracursos.literalura.repository.AuthorRepository;
import com.aluracursos.literalura.repository.BookRepository;
import com.aluracursos.literalura.service.ApiConnection;
import com.aluracursos.literalura.service.DataConverter;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Menu {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private final Scanner scanner = new Scanner(System.in);
    private final ApiConnection connection = new ApiConnection();
    private final DataConverter converter = new DataConverter();
    private final String CLOSING_APPLICATION = "Cerrando aplicación";
    private final String INVALID_OPTION = "Opción no valida";
    private final String INVALID_INPUT = "Entrada no valida, ingrese un digito.";

    public Menu(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public void show(){
        int option = 0;
        boolean exit = false;
        while (!exit){
            String menu = """
                =================================================
                **                      Menú                   **
                =================================================
                1 - Buscar libro por titulo en API
                2 - Listar libros registrados localmente
                3 - Buscar por autores almacenados localmente
                4 - Buscar autores vivos en un año determinado
                5 - Buscar libros por idioma
                0 - Salir
                =================================================
                """;
            System.out.println(menu);
            try {
                option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1:
                        findBookFromApi();
                        break;
                    case 2:
                        showBooks();
                        break;
                    case 3:
                        searchByAuthor();
                        break;
                    case 4:
                        showAuthorsByYear();
                        break;
                    case 5:
                        languageMenu();
                        break;
                    case 0:
                        exit = true;
                        System.out.println(CLOSING_APPLICATION);
                        break;
                    default:
                        System.out.println(INVALID_OPTION);
                }
            } catch (InputMismatchException e) {
                System.out.println(INVALID_INPUT);
                scanner.nextLine();
            }

        }

    }

    private void languageMenu() {
        String menu = """
            =================================================
            ***          Menu buscar por idioma           ***
            =================================================
            Escriba la abrebiación: 
            """;
        System.out.print(menu);
        var acronym = scanner.nextLine();
        System.out.println("=================================================\n");
        showBooksByLanguage(acronym);
    }


    private void showBooksByLanguage(String acronym) {
        List<Book> books = bookRepository.findByLanguageAcronym(acronym);
        if (books.isEmpty()) {
            System.out.printf("No hay libros disponibles en: " + acronym);
        } else {
            System.out.printf("Libros disponibles en: \n" + acronym);
            books.forEach(book -> System.out.println(book.toString()));
        }
    }

    private void findBookFromApi() {
        System.out.println("Escribe el titulo a buscar ");
        var title = scanner.nextLine().toLowerCase();
        var json = connection.getData(ApiConnection.BASE_URL + title.replace(" ", "%20"));
        BooksData booksData = converter.transformData(json, BooksData.class);

        if (booksData.results().isEmpty()) {
            System.out.println("No se encontraron libros.");
            return;
        }
        JsonBook jsonBook = booksData.results().get(0);
        Book book = new Book(jsonBook);

        if (bookRepository.existsByTitle(book.getTitle())) {
            System.out.println("El libro ya está registrado en la base de datos:");
            Book existsBook = bookRepository.findByTitleContainsIgnoreCase(book.getTitle());
            System.out.println(existsBook.toString());
        } else {
            bookRepository.save(book);
            System.out.println("Libro guardado correctamente:");
            System.out.println(book.toString());
        }

    }

    private void showBooks() {
        var books = bookRepository.findAll();
        if (books.isEmpty()) {
            System.out.println("No hay libros almacenados en la base de datos.");
        } else {
           books.forEach(System.out::println);
        }
    }

    private void searchByAuthor() {
        System.out.println("Escribe el nombre del autor que deseas buscar");
        var name = scanner.nextLine().toLowerCase();
        List<Book> books = bookRepository.findByAuthorName(name);

        if (books.isEmpty()) {
            System.out.println("No se encontraron libros para el autor: " + name);
        } else {
            Author author = books.get(0).getAuthor();
            System.out.println("====================Libros========================");
            System.out.println("Nombre: " + name);
            System.out.println("\tFecha de nacimiento: " + author.getBirthYear());
            System.out.println("\tFecha de fallecimiento: " + author.getDeathYear());
            System.out.println("\tLibros:");
            books.forEach(book -> System.out.println(book.toString()));
        }

    }

    private void showAuthorsByYear() {
        System.out.print("Año a buscar: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        List<Author> authors = authorRepository.findAuthorsByYear(year);

        if (authors.isEmpty()) {
            System.out.println("No se encontraron autores en el año: " + year);
        } else {
            System.out.println("Autores que habia en el año " + year + ":");
            authors.forEach(author -> {
                System.out.println("Nombre: " + author.getName());
                System.out.println("Fecha de Nacimiento: " + author.getBirthYear());
                System.out.println("Fecha de Fallecimiento: " + author.getDeathYear());
                System.out.println("=================================================");
            });
        }
    }
}
