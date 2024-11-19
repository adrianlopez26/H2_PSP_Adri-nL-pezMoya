package com.empresa.cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 8080;
        try (Socket socket = new Socket(host, puerto);
             PrintWriter salida = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Conectado al servidor.");
            boolean salir = false;

            while (!salir) {
                System.out.println("\n--- Libreria Adrilib ---");
                System.out.println("1. Listar todos los libros");
                System.out.println("2. Agregar un nuevo libro");
                System.out.println("3. Actualizar un libro existente");
                System.out.println("4. Eliminar un libro");
                System.out.println("5. Buscar libros por año");
                System.out.println("6. Salir");
                System.out.print("Selecciona una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer

                switch (opcion) {
                    case 1:
                        salida.println("LISTAR");
                        String respuesta;
                        System.out.println("\n--- Lista de libros ---");
                        while ((respuesta = entrada.readLine()) != null && !respuesta.isEmpty()) {
                            System.out.println(respuesta);
                        }
                        break;

                    case 2: // Opción para agregar un libro
                        salida.println("AGREGAR"); // Enviar solicitud al servidor

                        // Solicitar datos al usuario
                        System.out.print("Título: ");
                        salida.println(scanner.nextLine());
                        System.out.print("Autor: ");
                        salida.println(scanner.nextLine());
                        System.out.print("Año: ");
                        salida.println(scanner.nextInt());
                        scanner.nextLine(); // Limpiar el buffer

                        // Leer y mostrar la respuesta del servidor
                        System.out.println(entrada.readLine());
                        break;

                    case 3:
                        salida.println("ACTUALIZAR");
                        System.out.print("ID del libro a actualizar: ");
                        salida.println(scanner.nextInt());
                        scanner.nextLine(); // Limpiar el buffer
                        System.out.print("Nuevo Título: ");
                        salida.println(scanner.nextLine());
                        System.out.print("Nuevo Autor: ");
                        salida.println(scanner.nextLine());
                        System.out.print("Nuevo Año: ");
                        salida.println(scanner.nextInt());
                        scanner.nextLine(); // Limpiar el buffer
                        System.out.println(entrada.readLine());
                        break;

                    case 4:
                        salida.println("ELIMINAR");
                        System.out.print("ID del libro a eliminar: ");
                        salida.println(scanner.nextInt());
                        scanner.nextLine(); // Limpiar el buffer
                        System.out.println(entrada.readLine());
                        break;

                    case 5:
                        salida.println("BUSCAR_POR_ANIO");
                        System.out.print("Año: ");
                        int anio = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer
                        salida.println(anio);
                        System.out.println("\n--- Libros del año " + anio + " ---");
                        while ((respuesta = entrada.readLine()) != null && !respuesta.isEmpty()) {
                            System.out.println(respuesta);
                        }
                        break;

                    case 6:
                        System.out.println("Saliendo del programa...");
                        salir = true;
                        break;

                    default:
                        System.out.println("Opción no válida. Inténtalo de nuevo.");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}