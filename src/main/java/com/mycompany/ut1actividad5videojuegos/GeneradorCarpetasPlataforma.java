package com.mycompany.ut1actividad5videojuegos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class GeneradorCarpetasPlataforma {
    private ArrayList<GameXML> juegos = new ArrayList<>();
    private ArrayList<String> plataformas = new ArrayList<>();

    public GeneradorCarpetasPlataforma(ArrayList<GameXML> juegos, ArrayList<String> plataformas) {
        this.juegos = juegos;
        this.plataformas = plataformas;
    }

    public ArrayList<GameXML> getJuegos() {
        return juegos;
    }

    public ArrayList<String> getPlataformas() {
        return plataformas;
    }

    public void setJuegos(ArrayList<GameXML> juegos) {
        this.juegos = juegos;
    }

    public void setPlataformas(ArrayList<String> plataformas) {
        this.plataformas = plataformas;
    }

    public void generarCarpetas() {
        try {
            File directorio = new File("Resultados/Plataformas");
            if (!directorio.exists()) {
                directorio.mkdir();  // Crear el directorio si no existe
            }

            for (String plataforma : plataformas) {
                File directorioPlataforma = new File(directorio, plataforma);
                if (!directorioPlataforma.exists()) {
                    directorioPlataforma.mkdirs();  // Crear el subdirectorio si no existe
                }
                File fichero = new File(directorioPlataforma,plataforma + ".txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(fichero, true));

                for (GameXML juego : juegos) {
                    if (plataforma.equalsIgnoreCase(juego.getPlatforma())) {
                        bw.write("========================================================================================================");
                        bw.newLine();
                        bw.write("Nombre: " + juego.getNombre());
                        bw.newLine();
                        bw.write("Desarrollador: " + juego.getDesarrollador());
                        bw.newLine();
                    }
                }
                bw.write("========================================================================================================");
                bw.close();
                System.out.println("Fichero escrito correctamente: " + fichero.getPath());
            }
        } catch (Exception e) {
            System.out.println("Error al generar las carpetas");
        }
    }
}
