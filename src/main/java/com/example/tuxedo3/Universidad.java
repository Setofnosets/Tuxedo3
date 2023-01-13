package com.example.tuxedo3;

public class Universidad {
    private Long Codigo;
    private String Grupo;
    private String Materia;
    private Long Creditos;
    private Long Trimestre;
    private String NombreProfesor;

    public Universidad(long Codigo, String Grupo, String Materia, long Creditos, long Trimestre, String NombreProfesor) {
        this.Codigo = Codigo;
        this.Grupo = Grupo;
        this.Materia = Materia;
        this.Creditos = Creditos;
        this.Trimestre = Trimestre;
        this.NombreProfesor = NombreProfesor;
    }

    public Universidad() {
        Codigo = -1L;
        Grupo = "";
        Materia = "";
        Creditos = -1L;
        Trimestre = -1L;
        NombreProfesor = "";
    }

    public long getCodigo() {
        return Codigo;
    }

    public void setCodigo(long codigo) {
        Codigo = codigo;
    }

    public String getGrupo() {
        return Grupo;
    }

    public void setGrupo(String grupo) {
        Grupo = grupo;
    }

    public String getMateria() {
        return Materia;
    }

    public void setMateria(String materia) {
        Materia = materia;
    }

    public long getCreditos() {
        return Creditos;
    }

    public void setCreditos(long creditos) {
        Creditos = creditos;
    }

    public long getTrimestre() {
        return Trimestre;
    }

    public void setTrimestre(long trimestre) {
        Trimestre = trimestre;
    }

    public String getNombreProfesor() {
        return NombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        NombreProfesor = nombreProfesor;
    }
}
