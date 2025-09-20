package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

/*
ENUNCIADO:
Trae los libros cuyo título termine en 'o' (insensible a mayúsculas)
y cuyo género empiece con 'fic' (ej. 'Ficción').
Ordena por titulo ASC.
*/
public class Activity10_LikeCaseInsensitiveLibros {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            List<Libro> libros = entityManager.createQuery(
                            "SELECT l FROM Libro l " +
                                    "WHERE LOWER(l.titulo) LIKE :fin " +
                                    "AND LOWER(l.genero) LIKE :inicio " +
                                    "ORDER BY l.titulo ASC",
                            Libro.class
                    )
                    .setParameter("fin", "%o")       // termina en "o"
                    .setParameter("inicio", "fic%") // empieza con "fic"
                    .getResultList();

            if (libros.isEmpty()) {
                System.out.println("No se encontraron libros con esas condiciones.");
            } else {
                System.out.println("Libros encontrados:");
                libros.forEach(System.out::println);
            }

        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
