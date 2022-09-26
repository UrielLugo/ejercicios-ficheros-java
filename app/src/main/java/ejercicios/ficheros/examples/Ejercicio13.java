package ejercicios.ficheros.examples;

import ejercicios.ficheros.utils.Constants;
import ejercicios.ficheros.utils.EjerciciosInterface;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Ejercicio13 implements EjerciciosInterface {
    @Override
    public String getNameExample() {
        return "13. Archivo binario con Serialización";
    }

    @Override
    public String getDescription() {
        return "Gestion de clientes desde un fichero binario usando Serialización\n" +
                "Los datos del cliente son: DNI, Nombre y Teléfono\n" +
                "Opciones en el menu:\n" +
                "\t-Crear fichero: Crea el fichero sino existe, si existe indicarlo\n" +
                "\t-Añadir cliente: Pide todos los datos del cliente, crea el objeto y lo mete en el fichero\n" +
                "\t-Listar clientes: Muestra todos los clientes\n" +
                "\t-Borrar fichero: Borra el fichero, sino existe lo indica\n" +
                "\t-Termino: salir del programa";
    }

    @Override
    public void example() {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        boolean salir = false;
        int opcion;

        File fichero = new File(Constants.RESOURCES_PATH + "clientes.dat");

        while (!salir) {
            System.out.println("1. Crear fichero");
            System.out.println("2. Añadir cliente");
            System.out.println("3. Listar clientes");
            System.out.println("4. Borrar fichero");
            System.out.println("5. Salir del programa");

            try {
                System.out.println("Escribe una opción");
                if (!scanner.hasNext()) {
                    throw new InputMismatchException("Se ingreso un caracter no permitido");
                } else {
                    opcion = scanner.nextInt();
                }
                switch (opcion) {
                    case 1:
                        createFile(fichero);
                        break;
                    case 2:
                        addClient(fichero, scanner);
                        break;
                    case 3:
                        List<Cliente> clientes = listClients(fichero);
                        clientes.forEach(System.out::println);
                        break;
                    case 4:
                        deleteFile(fichero);
                        break;
                    case 5:
                        salir = true;
                        break;
                    default:
                        break;
                }
            } catch(Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    void createFile(File file) throws IOException {
        if(file.exists()) {
            System.out.println("El fichero ya esta creado");
        } else {
            boolean newFile = file.createNewFile();
            if (newFile) {
                System.out.println("Se ha creado el fichero!");
            }else {
                throw new IOException("No se ha podido crear el fichero");
            }
        }
    }

    void addClient(File file, Scanner scanner) throws IOException {
        if(!file.exists()) {
            throw new IOException("El archivo no existe, crear archivo primero");
        }
        System.out.println("Introduce un DNI: ");
        String dni = scanner.next();
        System.out.println("Introduce el nombre");
        String nombre = scanner.next();
        System.out.println("Introduce el numero de telefono");
        String telefono = scanner.next();

        Cliente cliente = new Cliente(dni, nombre, telefono);
        writeObjects(file, cliente);
    }

    List<Cliente> listClients(File file) throws IOException {
        if(file == null) {
            throw new NullPointerException("File is null");
        }
        if (!file.exists()) {
            throw new IOException("El archivo no existe, crear archivo primero");
        }

        List<Cliente> clientes = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while(true) {
                Cliente cliente = (Cliente) ois.readObject();
                clientes.add(cliente);
            }
        } catch (ClassNotFoundException e) {
            System.err.println("No se encontro la clase especificada");
            e.printStackTrace();
        } catch (EOFException e) {
            return clientes;
        }
        return clientes;
    }

    /**
     * Write objects in the file specified
     * @param file
     * @param object
     */
    void writeObjects(File file, Object object) throws IOException {
        if (file == null || object == null) {
            throw new NullPointerException("The params must be not null");
        }
        try(ObjectOutputStream oos = (file.length() == 0)
                ? new ObjectOutputStream(new FileOutputStream(file))
                : new MiObjectInputStream(new FileOutputStream(file, true))) {
            oos.writeObject(object);
        }
    }

    void deleteFile(File file) throws IOException {
        if(!file.exists()) {
            throw new IOException("El archivo no existe");
        }

        if(Files.deleteIfExists(file.toPath())) {
            System.out.println("El fichero se ha borrado correctamente");
        }else {
            throw new IOException("El fichero no se ha podido borrar");
        }
    }

    static class Cliente implements Serializable {
        private static final long serialVersionUID = 92439570218045468L;

        private String dni;
        private String nombre;
        private String telefono;

        public Cliente(String dni, String nombre, String telefono) {
            this.dni = dni;
            this.nombre = nombre;
            this.telefono = telefono;
        }

        public String getDni() {
            return dni;
        }

        public void setDni(String dni) {
            this.dni = dni;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        @Override
        public String toString() {
            return "Cliente{" +
                    "dni='" + dni + '\'' +
                    ", nombre='" + nombre + '\'' +
                    ", telefono='" + telefono + '\'' +
                    '}';
        }
    }

    static class MiObjectInputStream extends ObjectOutputStream {

        public MiObjectInputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() {
            // Must do nothing
        }
    }
}