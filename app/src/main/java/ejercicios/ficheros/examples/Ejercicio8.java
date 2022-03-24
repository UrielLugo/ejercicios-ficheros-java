package ejercicios.ficheros.examples;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import ejercicios.ficheros.utils.Constants;
import ejercicios.ficheros.utils.EjerciciosInterface;

public class Ejercicio8 implements EjerciciosInterface {

    @Override
    public String getNameExample() {
        return "8. Scanner. Imprimir numeros mayor a entrada";
    }

    @Override
    public String getDescription() {
        return "Pide un numero por teclado y lee el fichero usando Scanner, pero muestra solo los que sean mayores que el numero introducido";
    }

    @Override
    public void example() {
        String stringFilename = getNumberString(Constants.RESOURCES_PATH + Constants.NUMBERS_FILENAME);
        System.out.println("Numeros de archivo '" + Constants.NUMBERS_FILENAME + "': ");
        System.out.println(stringFilename.isEmpty() ? "No hay valores en el archivo" : stringFilename);
    }

    public String getNumberString(String pathFilename) {
        try(Scanner scanner = new Scanner(new File(pathFilename))) {
            
            StringBuilder stringBuilder = new StringBuilder();
            String showInputDialog = JOptionPane.showInputDialog(null, "Introduce un numero", "Numeros mayores a entrada", JOptionPane.QUESTION_MESSAGE);
            int numUsuario = Integer.parseInt(showInputDialog);

            while(scanner.hasNextInt()) {
                int nextInt = scanner.nextInt();
                if(nextInt > numUsuario) {
                    stringBuilder.append(nextInt + " ");
                }
            }
            return stringBuilder.toString();
        } catch(FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.err.println("Entrada no valida, introduce un numero: " + e.getMessage());
        } catch(NoSuchElementException e) {
            System.err.println("No se encuentra el scanner: " + e.getMessage());
        } catch(IllegalStateException e) {
            System.err.println("Scanner cerrado: " + e.getMessage());
        } catch(NumberFormatException e) {
            System.err.println("No se introdujo un numero: " + e.getMessage()); 
        }

        return null;
    }
    
}
