package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

/*
ENUNCIADO:
Imprime libros paginando de 5 en 5, recorriendo todas las páginas.
Ordena por id ASC.
*/
public class Activity13_PaginacionLibros {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            int pageSize = 5;
            int offset = 0;
            int page = 1;

            while (true) {
                List<Libro> libros = entityManager.createQuery(
                                "SELECT l FROM Libro l ORDER BY l.id ASC",
                                Libro.class
                        )
                        .setFirstResult(offset)   // desde qué registro empezar
                        .setMaxResults(pageSize)  // cuántos registros traer
                        .getResultList();

                if (libros.isEmpty()) {
                    break; // no hay más resultados, salir del bucle
                }

                System.out.println("Página " + page + ":");
                libros.forEach(System.out::println);

                offset += pageSize; // avanzar al siguiente bloque
                page++;
            }

        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
