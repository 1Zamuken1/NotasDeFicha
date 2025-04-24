package model;

import java.util.List;

public class Ficha {
    private String nombreFicha;
    private List<Estudiante> estudiantes;

    public Ficha(String nombreFicha, List<Estudiante> estudiantes) {
        this.nombreFicha = nombreFicha;
        this.estudiantes = estudiantes;
    }

    public String getNombreFicha() {
        return nombreFicha;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }
}
