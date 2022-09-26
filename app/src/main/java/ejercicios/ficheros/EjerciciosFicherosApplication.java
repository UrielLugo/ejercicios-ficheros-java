/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ejercicios.ficheros;

import ejercicios.ficheros.utils.EjerciciosInterface;
import ejercicios.ficheros.utils.FindClass;

import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class EjerciciosFicherosApplication {

    private static final String EXCERCISE_CLASS_STRING = "ejercicios.ficheros.examples.Ejercicio";

    // https://stackoverflow.com/questions/19766566/java-multiple-scanners
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        
        //System.out.println("Delimiter pattern: " + scanner.delimiter());

        System.out.println("Ejercicios disponibles: ");
        Class<?>[] classes = new Class[0];
        try {
            classes = FindClass.getClasses("ejercicios.ficheros.examples");
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        String[] strings = Arrays.stream(classes).map(Class::getSimpleName).sorted().toArray(String[]::new);
        for(int i=1; i<= strings.length; i++) {
            if(i%5 == 0 || i==strings.length){
                System.out.println();
            }else {
                System.out.print(strings[i-1] + " | ");
            }
        }

        int excerciseNumber = 0;
        while(true) {
            System.out.print("Ingrese el numero del ejercicio: ");

            if(scanner.hasNextInt()) {
                excerciseNumber = Integer.parseInt(scanner.next("\\d+"));
                scanner.nextLine();
            }else {
                System.out.println("Ingrese un valor numerico");
                scanner.nextLine();
                continue;
            }

            EjerciciosInterface exerciseClass = findClass(EXCERCISE_CLASS_STRING + excerciseNumber);
            if(exerciseClass == null) {
                System.out.println("Favor de ingresar un ejercicio valido.");
                continue;
            }else {
                initExcersise(exerciseClass);
                String repeat = "";
                while(true) {
                    try {
                        System.out.print("Desea ejecutar otro ejercicio? (S/N): ");
                        repeat = scanner.next(Pattern.compile("S|s|N|n"));
                        scanner.nextLine();
                        break;
                    } catch(InputMismatchException e) {
                        scanner.nextLine();
                        System.err.println("\nValor introducido incorrecto, introducir: S/s/N/n");
                        continue;
                    }
                }
                if(repeat.equals("s") || repeat.equals("S")) {
                    continue;
                }
                break;
            }
        }

        scanner.close();
    }

    public static EjerciciosInterface findClass(String className) {
        try {
            Class<?> exerciseClass = Class.forName(className);
            return (EjerciciosInterface) exerciseClass.newInstance();
        } catch (ClassNotFoundException e) {
            System.err.println("No se encontro la clase: " + className);
        } catch(InstantiationException e) {
            System.err.println("Error de instanciación: " + e.getMessage());
        } catch(IllegalAccessException e) {
            System.err.println("Acceso ilegal: " + e.getMessage());
        }
        return null;
    }

    public static void initExcersise(EjerciciosInterface excersise) {
        System.out.println("Nombre del ejercicio: " + excersise.getNameExample());
        System.out.println("Descripcion de Ejercicio: " + excersise.getDescription() + "\n");
        excersise.example();
        System.out.println();
    }
}