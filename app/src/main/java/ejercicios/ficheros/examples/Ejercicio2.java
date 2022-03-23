package ejercicios.ficheros.examples;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import ejercicios.ficheros.utils.Constants;
import ejercicios.ficheros.utils.EjerciciosInterface;

public class Ejercicio2 implements EjerciciosInterface {
    
    @Override
    public String getNameExample() {
        return "2. ReadFile";
    }

    @Override
    public String getDescription() {
        return "Leer el anterior fichero, mostrando su contenido por pantalla";
    }

    @Override
    public void example() {
        StringBuilder stringBuilder = new StringBuilder();

        try(FileReader fileReader = new FileReader(Constants.RESOURCES_PATH + Constants.HELLO_WORLD_FILENAME)){

            int character;
            while((character=fileReader.read()) != -1) {
                stringBuilder.append((char)character);
            }

        } catch(FileNotFoundException e) {
            System.err.println("Archivo no encontrado");

        } catch(IOException e) {
            System.err.println("Error IOException");
        }
        
        System.out.println("Contenido del archivo:\n\t" + stringBuilder.toString());
    }
    
}
