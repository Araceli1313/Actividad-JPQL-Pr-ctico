package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

/*Ejercicio realizado por Araceli*/

/* ENUNCIADO: Elimina un libro buscándolo por título exacto con JPQL.
Restricciones: - Usa getSingleResult() o maneja lista vacía.
- Transacción obligatoria. */

public class Activity03_EliminarLibroPorTitulo {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            String tituloBuscado = "El Señor de los Anillos";

            try {
                // Buscar libro por título exacto
                Libro libro = entityManager
                        .createQuery("SELECT l FROM Libro l WHERE l.titulo = :titulo", Libro.class)
                        .setParameter("titulo", tituloBuscado)
                        .getSingleResult();

                // Eliminarlo
                entityManager.remove(libro);
                System.out.println("Listo. libro eliminado: " + libro);

            } catch (NoResultException e) {
                System.out.println("Le informamos que no se encontró ningún libro con el título: " + tituloBuscado);
            }

            entityManager.getTransaction().commit();
        } catch (RuntimeException exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw exception;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
