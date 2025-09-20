package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.util.List;

/*
ENUNCIADO:
Consulta libros con precio entre 150 y 800 (inclusive) usando BETWEEN.
Ordena por precio ASC e imprime.
*/
public class Activity11_RangosPrecioBetweenLibros {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            List<Libro> libros = entityManager.createQuery(
                            "SELECT l FROM Libro l " +
                                    "WHERE l.precio BETWEEN :min AND :max " +
                                    "ORDER BY l.precio ASC",
                            Libro.class
                    )
                    .setParameter("min", new BigDecimal("150"))
                    .setParameter("max", new BigDecimal("800"))
                    .getResultList();

            if (libros.isEmpty()) {
                System.out.println("No se encontraron libros en ese rango de precios.");
            } else {
                System.out.println("Libros en rango de precios (150 - 800):");
                libros.forEach(System.out::println);
            }

        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
