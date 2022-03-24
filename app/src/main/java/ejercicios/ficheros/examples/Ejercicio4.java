package ejercicios.ficheros.examples;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import ejercicios.ficheros.utils.Constants;
import ejercicios.ficheros.utils.EjerciciosInterface;

public class Ejercicio4 implements EjerciciosInterface {

    @Override
    public String getNameExample() {
        return "4. Escribir fichero a Mayusculas";
    }

    @Override
    public String getDescription() {
        return "Eliminar espacios de un fichero y convertir a mayuscula todo. Usar bufferedWriter";
    }

    @Override
    public void example() {
        System.out.println("Texto orignal: ");
        String textFile = getTextFile(Constants.RESOURCES_PATH + Constants.LOREM_IPSUM_FILENAME);
        System.out.println(textFile);

        System.out.println("Texto con mayusculas y sin espacios: ");
        String textFileUpper = textFile.toUpperCase();
        textFileUpper = Pattern.compile(" ").matcher(textFileUpper).replaceAll("");
        
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Constants.RESOURCES_PATH + Constants.UPPERCASE_FILENAME))) {
            bufferedWriter.write(textFileUpper);
            System.out.println("Texto modificado: ");
            System.out.println(textFileUpper);
        } catch (IOException e) {
            System.out.println("Error IO: " + e.getMessage());
        }
        
    }

    public String getTextFile(String pathFilename) {
        StringBuilder textFile = new StringBuilder();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(pathFilename))) {
            Stream<String> textStream = bufferedReader.lines();
            textStream.forEach(t -> textFile.append(t + "\n"));
        } catch (IOException e) {
            System.out.println("Error IO: " + e.getMessage());
        }

        return textFile.toString();
    }
    
}
