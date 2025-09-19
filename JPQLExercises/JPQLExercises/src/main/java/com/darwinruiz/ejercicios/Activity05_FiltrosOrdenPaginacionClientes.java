package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

/*Ejercicio realizado por Araceli*/

/* ENUNCIADO: Consulta clientes cuyo nombre contenga 'a' (case-insensitive),
ordena por ciudad ASC, y devuelve la página 2 (tamaño 3). Imprime los resultados. */

import java.util.List;

public class Activity05_FiltrosOrdenPaginacionClientes {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            TypedQuery<Cliente> query = entityManager.createQuery(
                    "SELECT c FROM Cliente c " +
                            "WHERE LOWER(c.nombre) LIKE :patron " +
                            "ORDER BY c.ciudad ASC",
                    Cliente.class
            );

            query.setParameter("patron", "%a%");
            query.setFirstResult(3);
            query.setMaxResults(3);

            List<Cliente> clientes = query.getResultList();

            System.out.println("📋 Página 2 (3 resultados) de clientes con 'a' en el nombre:");
            clientes.forEach(System.out::println);

        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
