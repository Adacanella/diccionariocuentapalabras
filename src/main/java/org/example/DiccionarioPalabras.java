package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class DiccionarioPalabras {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario que ingrese el texto
        System.out.println("Por favor, ingrese el texto (debe comenzar con mayúscula y terminar con punto):");
        String texto = scanner.nextLine();

        // Validar que el texto comience con mayúscula y termine con punto
        while (!esTextoValido(texto)) {
            System.out.println("El texto debe comenzar con una letra mayúscula y terminar con un punto. Inténtelo de nuevo:");
            texto = scanner.nextLine();
        }

        // Contar palabras y sus longitudes
        Map<String, Integer> diccionario = contarPalabras(texto);

        // Mostrar conteo de palabras por longitud
        Map<Integer, Integer> conteoPorLongitud = contarPalabrasPorLongitud(diccionario);
        mostrarConteoPorLongitud(conteoPorLongitud);

        // Contar frases, palabras y caracteres por frase
        contarPalabrasYCaracteresPorFrase(texto);

        // Contar palabras y letras totales
        int totalLetras = contarLetras(diccionario);
        System.out.println("Total de letras en el texto: " + totalLetras);

        scanner.close(); // Cerrar el scanner
    }

    public static boolean esTextoValido(String texto) {
        return texto.length() > 0 && Character.isUpperCase(texto.charAt(0)) && texto.endsWith(".");
    }

    public static Map<String, Integer> contarPalabras(String texto) {
        Map<String, Integer> diccionario = new HashMap<>();
        Pattern pattern = Pattern.compile("\\b[\\w']+\\b"); // Permite apóstrofes en palabras
        Matcher matcher = pattern.matcher(texto);

        while (matcher.find()) {
            String palabra = matcher.group().toLowerCase(); // Convertimos a minúsculas
            diccionario.put(palabra, palabra.length());
        }

        return diccionario;
    }

    public static Map<Integer, Integer> contarPalabrasPorLongitud(Map<String, Integer> diccionario) {
        Map<Integer, Integer> conteoPorLongitud = new HashMap<>();
        for (Integer longitud : diccionario.values()) {
            conteoPorLongitud.put(longitud, conteoPorLongitud.getOrDefault(longitud, 0) + 1);
        }
        return conteoPorLongitud;
    }

    public static void mostrarConteoPorLongitud(Map<Integer, Integer> conteoPorLongitud) {
        System.out.println("Conteo de palabras por longitud:");
        for (Map.Entry<Integer, Integer> entrada : conteoPorLongitud.entrySet()) {
            System.out.printf("%d caracteres: %d palabra(s).%n", entrada.getKey(), entrada.getValue());
        }
    }

    public static void contarPalabrasYCaracteresPorFrase(String texto) {
        String[] frases = texto.split("(?<=[.!?])\\s*"); // Divide el texto en frases

        for (String frase : frases) {
            String fraseTrimmed = frase.trim();
            int cantidadPalabras = contarPalabras(fraseTrimmed).size(); // Cuenta las palabras en la frase
            int cantidadCaracteres = fraseTrimmed.replace(" ", "").length(); // Cuenta los caracteres sin espacios
            System.out.printf("Frase: \"%s\" - Cantidad de palabras: %d, Cantidad de caracteres: %d%n", fraseTrimmed, cantidadPalabras, cantidadCaracteres);
        }
    }

    public static int contarLetras(Map<String, Integer> diccionario) {
        return diccionario.values().stream().mapToInt(Integer::intValue).sum();
    }
}

