/**
 * @Autor Saúl López Díez
 * Clase Utilidades con varios metodos utiles
 */
package ioc.android.biblioapp.ViewModel.Utilidades;

import static android.content.Context.*;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class Utilidades {


    /**
     * @author Saúl López Díez
     * @param context Contexto de la actividad
     * @param view Vista de la actividad
     * Metodo que esconde el teclado y presenta un mensaje en pantalla que indica que se esta conectando
     */
    public static void esconderTeclado(Context context, View view){
        Toast.makeText(context, "Conectando....", Toast.LENGTH_SHORT).show();
        InputMethodManager inputManager = (InputMethodManager)
               context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

    }

    /**
     * @author Saúl López Díez
     * @param context Contexto de la actividad
     * @return Boolean conectividad, true si hay conectividad y conexión
     */
    public static Boolean conectividad (Context context){
        Boolean conectividad=false;

        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                conectividad=true;
            }
        }
        return conectividad;
    }
}
