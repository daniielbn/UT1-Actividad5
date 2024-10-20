package com.mycompany.ut1actividad5videojuegos;

import java.util.ArrayList;

public class GeneradorDisparidad {
    private ArrayList<Game> juegos = new ArrayList<>();

    public GeneradorDisparidad(ArrayList<Game> juegos) {
        this.juegos = juegos;
    }

    public ArrayList<Game> getJuegos() {
        return juegos;
    }

    public void setJuegos(ArrayList<Game> juegos) {
        this.juegos = juegos;
    }

    public void mostrarDisparidad(boolean opcion) {
        Game juegoDisparidad = null;
        int diferencia = 0;
        int temp = 0;
        for (Game juego : juegos) {
            if (opcion) {
                diferencia = juego.getMetaScore() - juego.getUserScore();
                if (diferencia > 0) {
                    if (diferencia > temp) {
                        temp = diferencia;
                        juegoDisparidad = juego;
                    }
                }
            } else {
                diferencia = juego.getUserScore() - juego.getMetaScore();
                if (diferencia > 0) {
                    if (diferencia > temp) {
                        temp = diferencia;
                        juegoDisparidad = juego;
                    }
                }
            }
        }
        if (opcion) {
            System.out.println("El juego con mayor disparidad entre notas de metacritic y usuario es: " + juegoDisparidad.getName() + "\nPuntuación Metacritic: " + juegoDisparidad.getMetaScore() + "\nPuntuación Usuario: " + juegoDisparidad.getUserScore() + "\nDiferencia: " + (juegoDisparidad.getMetaScore() - juegoDisparidad.getUserScore()));
        } else {
            System.out.println("El juego con mayor disparidad entre notas de usuario y metacritic es: " + juegoDisparidad.getName() + "\nPuntuación Usuario: " + juegoDisparidad.getUserScore() + "\nPuntuación Metacritic: " + juegoDisparidad.getMetaScore() + "\nDiferencia: " + (juegoDisparidad.getUserScore() - juegoDisparidad.getMetaScore()));
        }
    }
}
