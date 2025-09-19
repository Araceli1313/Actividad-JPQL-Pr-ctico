package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/*Ejercicio realizado por Araceli*/
/*
ENUNCIADO:
Usa la NamedQuery "Cliente.buscarPorEmail" para encontrar un cliente por email
y actualizar su nombre y ciudad.
Restricciones:
- Maneja transacción.
- Si no encuentra, muestra un mensaje.
*/
public class Activity02_ActualizarClientePorEmail {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            // TODO: obtener cliente por NamedQuery, modificar campos y merge
                String emailBuscado= "asencioaraceli5022@gmail.com";

            Cliente cliente = entityManager
                    .createNamedQuery("Cliente.buscarPorEmail", Cliente.class)
                    .setParameter("email", emailBuscado)
                    .getResultStream() // evita excepción si no hay resultado
                    .findFirst()
                    .orElse(null);

            if (cliente != null) {
                // Actualizar datos
                cliente.setNombre("Listo,nombre Actualizado");
                cliente.setCiudad("Ciudad Nueva");

                // merge = sincronizar cambios
                entityManager.merge(cliente);

                System.out.println("Listo, cliente actualizado: " + cliente);
            } else {
                System.out.println("Le informamos que no se encontró un cliente con el email: " + emailBuscado);
            }

            entityManager.getTransaction().commit();
        } catch (RuntimeException exception) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            throw exception;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}