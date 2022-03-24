package ejercicios.ficheros.examples;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import ejercicios.ficheros.utils.Constants;
import ejercicios.ficheros.utils.EjerciciosInterface;

public class Ejercicio5 implements EjerciciosInterface {

    @Override
    public String getNameExample() {
        return "5. BufferedWriter - Escritura de archivos por lineas";
    }

    @Override
    public String getDescription() {
        return "Escribir en un fichero con BufferedWriter";
    }

    @Override
    public void example() {
        
        String texto = "En un lugar de la mancha...";
        String texto2 = "Hace mucho tiempo, en una tierra muy lejana...";

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Constants.RESOURCES_PATH + Constants.BUFFERED_FILENAME))) {
            bufferedWriter.write(texto);
            bufferedWriter.newLine();
            bufferedWriter.write(texto2);
            System.out.println("Archivo creado o sobrescrito!!!");
        } catch (IOException e) {
            System.err.println("Error IO: " + e.getMessage());
        }
    }
    
}
