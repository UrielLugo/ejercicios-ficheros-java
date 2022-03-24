package ejercicios.ficheros.examples;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ejercicios.ficheros.utils.Constants;
import ejercicios.ficheros.utils.EjerciciosInterface;

public class Ejercicio11 implements EjerciciosInterface {

    @Override
    public String getNameExample() {
        return "11. Lectura con DataInputStream";
    }

    @Override
    public String getDescription() {
        return "Lee el anterior fichero binario con DataInputStream. Despues pide un valor (expectativa salarial) y muestra aquellos registros que sean menores que ese dato";
    }

    @Override
    public void example() {
        
        List<Double> expectList = new ArrayList<>();
        
        try(DataInputStream dataInputStream = new DataInputStream(new FileInputStream(Constants.RESOURCES_PATH + Constants.CANDIDATES_FILENAME))) {
            
            System.out.println("CURP\t\t\tNOMBRE\t\t\tEDAD\t\t\tEXPECTATIVA");

            String curp;
            String nombre;
            String edad;
            String expectativaSalarial;


            while(true){
                curp = dataInputStream.readUTF();
                System.out.print(curp + "\t\t\t");
                nombre = dataInputStream.readUTF();
                System.out.print(nombre + "\t\t\t");
                edad = dataInputStream.readUTF();
                System.out.print(edad + "\t\t\t");
                expectativaSalarial = dataInputStream.readUTF();
                expectList.add(Double.valueOf(expectativaSalarial));
                System.out.print(expectativaSalarial + "\t\t\t");
                System.out.println();
            }
        }catch(EOFException e) {
            System.out.println("Fin de archivo");
            expectList.sort((a,b) -> {
                if(a - b > 0) { // List ascending order
                    return 1; // a is greater than b
                }else if(a - b == 0) {
                    return 0; // Is equal a and b
                } else {
                    return -1; // a is less than b
                }
            });
            System.out.println("Lista de salarios ordenados: " + expectList);
            System.out.println("Expectativa salarial mas alta: " + expectList.get(expectList.size() - 1));
        }catch(FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + e.getMessage());
        }catch(IOException e) { 
            System.err.println("Error IO: " + e.getMessage());
        }
    }
    
}
