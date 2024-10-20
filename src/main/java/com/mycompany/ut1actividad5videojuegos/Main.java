package com.mycompany.ut1actividad5videojuegos;

import java.util.ArrayList;

public class Main {
    private static ArrayList<Game> juegos = new ArrayList<>();
    private static final ArrayList<String> plataformas = new ArrayList<>();
    private static final ArrayList<String> generos = new ArrayList<>();

    private static ArrayList<String> getPlataformas() {
        for (Game juego : juegos) {
            if (!plataformas.contains(juego.getPlatform())) {
                plataformas.add(juego.getPlatform().trim());
            }
        }
        return plataformas;
    }

    private static ArrayList<String> getGeneros() {
        for (Game juego : juegos) {
            String genero = juego.getGenre().replace("/", "-").trim();
            if (!generos.contains(genero)) {
                generos.add(genero);
            }
        }
        return generos;
    }

    private static ArrayList<Game> getjuegosGeneros() {
        ArrayList<Game> juegosGeneros = new ArrayList<>();
        for (String genero : generos) {
            for (Game juego : juegos) {
                if (juego.getGenre().equals(genero)) {
                    if (juego.getMetaScore() >= 80) {
                        juegosGeneros.add(juego);
                    }
                }
            }
        }
        return juegosGeneros;
    }

    public static void main(String[] args) {
        ConvertirBinario convertirBinario = new ConvertirBinario();

        // Obligatorio llamar a este método para recuperar los juegos del fichero "metacritics_games.csv".
        convertirBinario.convertir();

        // Leer los juegos del fichero binario, y los ordena por fecha de lanzamiento.
        //convertirBinario.escribirFicheroAleatorio();
        // Lee los juegos del fichero "juegosOrdenados.dat"
        // convertirBinario.leer();

        juegos = convertirBinario.getJuegos();
        // Crear y leer XMl.
        // GeneradorXML xml = new GeneradorXML(getPlataformas(), juegos);
        // xml.generarXML();
        // xml.leerXML();
        // ArrayList<GameXML> juegosXML = xml.getJuegosXML();
        // for (GameXML juego : juegosXML) {
        //    System.out.println(juego.toString());
        //}

        // Lee el fichero XML y crear una estructura de carpetas con todas las plataformas y juegos de la misma.
        //GeneradorCarpetasPlataforma carpetas = new GeneradorCarpetasPlataforma(xml.getJuegosXML(), xml.getPlataformas());
        //carpetas.generarCarpetas();

        // Crea árbol de directorios con todos los géneros, y añade a los ficheros de cada género los juegos que pertenecen a él y tengan una puntuación de metacritic superior a 8 (80).
        //GeneradorCarpetasGenero carpetasGenero = new GeneradorCarpetasGenero(getjuegosGeneros(), getGeneros());
        //carpetasGenero.generarCarpetas();

        // Muestra el juego con mayor disparidad entre las notas de metacritic y usuario.
        // GeneradorDisparidad disparidad = new GeneradorDisparidad(juegos);
        // disparidad.mostrarDisparidad(false);
        // System.out.println("");
        // disparidad.mostrarDisparidad(true);

        // Muestra los juegos con rating M.
        // BusquedaAleatoria busqueda = new BusquedaAleatoria();
        // busqueda.seleccionarYVisualizarJuego();
    }
}
