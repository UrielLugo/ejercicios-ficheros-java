package ejercicios.ficheros.examples;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.swing.JOptionPane;

import ejercicios.ficheros.utils.EjerciciosInterface;

public class Ejercicio3 implements EjerciciosInterface {

    @Override
    public String getNameExample() {
        return "3. Conteo de caracteres en archivo";
    }

    @Override
    public String getDescription() {
        
        return "Contar el numero de vocales, consonantes y numeros que hay en un fichero pasando la ruta por teclado";
    }

    @Override
    public void example() {

        String ruta = null;
        do {
            ruta = JOptionPane.showInputDialog(null, "Interta la ruta del fichero", "Ruta de Archivo", 1);
        }while(ruta == null);

        try {
            Instant tiempoInicial = Instant.now();
            fileCount(ruta);
            System.out.println("Tiempo transcurrido (ms): " + Duration.between(tiempoInicial, Instant.now()).toMillis());
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            this.example();
        }
    }

    private void fileCount(String path) throws FileNotFoundException{
        int contadorNumeros = 0;
        int contadorMayusculas = 0;
        int contadorMinusculas = 0;
        int contadorEspacios = 0;
        int contadorVocales = 0;
        int contadorConsonantes = 0;
        int contadorGeneral = 0;

        Character[] vocales = {'a','e','i','o','u','A','E','I','O','U'}; 

        try(FileReader fileReader = new FileReader(path)) {

            int character;
            while((character = fileReader.read()) != -1) {
                if((Character.isDigit(character))) {
                    contadorNumeros++;
                }else if(Character.isWhitespace(character)) {
                    contadorEspacios++;
                }else if(Character.isUpperCase(character)) {
                    contadorMayusculas++;
                }else if(Character.isLowerCase(character)) {
                    contadorMinusculas++;
                }

                if(Arrays.asList(vocales).contains(Character.valueOf((char)character))) {
                    contadorVocales++;
                }else if((character >= 65 && character <= 90) || (character >= 97 && character <= 122)) {
                    contadorConsonantes++;
                }
                contadorGeneral++;
            }

        } catch(FileNotFoundException e) {
            throw new FileNotFoundException("Archivo no encontrado: " + e);
        } catch(IOException e) {
            System.err.println("IOException: " + e);
        }

        System.out.println("Conteo de Archivo: ");
        System.out.println("Mayusculas: " + contadorMayusculas);
        System.out.println("Minusculas: " + contadorMinusculas);
        System.out.println("Digitos: " + contadorNumeros);
        System.out.println("Espacios: " + contadorEspacios);
        System.out.println("\nEn paralelo, las vocales y consonantes son las siguientes: ");
        System.out.println("Vocales: " + contadorVocales);
        System.out.println("Consonantes: " + contadorConsonantes);
        System.out.println("\nTotal de caracteres en archivo: " + contadorGeneral);
    }
    
}
