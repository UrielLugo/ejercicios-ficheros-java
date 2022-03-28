package ejercicios.ficheros.examples;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import ejercicios.ficheros.utils.Constants;
import ejercicios.ficheros.utils.EjerciciosInterface;

public class Ejercicio10 implements EjerciciosInterface {

    @Override
    public String getNameExample() {
        return "10. Guardado de información con DataOutputStream";
    }

    @Override
    public String getDescription() {
        return "Guardar una serie de datos sobre los candidatos a un puesto de trabajo en un fichero binario. Debemos guardar lo siguiente: DNI, nombre, edad y expectativa salarial en ese orden. Usa DataOutputStream";
    }

    @Override
    public void example() {
        
        String curp = getInput("Ingrese un curp", null);
        String nombre = getInput("Ingrese un nombre", null);
        int edad = getInput("Ingrese una edad", Integer.class);
        double expectativaSalarial = getInput("Ingrese una expectativa salarial", Double.class);

        try(DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(Constants.RESOURCES_PATH + Constants.CANDIDATES_FILENAME, true))) {
            dataOutputStream.writeUTF(curp);
            dataOutputStream.writeUTF(nombre);
            dataOutputStream.writeUTF(Integer.toString(edad));
            dataOutputStream.writeUTF(Double.toString(expectativaSalarial));
        } catch(FileNotFoundException e) { 
            System.err.println("Archivo no encontrado: " + e.getMessage());
        } catch(IOException e) {
            System.err.println("Error IO: " + e.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
    public <T> T getInput(String message, Class<? extends Number> type ) throws ClassCastException{
        String title = "Recolección de información";
        String showInputDialog = JOptionPane.showInputDialog(null, message, title, JOptionPane.QUESTION_MESSAGE);


        if(type == null) {
            return (T) showInputDialog;
        }else if(type == Integer.class) {
            return (T) Integer.valueOf(showInputDialog);
        }else if(type == Double.class) {
            return (T) Double.valueOf(showInputDialog);
        }else if(type == Float.class) {
            return (T) Float.valueOf(showInputDialog);
        }

        return null;
    }    
}
