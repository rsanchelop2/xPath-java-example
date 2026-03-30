package es.masanz.ut7;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    public static final String BASE_PATH = "doc/data/";
    public static final String FILE_NAME = "ejemplo.txt";

    public static void main(String[] args) throws IOException {
        String url = "https://www.w3schools.com/html/default.asp";
        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.selectXpath("//div[@id='leftmenuinnerinner']/a");

        int id = 1;
        StringBuilder xmlFinal = new StringBuilder();
        xmlFinal.append("<xml>\n");

        for (Element element : elements) {
            String titulo = element.text();
            String enlace = element.attr("abs:href");

            xmlFinal.append("<h1 id=\"").append(id++).append("\">\n");
            xmlFinal.append("\t<titulo>").append(titulo).append("</titulo>\n");
            xmlFinal.append("\t<enlace>").append(enlace).append("</enlace>\n");
            xmlFinal.append("\t<h2>").append(titulo).append(" examples</h2>\n");
            xmlFinal.append("</h1>\n");

            System.out.println(element);
        }

        xmlFinal.append("</xml>");

        escribir(FILE_NAME, xmlFinal.toString(), false);
    }

//    public static void main(String[] args) throws IOException {
//        String url = "https://www.aemet.es/xml/municipios_h/localidad_h_31201.xml";
//        Document doc = Jsoup.connect(url).get();
//        Elements elements = doc.selectXpath("//prediccion/dia[@fecha='2026-03-30']");
//        for (Element element : elements) {
//            System.out.println(element.text());
//        }
//        escribir(FILE_NAME, elements.text(), true);
//    }

    private static void escribir(String nombreFichero, String texto, boolean mantenerTexto) {
        PrintWriter file = null;
        try {
            file = new PrintWriter(new BufferedWriter(new FileWriter(BASE_PATH+nombreFichero, mantenerTexto)));
            file.println(texto);
            file.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}