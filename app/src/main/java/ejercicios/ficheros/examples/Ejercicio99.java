package ejercicios.ficheros.examples;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import ejercicios.ficheros.utils.Constants;
import ejercicios.ficheros.utils.EjerciciosInterface;

public class Ejercicio99 implements EjerciciosInterface {

    @Override
    public String getNameExample() {
        return "Guardar una imagen de la web";
    }

    @Override
    public String getDescription() {
        return "Descargar una imagen de internet y guardarla en un archivo con su formato jpg";
    }

    @Override
    public void example() {
        final String IMAGE_URL = "https://images.unsplash.com/photo-1417325384643-aac51acc9e5d?q=75&fm=jpg&w=1080&fit=max";
        final String FILENAME = "randomImage.jpg";
        final String PATH = Constants.RESOURCES_TEMP_PATH;

        download("https://picsum.photos/1280/720", FILENAME, PATH);
    }

    public static void download(String urlString, String filename, String savePath) {
        try {
            // Construir URL
            URL url = new URL(urlString);

            System.out.println("Generando conexion con URL: " + urlString);
            
            // Conexion abierta
            URLConnection con = url.openConnection();
            // Establece el tiempo de espera de la solicitud en 5 s
            con.setConnectTimeout(5 * 1000);
            
            // flujo de entrada
            InputStream is = con.getInputStream();

            // Búfer de datos de 1K
            byte[] bs = new byte[1024];
            // La longitud de los datos leídos
            int len;
            
            // flujo de archivo de salida
            File sf = new File(savePath);
            if (!sf.exists()) {
                sf.mkdirs();
            }

            System.out.println("Guardando archivo " + filename + "...");
            String filenamePath = sf.getPath() + File.separator + filename;
            OutputStream os = new FileOutputStream(filenamePath);
            // empieza a leer
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            // Finalizar, cerrar todos los enlaces
            os.close();
            is.close();

            System.out.println("Archivo guardado: " + filenamePath);

        } catch (MalformedURLException e) {
            System.err.println("Ingrese una URL correcta: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error IO: " + e.getMessage());
        }
    }

}
