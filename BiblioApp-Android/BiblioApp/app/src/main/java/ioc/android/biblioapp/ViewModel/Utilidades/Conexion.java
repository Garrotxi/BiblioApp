package ioc.android.biblioapp.ViewModel.Utilidades;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Clase que gestiona la conexion con la BBDD y genera un JSON con la respuesta
 */
public class Conexion {
    // Nombre de la clase para los Logs
    private static final String LOG_TAG = Conexion.class.getSimpleName();

    // URL y parametros para la conexion
    private static final String URL_BASE = "https://webhook.site/bec2e0aa-3d6f-41ac-ae1a-65c9a1c597bf";
    private static final String USUARIO = "admin";
    private static final String CONTRASEÑA = "admin";
    private static final String QUERY_PARAM = "login";


    /**
     *
     * @param usuario nombre de usuario
     * @param contraseña contraseña de usuario
     * @param query tipo de query
     * @return JSON con los resultados de la consulta
     */
    static String obtenerLogin(String usuario, String contraseña, String query) {
        //Delclaramos e inicailizamos variables
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String loginJSONString = null;


        try {
            //Construimos el URI
            Uri builtURI = Uri.parse(URL_BASE).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, "login")
                    //.appendQueryParameter(USUARIO, usuario) // incluimos los datos de conexion
                    //.appendQueryParameter(CONTRASEÑA, contraseña)
                    .build();

            //Construimos el URL
            URL requestURL = new URL(builtURI.toString());

            //abrimos la conexion URL
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Creamos el InputStream.
            InputStream inputStream = urlConnection.getInputStream();

            // Creamos un buffered reader del inputStream.
            reader = new BufferedReader(new InputStreamReader(inputStream));

            // Usamos un StringBuilder para guardar la respuesta
            StringBuilder builder = new StringBuilder();

            //se lee la respuesta linea a linea
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                // Se añade una linea para facilitar hacer debugging
                builder.append("\n");
            }

            if (builder.length() == 0) {
                // Si el stream esta vacio, no tiene sentido continuar
                return null;
            }

            //Converitmos el stringbuilder en un string
            loginJSONString = builder.toString();

            //escribimos en el log el resultado
            Log.d(LOG_TAG, loginJSONString);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            //cerramos al conexion y el bufferedreader
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //devolvemos el JSON
        return loginJSONString;

    }

}


