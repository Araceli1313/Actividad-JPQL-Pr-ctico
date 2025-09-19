package com.darwinruiz.ejercicios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

/*Ejercicio realizado por Araceli*/

/* ENUNCIADO: Agrupa libros por género y muestra:
- COUNT(*), AVG(precio), SUM(stock) Ordena por género ASC. */

import java.util.List;

public class Activity06_AgruparLibrosPorGenero {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            Query query = entityManager.createQuery(
                    "SELECT l.genero, COUNT(l), AVG(l.precio), SUM(l.stock) " +
                            "FROM Libro l " +
                            "GROUP BY l.genero " +
                            "ORDER BY l.genero ASC"
            );

            List<Object[]> resultados = query.getResultList();

            System.out.println("Libros agrupados por género:");
            for (Object[] fila : resultados) {
                String genero = (String) fila[0];
                Long cantidad = (Long) fila[1];
                Double promedioPrecio = (Double) fila[2];
                Long totalStock = (Long) fila[3];

                System.out.printf("Género: %-15s | Cantidad: %d | Promedio Precio: %.2f | Total Stock: %d%n",
                        genero, cantidad, promedioPrecio, totalStock);
            }
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
