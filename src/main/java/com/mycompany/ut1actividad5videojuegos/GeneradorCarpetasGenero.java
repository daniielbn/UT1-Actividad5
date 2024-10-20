package com.mycompany.ut1actividad5videojuegos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class GeneradorCarpetasGenero {
    private ArrayList<Game> juegosGeneros = new ArrayList<>();
    private ArrayList<String> generos = new ArrayList<>();

    public GeneradorCarpetasGenero(ArrayList<Game> juegosGeneros, ArrayList<String> generos) {
        this.juegosGeneros = juegosGeneros;
        this.generos = generos;
    }

    public ArrayList<Game> getJuegosGeneros() {
        return juegosGeneros;
    }

    public ArrayList<String> getGeneros() {
        return generos;
    }

    public void setJuegosGeneros(ArrayList<Game> juegosGeneros) {
        this.juegosGeneros = juegosGeneros;
    }

    public void setGeneros(ArrayList<String> generos) {
        this.generos = generos;
    }

    public void generarCarpetas() {
        try {
            File directorio = new File("Resultados/Generos");
            if (!directorio.exists()) {
                directorio.mkdir();  // Crear el directorio si no existe
            }
            for (String genero : generos) {
                File directorioGenero = new File(directorio, genero);
                if (!directorioGenero.exists()) {
                    directorioGenero.mkdirs();  // Crear el subdirectorio si no existe
                }
                File fichero = new File(directorioGenero, genero + ".txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(fichero, true));

                for (Game juego : juegosGeneros) {
                    String generoModificado = juego.getGenre().replace("/", "-").trim();
                    if (generoModificado.equals(genero)) {
                        bw.write(juego.toString());  // Escribir la información específica del juego
                        bw.newLine();
                    }
                }
                System.out.println("Se ha escrito un nuevo fichero: " + fichero.getPath());
                bw.close();
            }
        } catch (Exception e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }
    }
}
