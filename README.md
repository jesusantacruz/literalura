# Aplicación Literalura challenge

Esta es una aplicación de consola desarrollada en Java utilizando el framework Spring, que interactúa con la API de Gutendex para obtener datos de libros y almacenarlos en una base de datos local PostgreSQL. La aplicación permite realizar diversas operaciones sobre los datos almacenados.

## Funcionalidades

La aplicación proporciona el siguiente menú de opciones:

1. **Buscar libro por título en API**: Permite buscar libros en la API de Gutendex mediante el título.
2. **Listar libros registrados localmente**: Muestra una lista de los libros que han sido almacenados en la base de datos local.
3. **Buscar por autores almacenados localmente**: Permite buscar información sobre los autores que han sido almacenados en la base de datos local.
4. **Buscar autores vivos en un año determinado**: Permite buscar autores que estaban vivos en un año específico.
5. **Buscar libros por idioma**: Permite buscar libros almacenados en la base de datos local por su idioma.
0. **Salir**: Termina la ejecución de la aplicación.

## Tecnologías Utilizadas

- **Java**: Lenguaje de programación principal.
- **Spring Framework**: Para la configuración y gestión de dependencias.
- **Hibernate**: Para la gestión de la persistencia de datos (ORM).
- **PostgreSQL**: Base de datos relacional utilizada para almacenar los datos localmente.
- **Gutendex API**: API utilizada para obtener los datos de los libros.

## Requisitos Previos

Antes de ejecutar la aplicación, asegúrate de tener instalado lo siguiente:

- Java 11 o superior.
- Maven.
- PostgreSQL.
- Configurar una base de datos PostgreSQL local y actualizar las credenciales en el archivo `application.properties`.

