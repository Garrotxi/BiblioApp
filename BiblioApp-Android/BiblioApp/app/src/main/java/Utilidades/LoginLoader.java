package Utilidades;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

/**
 * Clase Loader para gestionar en segundo plano fuera del thread principal
 * la conexion con la bases de datos para realizar el Login
 */
public class LoginLoader extends AsyncTaskLoader<String> {
    private String mLoginString, mContraseñaString, mQuery;

    /**
     * @param context
     * @param loginString      Nombre de ususario
     * @param contraseñaString Contraseña del usuario
     */

    public LoginLoader(Context context, String loginString, String contraseñaString, String query) {
        super(context);
        mLoginString = loginString;
        mContraseñaString = contraseñaString;
        mQuery = query;
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return Conexion.obtenerLogin(mLoginString, mContraseñaString,mQuery);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
