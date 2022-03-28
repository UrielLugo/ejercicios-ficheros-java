package ejercicios.ficheros.examples;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ejercicios.ficheros.utils.Constants;
import ejercicios.ficheros.utils.EjerciciosInterface;

public class Ejercicio12 implements EjerciciosInterface {

    @Override
    public String getNameExample() {
        return "12. Lectura archivo Excel";
    }

    @Override
    public String getDescription() {
        return "Leer con Apache POI (Java API To Access Microsoft Format Files) el Excel adjuntado, todo el contenido de la primera hoja";
    }

    @Override
    public void example() {
        try(FileInputStream fileInputStream = new FileInputStream(Constants.RESOURCES_PATH + Constants.EXCEL_FILENAME)) {
            XSSFWorkbook libro = new XSSFWorkbook(fileInputStream);
            XSSFSheet hoja = libro.getSheetAt(0);
            
            Iterator<Row> filas = hoja.iterator();
            Row fila;
            Iterator<Cell> celdas;
            while(filas.hasNext()) {
                fila = filas.next();
                celdas = fila.iterator();
                while(celdas.hasNext()) {
                    Cell celda = celdas.next();
                    String cellString = celda.getCellType() == CellType.NUMERIC ? Double.toString(celda.getNumericCellValue()) : celda.getStringCellValue();
                    System.out.print(cellString + " | ");
                }
                System.out.println();
            }
            libro.close();
        } catch(FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + e.getMessage());
        } catch(IOException e) {
            System.err.println("IO Error: " + e.getMessage());
        }
    }
    
}
