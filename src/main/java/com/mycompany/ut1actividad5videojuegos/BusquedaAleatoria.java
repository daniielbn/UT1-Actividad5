package com.mycompany.ut1actividad5videojuegos;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BusquedaAleatoria {
    private ArrayList<Game> juegos = new ArrayList<>();
    /**
     * Devuelve el tamaño de un registro, correspondiente a la clase Persona, en bytes.
     * @return Tamaño del registro de la persona en bytes.
     */
    public int tamanioRegistro() {
        int tamanioName = 15 * 2;
        int tamanioPlatform = 15 * 2;
        int tamanioDeveloper = 15 * 2;
        int tamanioGenre = 15 * 2;
        int tamanioNumberPlayers = 15 * 2;
        int tamanioRating = 15 * 2;
        int tamanioReleaseDate = 15 * 2;
        int tamanioPositiveCritics = Integer.BYTES;
        int tamanioNeutralCritics = Integer.BYTES;
        int tamanioNegativeCritics = Integer.BYTES;
        int tamanioPositiveUsers = Integer.BYTES;
        int tamanioNeutralUsers = Integer.BYTES;
        int tamanioNegativeUsers = Integer.BYTES;
        int tamanioMetaScore = Integer.BYTES;
        int tamanioUserScore = Integer.BYTES;

        return tamanioName + tamanioPlatform + tamanioDeveloper + tamanioGenre + tamanioNumberPlayers + tamanioRating + tamanioReleaseDate + tamanioPositiveCritics + tamanioNeutralCritics + tamanioNegativeCritics + tamanioPositiveUsers + tamanioNeutralUsers + tamanioNegativeUsers + tamanioMetaScore + tamanioUserScore;
    }

    /**
     * Establece la longitud fija de un String y lo devuelve.
     * @param raf RandomAccessFile para leer el String.
     * @param longitud Longitud fija del String.
     * @return String con longitud fija.
     */
    public String leerString(RandomAccessFile raf, int longitud) {
        try {
            byte[] bytes = new byte[longitud * 2];
            raf.readFully(bytes);
            return new String(bytes).trim();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    /**
     * Lee un registro de un fichero de manera aleatoria y lo devuelve.
     * @param raf RandomAccessFile para leer el registro.
     * @return Persona leída del fichero.
     */
    public Game leerRegistro(RandomAccessFile raf) {
        try {
            String name = leerString(raf, 15);
            String plataform = leerString(raf, 15);
            String developer = leerString(raf, 15);
            String genre = leerString(raf, 15);
            String numberPlayers = leerString(raf, 15);
            String rating = leerString(raf, 15);
            String releaseDate = leerString(raf, 15);
            int positiveCritics = raf.readInt();
            int neutralCritics = raf.readInt();
            int negativeCritics = raf.readInt();
            int positiveUsers = raf.readInt();
            int neutralUsers = raf.readInt();
            int negativeUsers = raf.readInt();
            int metaScore = raf.readInt();
            int userScore = raf.readInt();

            return new Game(name, plataform, developer, genre, numberPlayers, rating, releaseDate, positiveCritics, neutralCritics, negativeCritics, positiveUsers, neutralUsers, negativeUsers, metaScore, userScore);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


public List<Long> buscarJuegosRatingM() {
        List<Long> posiciones = new ArrayList<>();
        try (RandomAccessFile raf = new RandomAccessFile("Resultados\\juegosOrdenados.dat", "r")) {
            long numRegistros = raf.length() / tamanioRegistro();
            for (long i = 0; i < numRegistros; i++) {
                raf.seek(i * tamanioRegistro());
                Game juego = leerRegistro(raf);
                if (juego != null && "M".equals(juego.getRating())) {
                    posiciones.add(i);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return posiciones;
    }

    // Método para permitir al usuario seleccionar y visualizar un juego
    public void seleccionarYVisualizarJuego() {
        List<Long> posiciones = buscarJuegosRatingM();
        if (posiciones.isEmpty()) {
            System.out.println("No se encontraron juegos con rating M.");
            return;
        }

        System.out.println("Juegos con rating M encontrados en las siguientes posiciones:");
        for (Long posicion : posiciones) {
            System.out.println("Posición: " + posicion);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Seleccione la posición del juego que desea visualizar: ");
        long seleccion = scanner.nextLong();

        if (posiciones.contains(seleccion)) {
            try (RandomAccessFile raf = new RandomAccessFile("Resultados\\juegosOrdenados.dat", "r")) {
                raf.seek(seleccion * tamanioRegistro());
                Game juego = leerRegistro(raf);
                System.out.println("Juego seleccionado: " + juego);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Posición no válida.");
        }
    }
}
