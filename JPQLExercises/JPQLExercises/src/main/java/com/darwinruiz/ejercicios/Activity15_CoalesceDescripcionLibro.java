package com.darwinruiz.ejercicios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

/*
ENUNCIADO:
Selecciona titulo y una descripcion segura usando COALESCE(descripcion, 'Sin descripción').
Imprime "Titulo | DescripcionSegura".
*/
public class Activity15_CoalesceDescripcionLibro {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            List<Object[]> resultados = entityManager.createQuery(
                    "SELECT l.titulo, COALESCE(l.descripcion, 'Sin descripción') " +
                            "FROM Libro l ORDER BY l.titulo",
                    Object[].class
            ).getResultList();

            if (resultados.isEmpty()) {
                System.out.println("No se encontraron libros.");
            } else {
                resultados.forEach(fila -> {
                    String titulo = (String) fila[0];
                    String descripcionSegura = (String) fila[1];
                    System.out.println(titulo + " | " + descripcionSegura);
                });
            }

        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
