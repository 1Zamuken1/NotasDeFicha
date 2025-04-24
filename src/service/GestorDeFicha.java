package service;

import model.Ficha;
import model.Estudiante;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorDeFicha {
    private static final String CARPETA_ARCHIVOS = "Fichas";

    public void verificarOCrearCarpeta() {
        File carpeta = new File(CARPETA_ARCHIVOS);
        if (!carpeta.exists()) {
            if (carpeta.mkdir()) {
                System.out.println("Carpeta creada: " + CARPETA_ARCHIVOS);
            } else {
                System.out.println("No se pudo crear la carpeta.");
            }
        }
    }

    public Ficha crearFicha() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre de la ficha o curso: ");
        String nombreFicha = scanner.nextLine();

        System.out.print("Ingrese el número de estudiantes: ");
        int numeroEstudiantes = scanner.nextInt();

        System.out.print("Ingrese el número de notas por estudiante: ");
        int numeroNotas = scanner.nextInt();

        List<Estudiante> estudiantes = new ArrayList<>();

        for (int i = 0; i < numeroEstudiantes; i++) {
            scanner.nextLine();
            System.out.print("Ingrese el nombre del estudiante " + (i + 1) + ": ");
            String nombreEstudiante = scanner.nextLine();

            List<Double> notas = new ArrayList<>();
            for (int j = 0; j < numeroNotas; j++) {
                System.out.print("Ingrese la nota " + (j + 1) + " de " + nombreEstudiante + ": ");
                notas.add(scanner.nextDouble());
            }

            estudiantes.add(new Estudiante(nombreEstudiante, notas));
        }

        return new Ficha(nombreFicha, estudiantes);
    }

    public void generarArchivo(Ficha ficha) {
        String nombreArchivo = ficha.getNombreFicha() + ".txt";
    
        try (FileWriter writer = new FileWriter(Paths.get(CARPETA_ARCHIVOS, nombreArchivo).toFile())) {
            writer.write("Ficha: " + ficha.getNombreFicha() + "\n\n");
    
            for (Estudiante estudiante : ficha.getEstudiantes()) {
                double promedio = estudiante.calcularPromedio();
                String promedioFormateado = String.format("%.2f", promedio);
    
                writer.write("Nombre: " + estudiante.getNombre() + "\n");
                writer.write("Notas: " + estudiante.getNotas() + "\n");
                writer.write("Promedio: " + promedioFormateado + "\n");
                writer.write("Calificación Final: " + promedioFormateado + "\n\n");
            }
    
            System.out.println("Archivo generado: " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al generar el archivo: " + e.getMessage());
        }
    }

    public List<String> listarArchivos() {
        File carpeta = new File(CARPETA_ARCHIVOS);
        String[] archivos = carpeta.list((dir, name) -> name.endsWith(".txt"));
        if (archivos == null || archivos.length == 0) {
            System.out.print("No hay archivos disponibles.");
            return new ArrayList<>();
        }
        List<String> listaArchivos = List.of(archivos);
        System.out.println("Archivos disponibles:");
        for (int i = 0; i < listaArchivos.size(); i++) {
            System.out.print((i + 1) + ". " + listaArchivos.get(i) + "\n");
        }
        return listaArchivos;
    }

    public void leerArchivo(String nombreArchivo) {
        try {
            List<String> lineas = Files.readAllLines(Paths.get(CARPETA_ARCHIVOS, nombreArchivo));
            System.out.println("Contenido del archivo:");
    
            for (String linea : lineas) {
                if (linea.startsWith("Promedio: ") || linea.startsWith("Calificación Final: ")) {
                    String[] partes = linea.split(": ");
                    String valorTexto = partes[1].replace(",", ".");
                    double valor = Double.parseDouble(valorTexto);
                    String valorFormateado = String.format("%.2f", valor);
                    System.out.println(partes[0] + ": " + valorFormateado);
                } else {
                    System.out.println(linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error al convertir un número: " + e.getMessage());
        }
    }

    public void editarNotas(String nombreArchivo) {
        try {
            List<String> lineas = Files.readAllLines(Paths.get(CARPETA_ARCHIVOS, nombreArchivo));
            Scanner scanner = new Scanner(System.in);
    
            List<String> estudiantes = new ArrayList<>();
            for (String linea : lineas) {
                if (linea.startsWith("Nombre: ")) {
                    estudiantes.add(linea.substring(8));
                }
            }
    
            if (estudiantes.isEmpty()) {
                System.out.println("No hay estudiantes en este archivo.");
                return;
            }
    
            System.out.println("Estudiantes disponibles:");
            for (int i = 0; i < estudiantes.size(); i++) {
                System.out.println((i + 1) + ". " + estudiantes.get(i));
            }
    
            System.out.print("Seleccione un estudiante para editar sus notas: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();
    
            if (opcion < 1 || opcion > estudiantes.size()) {
                System.out.println("Opción inválida.");
                return;
            }
    
            String estudianteSeleccionado = estudiantes.get(opcion - 1);
            System.out.println("Editando notas para: " + estudianteSeleccionado);
    
            List<Double> nuevasNotas = new ArrayList<>();
            System.out.print("Ingrese el número de notas: ");
            int numeroNotas = scanner.nextInt();
    
            for (int i = 0; i < numeroNotas; i++) {
                System.out.print("Ingrese la nota " + (i + 1) + ": ");
                nuevasNotas.add(scanner.nextDouble());
            }
    
            double nuevoPromedio = nuevasNotas.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
            String promedioFormateado = String.format("%.2f", nuevoPromedio);
    
            try (FileWriter writer = new FileWriter(Paths.get(CARPETA_ARCHIVOS, nombreArchivo).toFile())) {
                boolean actualizar = false;
    
                for (String linea : lineas) {
                    if (linea.startsWith("Nombre: ") && linea.contains(estudianteSeleccionado)) {
                        writer.write("Nombre: " + estudianteSeleccionado + "\n");
                        writer.write("Notas: " + nuevasNotas + "\n");
                        writer.write("Promedio: " + promedioFormateado + "\n");
                        writer.write("Calificación Final: " + promedioFormateado + "\n");
                        actualizar = true;
                    } else if (actualizar && (linea.startsWith("Notas: ") || linea.startsWith("Promedio: ") || linea.startsWith("Calificación Final: "))) {
                        continue;
                    } else {
                        writer.write(linea + "\n");
                        actualizar = false;
                    }
                }
            }
    
            System.out.println("Notas actualizadas correctamente.");
        } catch (IOException e) {
            System.out.println("Error al editar el archivo: " + e.getMessage());
        }
    }
}