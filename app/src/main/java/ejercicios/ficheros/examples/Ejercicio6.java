package ejercicios.ficheros.examples;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

import ejercicios.ficheros.utils.Constants;
import ejercicios.ficheros.utils.EjerciciosInterface;

public class Ejercicio6 implements EjerciciosInterface {

    @Override
    public String getNameExample() {
        return "6. BufferedReader - Lectura de archivos por lineas";
    }

    @Override
    public String getDescription() {
        return "Leer un fichero con BufferedReader";
    }

    @Override
    public void example() {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(Constants.RESOURCES_PATH + Constants.BUFFERED_FILENAME))) {
            //bufferedReader.mark(1);
            streamForm(bufferedReader);
            //bufferedReader.reset();
            whileForm(bufferedReader);
        } catch(IOException e) {
            System.err.println("Error IO: " + e.getMessage());
        }
    }

    public void whileForm(BufferedReader bufferedReader) throws IOException {
        String linea;
        int contador = 0;
        while((linea = bufferedReader.readLine()) != null) {
            System.out.println("Linea " + (++contador) + ": " + linea);
        }
    }

    public void streamForm(BufferedReader bufferedReader) {
        Stream<String> lineas = bufferedReader.lines();
        System.out.println("BufferedReader usando stream");
        lineas.forEach(System.out::println);

    }
    

}
