package org.example;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DiccionarioPalabras {
    public static void main(String[] args) {
        // Definir el texto directamente en el código
        String texto = "Este es un texto de ejemplo. Este texto es para contar palabras y mostrar un diccionario.";

        // Contar palabras y sus longitudes
        Map<String, Integer> diccionario = contarPalabras(texto);

        // Mostrar el diccionario
        System.out.println("Diccionario de palabras y sus longitudes:");
        for (Map.Entry<String, Integer> entrada : diccionario.entrySet()) {
            System.out.println(entrada.getKey() + ": " + entrada.getValue() + " caracteres.");
        }

        // Mostrar categorías de longitud de palabras
        Map<Integer, Integer> categorias = mostrarCategorias(diccionario);

        // Encontrar la longitud máxima y la palabra en esa categoría
        int longitudMaxima = obtenerLongitudMaxima(categorias);
        String palabraMasLargaEnCategoria = encontrarPalabraPorLongitud(diccionario, longitudMaxima);

        // Mostrar resultados
        System.out.println("\nCantidad de palabras por longitud:");
        for (Map.Entry<Integer, Integer> entrada : categorias.entrySet()) {
            System.out.println("Longitud " + entrada.getKey() + ": " + entrada.getValue() + " palabras.");
        }

        System.out.println("\nLa palabra más larga en la categoría de longitud " + longitudMaxima + " es: \"" + palabraMasLargaEnCategoria + "\"");
    }

    public static Map<String, Integer> contarPalabras(String texto) {
        Map<String, Integer> diccionario = new HashMap<>();

        // Definimos el patrón para encontrar palabras
        Pattern pattern = Pattern.compile("\\b\\w+\\b");
        Matcher matcher = pattern.matcher(texto);

        while (matcher.find()) {
            String palabra = matcher.group().toLowerCase(); // Convertimos a minúsculas
            diccionario.put(palabra, palabra.length()); // Guardamos la longitud de la palabra
        }

        return diccionario;
    }

    public static Map<Integer, Integer> mostrarCategorias(Map<String, Integer> diccionario) {
        // Usamos un mapa para contar las palabras por longitud
        Map<Integer, Integer> categorias = new HashMap<>();

        for (Integer longitud : diccionario.values()) {
            categorias.put(longitud, categorias.getOrDefault(longitud, 0) + 1);
        }

        return categorias;
    }

    public static int obtenerLongitudMaxima(Map<Integer, Integer> categorias) {
        int longitudMaxima = 0;

        for (Integer longitud : categorias.keySet()) {
            if (longitud > longitudMaxima) {
                longitudMaxima = longitud;
            }
        }

        return longitudMaxima;
    }

    public static String encontrarPalabraPorLongitud(Map<String, Integer> diccionario, int longitudMaxima) {
        for (Map.Entry<String, Integer> entrada : diccionario.entrySet()) {
            if (entrada.getValue() == longitudMaxima) {
                return entrada.getKey();
            }
        }
        return "";
    }
}
