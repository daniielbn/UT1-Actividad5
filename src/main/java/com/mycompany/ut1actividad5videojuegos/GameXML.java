package com.mycompany.ut1actividad5videojuegos;

public class GameXML {
    private String platforma;
    private String nombre;
    private String desarrollador;

    public GameXML(String platforma, String nombre, String desarrollador) {
        this.platforma = platforma;
        this.nombre = nombre;
        this.desarrollador = desarrollador;
    }

    public String getPlatforma() {
        return platforma;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDesarrollador() {
        return desarrollador;
    }

    public void setPlatforma(String platforma) {
        this.platforma = platforma;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDesarrollador(String desarrollador) {
        this.desarrollador = desarrollador;
    }

    @Override
    public String toString() {
        return "GameXML{" + "platforma=" + platforma + ", nombre=" + nombre + ", desarrollador=" + desarrollador + '}';
    }
}
