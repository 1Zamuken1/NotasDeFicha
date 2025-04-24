package model;

import java.util.List;

public class Estudiante {
    private String nombre;
    private List<Double> notas;

    public Estudiante(String nombre, List<Double> notas) {
        this.nombre = nombre;
        this.notas = notas;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Double> getNotas() {
        return notas;
    }

    public double calcularPromedio() {
        return notas.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }
}