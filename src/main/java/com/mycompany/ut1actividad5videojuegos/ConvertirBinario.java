package com.mycompany.ut1actividad5videojuegos;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class ConvertirBinario {
    private String[] cabecera = new String[15];
    private ArrayList<Game> juegos = new ArrayList<>();

    public ConvertirBinario() {

    }

    public String[] getCabecera() {
        return cabecera;
    }

    public ArrayList<Game> getJuegos() {
        return juegos;
    }

    public void setCabecera(String[] cabecera) {
        this.cabecera = cabecera;
    }

    public void setJuegos(ArrayList<Game> juegos) {
        this.juegos = juegos;
    }

    private void leerFicheroTexto() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("metacritic_games.csv"));
            String linea;
            boolean primeraLinea = true;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (primeraLinea) {
                    cabecera = datos;
                    primeraLinea = false;
                } else {
                    juegos.add(new Game(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], datos[6], Integer.parseInt(datos[7]), Integer.parseInt(datos[8]), Integer.parseInt(datos[9]), Integer.parseInt(datos[10]), Integer.parseInt(datos[11]), Integer.parseInt(datos[12]), Integer.parseInt(datos[13]), Integer.parseInt(datos[14])));
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error al leer el fichero de texto: " + e.getMessage());
        }
    }

    private void escribirFicheroBinario() {
        try {
            File resultadosDir = new File("Resultados");
            if (!resultadosDir.exists()) {
                resultadosDir.mkdirs();
            }
            DataOutputStream dos = new DataOutputStream(new FileOutputStream("Resultados\\juegos.dat"));
            for (int i = 0; i < 15; i++) {
                dos.writeUTF(cabecera[i]);
            }
            for (Game juego : juegos) {
                dos.writeUTF(juego.getName());
                dos.writeUTF(juego.getPlatform());
                dos.writeUTF(juego.getDeveloper());
                dos.writeUTF(juego.getGenre());
                dos.writeUTF(juego.getNumberPlayers());
                dos.writeUTF(juego.getRating());
                dos.writeUTF(juego.getReleaseDate());
                dos.writeInt(juego.getPositiveCritics());
                dos.writeInt(juego.getNeutralCritics());
                dos.writeInt(juego.getNegativeCritics());
                dos.writeInt(juego.getPositiveUsers());
                dos.writeInt(juego.getNeutralUsers());
                dos.writeInt(juego.getNegativeUsers());
                dos.writeInt(juego.getMetaScore());
                dos.writeInt(juego.getUserScore());
            }
            dos.close();
        } catch (Exception e) {
            System.out.println("Error al escribir el fichero binario: " + e.getMessage());
        }
    }

    private void leerFicheroBinario() {
        juegos = new ArrayList<>();
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream("Resultados\\juegos.dat"));
            boolean primeraLinea = true;

            // Leer la cabecera solo una vez
            if (primeraLinea) {
                for (int i = 0; i < 15; i++) {
                    cabecera[i] = dis.readUTF();  // Leer cabeceras en el mismo orden en que fueron escritas
                }
                primeraLinea = false;
            }

            // Leer juegos
            while (dis.available() > 0) {  // Verifica si hay datos restantes
                String name = dis.readUTF();
                String platform = dis.readUTF();
                String developer = dis.readUTF();
                String genre = dis.readUTF();
                String numberPlayers = dis.readUTF();
                String rating = dis.readUTF();
                String releaseDate = dis.readUTF();
                int positiveCritics = dis.readInt();
                int neutralCritics = dis.readInt();
                int negativeCritics = dis.readInt();
                int positiveUsers = dis.readInt();
                int neutralUsers = dis.readInt();
                int negativeUsers = dis.readInt();
                int metaScore = dis.readInt();
                int userScore = dis.readInt();

                // Crear el objeto Game y añadirlo a la lista
                juegos.add(new Game(name, platform, developer, genre, numberPlayers, rating, releaseDate, positiveCritics, neutralCritics, negativeCritics, positiveUsers, neutralUsers, negativeUsers, metaScore, userScore));
            }

        } catch (EOFException e) {
            System.out.println("Fin del fichero.");
        } catch (Exception e) {
            System.out.println("Error al leer el fichero binario");
        }
    }

    public void escribirFicheroAleatorio() {
        Collections.sort(juegos);
        try {
            RandomAccessFile raf = new RandomAccessFile("Resultados\\juegosOrdenados.dat", "rw");
            for (String elemento : cabecera) {
                raf.writeBytes(String.format("%-15s", elemento)); // Cabecera
            }

            // Escribir los datos de los juegos
            for (Game juego : juegos) {
                raf.writeBytes(String.format("%-15s", juego.getName()));
                raf.writeBytes(String.format("%-15s", juego.getPlatform()));
                raf.writeBytes(String.format("%-15s", juego.getDeveloper()));
                raf.writeBytes(String.format("%-15s", juego.getGenre()));
                raf.writeBytes(String.format("%-15s", juego.getNumberPlayers()));
                raf.writeBytes(String.format("%-10s", juego.getRating()));
                raf.writeBytes(String.format("%-15s", juego.getReleaseDate() != null ? juego.getReleaseDate().toString() : ""));
                raf.writeInt(juego.getPositiveCritics());
                raf.writeInt(juego.getNeutralCritics());
                raf.writeInt(juego.getNegativeCritics());
                raf.writeInt(juego.getPositiveUsers());
                raf.writeInt(juego.getNeutralUsers());
                raf.writeInt(juego.getNegativeUsers());
                raf.writeInt(juego.getMetaScore());
                raf.writeInt(juego.getUserScore());
            }

        } catch (Exception e) {
            System.out.println("Error al escribir el fichero aleatorio");
        }
    }

    public void convertir() {
        leerFicheroTexto();
        escribirFicheroBinario();
        //GeneradorXML xml = new GeneradorXML(plataformas, juegos);
        //xml.generarXML();
        //xml.leerXML();
        //GeneradorCarpetas carpetas = new GeneradorCarpetas(xml.getJuegosXML(), xml.getPlataformas());
        //carpetas.generarCarpetas();
        //Collections.sort(juegos);
        //escribirFicheroAleatorio();
    }

    public void leer() {
        leerFicheroBinario();
        for (Game juego : juegos) {
            System.out.println(juego.toString());
        }
    }
}
