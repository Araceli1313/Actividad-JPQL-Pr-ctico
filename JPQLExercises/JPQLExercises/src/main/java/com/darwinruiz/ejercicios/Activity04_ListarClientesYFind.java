package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/*Ejercicio realizado por Araceli*/
/*
ENUNCIADO:
1) Lista todos los clientes ordenados por nombre (JPQL).
2) Toma el primero de la lista y búscalo con entityManager.find() por ID.
3) Imprime ambos resultados.
*/

import java.util.List;

public class Activity04_ListarClientesYFind {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {

            List<Cliente> clientes = entityManager
                    .createQuery("SELECT c FROM Cliente c ORDER BY c.nombre", Cliente.class)
                    .getResultList();

            System.out.println("📋 Lista de clientes ordenados por nombre:");
            clientes.forEach(System.out::println);

            if (!clientes.isEmpty()) {
                Cliente primero = clientes.get(0);
                Cliente encontrado = entityManager.find(Cliente.class, primero.getId());

                System.out.println("\n Primer cliente de la lista: " + primero);
                System.out.println("Cliente encontrado con find(): " + encontrado);
            } else {
                System.out.println("No hay clientes en la base de datos.");
            }
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
