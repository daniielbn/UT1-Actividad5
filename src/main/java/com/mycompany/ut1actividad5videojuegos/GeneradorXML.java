package com.mycompany.ut1actividad5videojuegos;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GeneradorXML {
    private ArrayList<String> plataformas = new ArrayList<>();
    private ArrayList<Game> juegos = new ArrayList<>();
    private ArrayList<GameXML> juegosXML = new ArrayList<>();

    public GeneradorXML(ArrayList<String> plataformas, ArrayList<Game> juegos) {
        this.plataformas = plataformas;
        this.juegos = juegos;
    }

    public ArrayList<String> getPlataformas() {
        return plataformas;
    }

    public ArrayList<Game> getJuegos() {
        return juegos;
    }

    public ArrayList<GameXML> getJuegosXML() {
        return juegosXML;
    }

    public void setPlataformas(ArrayList<String> plataformas) {
        this.plataformas = plataformas;
    }

    public void setJuegos(ArrayList<Game> juegos) {
        this.juegos = juegos;
    }

    public void setJuegosXML(ArrayList<GameXML> juegosXML) {
        this.juegosXML = juegosXML;
    }

    public void formatearPlataforma() {
        for (String plataforma : plataformas) {
            if (plataforma == null || plataforma.isEmpty()) {
                plataforma = "Desconocida";
            }
            String plataformaFormateada = plataforma.replaceAll("[^a-zA-Z0-9]", "");
            if (Character.isDigit(plataformaFormateada.charAt(0))) {
                plataforma = "Plataforma " + plataformaFormateada;
            }
        }
    }

    public void generarXML() {
        try {
            formatearPlataforma();
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element root = document.createElement("Plataformas");
            document.appendChild(root);

            for (String plataforma : plataformas) {
                Element plataformaElement = document.createElement(plataforma);
                root.appendChild(plataformaElement);

                Element juegosElement = document.createElement("Juegos");
                plataformaElement.appendChild(juegosElement);
                for (Game juego : juegos) {
                    if (juego.getPlatform().equals(plataforma)) {
                        Element juegoElement = document.createElement("Juego");
                        plataformaElement.appendChild(juegoElement);
                        Element nombreElement = document.createElement("Nombre");
                        nombreElement.appendChild(document.createTextNode(juego.getName()));
                        juegoElement.appendChild(nombreElement);
                        Element desarrolladorElement = document.createElement("Desarrollador");
                        desarrolladorElement.appendChild(document.createTextNode(juego.getDeveloper()));
                        juegoElement.appendChild(desarrolladorElement);
                    }
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(document);

            StreamResult result = new StreamResult("Resultados\\juegos.xml");
            transformer.transform(domSource, result);

            System.out.println("Archivo XML generado con éxito.");

        } catch (Exception e) {
            System.out.println("Error al generar el XML");
        }
    }

    public void leerXML() {
        try {
            formatearPlataforma();
            File xmlFile = new File("Resultados\\juegos.xml");
            if (!xmlFile.exists()) {
                throw new FileNotFoundException("El archivo juegos.xml no existe en la carpeta Resultados");
            }

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);

            document.getDocumentElement().normalize();
            for (String plataforma : plataformas) {
                NodeList plataformas = document.getElementsByTagName(plataforma);

                for (int i = 0; i < plataformas.getLength(); i++) {
                    Node plataformaNode = plataformas.item(i);
                    if (plataformaNode.getNodeType() == plataformaNode.ELEMENT_NODE) {
                        Element plataformaElement = (Element) plataformaNode;
                        String nombrePlataforma = plataformaElement.getTextContent();

                        NodeList juegos = plataformaElement.getElementsByTagName("Juego");
                        for (int j = 0; j < juegos.getLength(); j++) {
                            Node juegoNode = juegos.item(j);
                            if (juegoNode.getNodeType() == juegoNode.ELEMENT_NODE) {
                                Element juegoElement = (Element) juegoNode;
                                String nombreJuego = juegoElement.getElementsByTagName("Nombre").item(0).getTextContent();
                                String desarrollador = juegoElement.getElementsByTagName("Desarrollador").item(0).getTextContent();
                                juegosXML.add(new GameXML(nombrePlataforma, nombreJuego, desarrollador));
                            }
                        }
                    }
                }
            }


        } catch (Exception e) {
            System.out.println("Error al leer el XML: " + e.getMessage());
        }
    }

}
