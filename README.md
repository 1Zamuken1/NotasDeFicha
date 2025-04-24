# Notas de Ficha

Este proyecto es una aplicación en Java que permite gestionar las notas de estudiantes de un curso o ficha. La aplicación utiliza los principios de la Programación Orientada a Objetos (POO) y permite realizar operaciones de **CRUD** (Crear, Leer, Actualizar, Eliminar) sobre los registros de estudiantes almacenados en archivos `.txt`.

---

## **Características**

- **Crear un curso o ficha**: Permite registrar estudiantes, sus notas y calcular automáticamente el promedio y la calificación final.
- **Ver registros previos**: Muestra los archivos `.txt` generados previamente y permite visualizar su contenido.
- **Editar notas de un estudiante**: Permite modificar las notas de un estudiante y recalcula automáticamente el promedio y la calificación final.
- **Gestión de archivos**: Los registros se almacenan en una carpeta específica (`Cursos`) y se pueden manipular fácilmente.
- **Formato consistente**: Los promedios y calificaciones finales se muestran con un máximo de 2 decimales.

---

## **Requisitos del sistema**

- **Java**: JDK 17 o superior.
- **Sistema operativo**: Windows, macOS o Linux.
- **IDE recomendado**: Visual Studio Code, IntelliJ IDEA o Eclipse.

---

## **Estructura del proyecto**

```
NotasDeFicha/
├── src/
│   ├── model/
│   │   ├── Ficha.java
│   │   ├── Estudiante.java
│   ├── service/
│   │   ├── GestorDeFicha.java
│   ├── Main.java
├── Cursos/ (se crea automáticamente para almacenar los archivos .txt)
├── README.md
```

- **`model/`**: Contiene las clases que representan los datos principales (`Ficha` y `Estudiante`).
- **`service/`**: Contiene la lógica de negocio (`GestorDeFicha`).
- **`Main.java`**: Punto de entrada del programa.
- **`Cursos/`**: Carpeta donde se almacenan los archivos `.txt` generados.

---

## **Cómo ejecutar el proyecto**

### **En Windows**
1. **Clonar el repositorio**:
   ```bash
   git clone <https://github.com/1Zamuken1/NotasDeFicha>
   cd NotasDeFicha
   ```

2. **Compilar el proyecto**:
   ```bash
   javac -d bin src/**/*.java
   ```

3. **Ejecutar el proyecto**:
   ```bash
   java -cp bin Main
   ```

### **En Linux**
1. **Clonar el repositorio**:
   ```bash
   git clone <https://github.com/1Zamuken1/NotasDeFicha>
   cd NotasDeFicha
   ```

2. **Compilar el proyecto**:
   ```bash
   javac -d bin src/**/*.java
   ```

3. **Ejecutar el proyecto**:
   ```bash
   java -cp bin Main
   ```

4. **Usar el menú interactivo**:
   - Selecciona una opción del menú para crear, ver o editar registros.

---

## **Uso del programa**

### **Menú principal**
El programa presenta un menú interactivo con las siguientes opciones:

1. **Crear un nuevo curso o ficha**:
   - Ingresa el nombre del curso.
   - Ingresa el número de estudiantes y sus notas.
   - El programa genera un archivo `.txt` con los datos.

2. **Ver registros previos**:
   - Muestra una lista de los archivos `.txt` disponibles.
   - Permite seleccionar un archivo para visualizar su contenido.

3. **Editar notas de un estudiante**:
   - Selecciona un archivo.
   - Elige un estudiante y modifica sus notas.
   - El archivo se actualiza automáticamente con las nuevas notas y el promedio.

4. **Salir**:
   - Finaliza la ejecución del programa.

---

## **Ejemplo de archivo generado**

Un archivo `.txt` generado por el programa tiene el siguiente formato:

```
Ficha: Curso001

Nombre: Juan
Notas: [10.0, 9.0, 8.0]
Promedio: 9.00
Calificación Final: 9.00

Nombre: María
Notas: [8.0, 7.0, 9.0]
Promedio: 8.00
Calificación Final: 8.00
```

---

## **Principios de diseño**

El proyecto sigue los principios de la Programación Orientada a Objetos (POO):

1. **Encapsulamiento**:
   - Las clases `Ficha` y `Estudiante` encapsulan los datos y métodos relacionados.
2. **Abstracción**:
   - La clase `GestorDeFicha` abstrae la lógica de negocio, como la gestión de archivos y el cálculo de promedios.
3. **Polimorfismo**:
   - Se utiliza en métodos como `calcularPromedio` para operar sobre diferentes datos sin preocuparse por los detalles.
4. **Modularidad**:
   - El código está organizado en paquetes (`model`, `service`) para facilitar su mantenimiento.

---

## **Posibles mejoras**

- Implementar una interfaz gráfica (GUI) para mejorar la experiencia del usuario.
- Agregar validaciones más robustas para la entrada de datos.
- Permitir eliminar estudiantes o cursos completos.
- Soporte para exportar los datos en otros formatos, como CSV o JSON.

---

## **Autor**

Este proyecto fue desarrollado por **Juan Barrios**.

---

## **Contribuciones**

¡Las contribuciones son bienvenidas! Si deseas colaborar, por favor sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una rama para tu funcionalidad (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza tus cambios y haz un commit (`git commit -m "Descripción de los cambios"`).
4. Envía un pull request.

---

## **Licencia**

Este proyecto está bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.