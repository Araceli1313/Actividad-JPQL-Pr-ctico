package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Arrays;
import java.util.List;

/*
ENUNCIADO:
Consulta clientes cuya ciudad esté en una lista (IN :ciudades),
por ejemplo: ["Guatemala","Antigua","Cobán"].
Imprime resultados.
*/
public class Activity14_IN_ListaDeCiudadesClientes {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            List<String> ciudades = Arrays.asList("Guatemala", "Antigua", "Cobán");

            List<Cliente> clientes = entityManager.createQuery(
                            "SELECT c FROM Cliente c WHERE c.ciudad IN :ciudades",
                            Cliente.class
                    )
                    .setParameter("ciudades", ciudades)
                    .getResultList();

            if (clientes.isEmpty()) {
                System.out.println("No se encontraron clientes en las ciudades: " + ciudades);
            } else {
                System.out.println("Clientes en ciudades " + ciudades + ":");
                clientes.forEach(System.out::println);
            }

        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
