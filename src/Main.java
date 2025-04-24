import model.Ficha;
import service.GestorDeFicha;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorDeFicha gestor = new GestorDeFicha();
        Scanner scanner = new Scanner(System.in);

        gestor.verificarOCrearCarpeta();

        while (true) {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Crear un nuevo curso o ficha");
            System.out.println("2. Ver registros previos");
            System.out.println("3. Editar notas de un estudiante");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    Ficha ficha = gestor.crearFicha();
                    gestor.generarArchivo(ficha);
                    break;
                case 2:
                    List<String> archivos = gestor.listarArchivos();
                    if (!archivos.isEmpty()) {
                        System.out.print("\nSeleccione un archivo para ver: ");
                        int archivoSeleccionado = scanner.nextInt();
                        if (archivoSeleccionado >= 1 && archivoSeleccionado <= archivos.size()) {
                            gestor.leerArchivo(archivos.get(archivoSeleccionado - 1));
                        } else {
                            System.out.println("Opción inválida.");
                        }
                    }
                    break;
                case 3:
                    List<String> archivosEditar = gestor.listarArchivos();
                    if (!archivosEditar.isEmpty()) {
                        System.out.print("\nSeleccione un archivo para editar: ");
                        int archivoSeleccionado = scanner.nextInt();
                        if (archivoSeleccionado >= 1 && archivoSeleccionado <= archivosEditar.size()) {
                            gestor.editarNotas(archivosEditar.get(archivoSeleccionado - 1));
                        } else {
                            System.out.println("Opción inválida.");
                        }
                    }
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}