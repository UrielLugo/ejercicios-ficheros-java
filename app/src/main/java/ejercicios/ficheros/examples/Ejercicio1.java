package ejercicios.ficheros.examples;

import java.io.FileWriter;
import java.io.IOException;

import ejercicios.ficheros.utils.Constants;
import ejercicios.ficheros.utils.EjerciciosInterface;

public class Ejercicio1 implements EjerciciosInterface {

    @Override
    public String getNameExample() {
        return "1. WriteFile";
    }

    @Override
    public String getDescription() {
        return "Escribir en un fichero \"Hola mundo\"";
    }

    @Override
    public void example() {
        final String CONTENT_FILE = "Hola mundo desde un archivo";

        try(FileWriter fileWriter = new FileWriter(Constants.RESOURCES_PATH + Constants.HELLO_WORLD_FILENAME)) { // Busca archivo y si no lo encuentra lo crea

            fileWriter.write(CONTENT_FILE); // Escribe en archivo, sobreescribe lo existente
            
            fileWriter.close(); // Cierra stream de archivo

            System.out.println("Archivo " + Constants.HELLO_WORLD_FILENAME + " encontrado o creado!");

        }catch(IOException e) {
            System.err.println(e);
        }
    }
    
}
