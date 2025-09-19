package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Cliente;
import com.darwinruiz.models.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.time.LocalDate;

/*Ejercicio realizado por Araceli*/
/*
ENUNCIADO:
1) Inserta al menos 3 clientes y 3 libros con datos realistas.
2) Varía fechas (fechaRegistro y fechaPublicacion), genera 1 libro con descripcion = NULL y 1 con stock = 0.
3) Imprime los IDs generados.
Restricciones:
- Usa tipos explícitos (sin var).
- Maneja transacción (begin/commit/rollback).
*/
public class Activity01_InsertarClientesYLibros {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            // TODO: crear clientes y libros, settear campos y persistir
            // Cliente cliente = new Cliente(); cliente.setNombre("..."); ...
            Cliente cliente = new Cliente();
            cliente.setNombre("Araceli Asencio");
            cliente.setEmail("asencioaraceli5022@gmail.com");
            cliente.setEdad(22);
            cliente.setCiudad("Guatemala");
            cliente.setActivo(true);
            cliente.setFechaRegistro(LocalDate.of(2024, 5, 10));
            entityManager.persist(cliente);

            Cliente cliente2 = new Cliente();
            cliente2.setNombre("David Escobar");
            cliente2.setEmail("escobardavid@gmail.com");
            cliente2.setEdad(20);
            cliente2.setCiudad("Guatemala");
            cliente2.setActivo(true);
            cliente2.setFechaRegistro(LocalDate.of(2025, 9, 10));
            entityManager.persist(cliente2);

            Cliente cliente3 = new Cliente();
            cliente3.setNombre("Sharon Marroquin");
            cliente3.setEmail("sharonMarroquind@gmail.com");
            cliente3.setEdad(20);
            cliente3.setCiudad("Guatemala");
            cliente3.setActivo(true);
            cliente3.setFechaRegistro(LocalDate.now());
            entityManager.persist(cliente3);

            Libro libro = new Libro();
            libro.setTitulo("El Señor de los Anillos");
            libro.setAutorNombre("J. R. R. Tolkien");
            libro.setGenero("Fantasía");
            libro.setPrecio(new BigDecimal("175.00"));
            libro.setStock(15);
            libro.setActivo(true);
            libro.setFechaPublicacion(LocalDate.of(1954, 7, 29));
            libro.setDescripcion("Clásico de la literatura fantástica.");
            entityManager.persist(libro);

            Libro libro2 = new Libro();
            libro2.setTitulo("Cien Años de Soledad");
            libro2.setAutorNombre("Gabriel García Márquez");
            libro2.setGenero("Realismo Mágico");
            libro2.setPrecio(new BigDecimal("180.00"));
            libro2.setStock(0); // stock = 0 (restricción)
            libro2.setActivo(true);
            libro2.setFechaPublicacion(LocalDate.of(1967, 5, 30));
            libro2.setDescripcion("Obra maestra del realismo mágico.");
            entityManager.persist(libro2);

            Libro libro3 = new Libro();
            libro3.setTitulo("Clean Code");
            libro3.setAutorNombre("Robert C. Martin");
            libro3.setGenero("Tecnología");
            libro3.setPrecio(new BigDecimal("220.00"));
            libro3.setStock(8);
            libro3.setActivo(true);
            libro3.setFechaPublicacion(LocalDate.of(2008, 8, 1));
            libro3.setDescripcion(null); // descripcion = NULL (restricción)
            entityManager.persist(libro3);

            entityManager.getTransaction().commit();

            // TODO: imprimir IDs creados
            System.out.println("Clientes insertados con IDs: " +
                    cliente.getId() + ", " + cliente2.getId() + ", " + cliente3.getId());
            System.out.println("Libros insertados con IDs: " +
                    libro.getId() + ", " + libro2.getId() + ", " + libro3.getId());

        } catch (RuntimeException exception) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            throw exception;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}