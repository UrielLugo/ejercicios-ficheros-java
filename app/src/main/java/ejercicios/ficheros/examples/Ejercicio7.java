package ejercicios.ficheros.examples;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import ejercicios.ficheros.utils.Constants;
import ejercicios.ficheros.utils.EjerciciosInterface;

public class Ejercicio7 implements EjerciciosInterface {

    @Override
    public String getNameExample() {
        return "7. Scanner. Impresion de numeros";
    }

    @Override
    public String getDescription() {
        return "Teniendo un fichero donde en cada linea hay un numero, leer el fichero usando Scanner, muestra el contenido por pantalla";
    }

    @Override
    public void example() {
        
        try(Scanner scanner = new Scanner(new File(Constants.RESOURCES_PATH + Constants.NUMBERS_FILENAME))) {
            System.out.println("Numeros de archivo '" + Constants.NUMBERS_FILENAME + "': ");
            while(scanner.hasNextInt()) {
                System.out.print(scanner.nextInt() + " ");
            }
        } catch(FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Entrada no valida, introduce un numero: " + e.getMessage());
        } catch(NoSuchElementException e) {
            System.out.println("No se encuentra el scanner: " + e.getMessage());
        } catch(IllegalStateException e) {
            System.out.println("Scanner cerrado: " + e.getMessage());
        }
    }
    
}
