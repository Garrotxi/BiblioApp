package ioc.android.biblioapp;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Utilidades.LoginLoader;

/**
 * Clase principal de Acceso a la APP
 */
public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private TextView usuario, contraseña, login_incorrecto;
    protected String mUsuario, mContraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //incializamos variables
        usuario = findViewById(R.id.user_edit_text);
        contraseña = findViewById(R.id.password_edit_text);
        login_incorrecto = findViewById(R.id.login_incorrecto);

        //añadimos el loader para gestionar en segundo plano tareas
        if (getSupportLoaderManager().getLoader(0) != null) {
            getSupportLoaderManager().initLoader(0, null, this);
        }
    }

    /**
     * Metodo para gestionar el Login al pulsar el boton correspondiente
     *
     * @param view
     */
    public void Login(View view) {
        //Cogemos los datos de los campos de texto de ususario y contraseña
        mUsuario = usuario.getText().toString();
        mContraseña = contraseña.getText().toString();

        //Para esconder el teclado una vez se ha lanzado la busqueda
        Toast.makeText(getApplicationContext(), "Conectando....", Toast.LENGTH_SHORT).show();
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        //para comprobar si no hay conexion o la query esta vacia
        //se comprueba la conectividad
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        //se comprueba que hay conexion y query
        if (networkInfo != null && networkInfo.isConnected()
                && mUsuario.length() != 0 && mContraseña.length() != 0) {
            //se añaden los datos y se lanza el loader
            Bundle queryBundle = new Bundle();
            queryBundle.putString("usuario", mUsuario);
            queryBundle.putString("contraseña", mContraseña);
            getSupportLoaderManager().restartLoader(0, queryBundle, this);
            //mensaje de error personalizado
        } else {
            if (mUsuario.length() == 0) {
                Toast.makeText(getApplicationContext(), "Introduzca usuario", Toast.LENGTH_SHORT).show();
            }
            if (mContraseña.length() == 0) {
                Toast.makeText(getApplicationContext(), "Introduzca contraseña", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Sin conexion a internet", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * @param id   identificador del loader
     * @param args Bundle en el que estan introducidos ususario y contraseña
     * @return una instancia de LoginLoader
     */
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String loginString = "";
        String contraseñaString = "";
        String querie ="";

        if (args != null) {
            loginString = args.getString("usuario");
            contraseñaString = args.getString("contraseña");
        }

        return new LoginLoader(this, loginString, contraseñaString, querie);
    }

    /**
     * @param loader
     * @param data   datos recogidos en la consulta
     */
    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {

        try {

            //para obtener el JSON array del string de  resultados
            JSONObject jsonObject = new JSONObject(data);
            JSONArray itemsArray = jsonObject.getJSONArray("login");

            //inicalizamos variables para el bucle
            int i = 0;
            String user = null;
            String pass = null;
            String esAdmin = null;

            //iteramos por el itemArray buscando usuario y contraseña hasta encontrarlo
            while (i < itemsArray.length() || (user == null && pass == null)) {
                // Conseguimos la informacion de los items
                JSONObject usuarios = itemsArray.getJSONObject(i);

                // Intenamos conseguir el usuario, contraseña y si es Admin del item,
                // capturamos si estan vacios
                try {
                    user = usuarios.getString("usuario");
                    pass = usuarios.getString("contraseña");
                    esAdmin = usuarios.getString("isAdmin");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //si coincide salimos del bucle
                if (user.equalsIgnoreCase(mUsuario) && pass.equalsIgnoreCase(mContraseña)) {
                    i = itemsArray.length();
                }
                // Seguimos al siguiente item
                i++;
            }

            // si hay resultados
            if (user != null && pass != null && esAdmin != null) {
                // si coincide y es Admin
                if (user.equalsIgnoreCase(mUsuario) && pass.equalsIgnoreCase(mContraseña) && esAdmin.equalsIgnoreCase("True")) {
                    Toast.makeText(getApplicationContext(), "Credenciales encontradas. Bienvenido Administrador " + mUsuario, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, Pantalla_Inicio_Administrador.class);
                    getWindow().setExitTransition(new Explode());
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                // si coincide y no es admin
                }
                if (user.equalsIgnoreCase(mUsuario) && pass.equalsIgnoreCase(mContraseña) && esAdmin.equalsIgnoreCase("False")) {
                    Toast.makeText(getApplicationContext(), "Credenciales encontradas. Bienvenido Usuario" + mUsuario, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, Pantalla_Inicio_Usuario.class);
                    getWindow().setExitTransition(new Explode());
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                    //si no se encuentra resultado
                } else {
                    login_incorrecto.setVisibility(View.VISIBLE);
                    //Toast.makeText(getApplicationContext(), R.string.Toast, Toast.LENGTH_SHORT).show();
                }
                //Si no hay resultados
            } else {
                Toast.makeText(getApplicationContext(), "No se ha podido conectar", Toast.LENGTH_SHORT).show();
            }
            //Capturamos excepciones
        } catch (JSONException e) {
            // Si no se recibe un JSON correctamente formado
            // Avisamos por el interfaz
            Toast.makeText(getApplicationContext(), "Error. El JSON no esta bien formado", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (NullPointerException nullPointerException) {
            // Si no se reciben resultados informamos de ello
            Toast.makeText(getApplicationContext(), "Error. Null Pointer.", Toast.LENGTH_SHORT).show();
            nullPointerException.printStackTrace();
        }


    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    public void Borra(View view) {
        usuario.setText("");
        contraseña.setText("");
    }
}