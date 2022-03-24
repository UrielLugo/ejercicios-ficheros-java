package ejercicios.ficheros.examples;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import ejercicios.ficheros.utils.Constants;
import ejercicios.ficheros.utils.EjerciciosInterface;

public class Ejercicio9 implements EjerciciosInterface {

    @Override
    public String getNameExample() {
        return "9. Guardar numeros filtrados";
    }

    @Override
    public String getDescription() {
        return "Lo mismo que el ejercicio anterior pero guarda el resultado en otro fichero, usar PrintWriter";
    }

    @Override
    public void example() {
        System.out.println("fssdfsdsfsdgsfgenmri");
        Ejercicio8 ejercicioAnterior = new Ejercicio8();

        String writeFilename = Constants.RESOURCES_PATH + Constants.WRITE_NUMBERS_FILENAME;
        String numberString = ejercicioAnterior.getNumberString(Constants.RESOURCES_PATH + Constants.NUMBERS_FILENAME);
        
        try(PrintWriter printWriter = new PrintWriter(new File(writeFilename));
            Scanner scanner = new Scanner(numberString)) {
            
            while(scanner.hasNextInt()) {
                int nextInt = scanner.nextInt();
                System.out.println(nextInt);
                printWriter.println(nextInt);
            }

            System.out.println("Archivo escrito correctamente");
        } catch(FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + e.getMessage());   
        } catch (InputMismatchException e) {
            System.err.println("Entrada no valida, introduce un numero: " + e.getMessage());
        } catch(NoSuchElementException e) {
            System.err.println("No se encuentra el scanner: " + e.getMessage());
        } catch(IllegalStateException e) {
            System.err.println("Scanner cerrado: " + e.getMessage());
        }
        
    }
    
}
