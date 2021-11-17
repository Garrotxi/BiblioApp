/**
 * @Autor Saúl López Díez
 * Clase BiblioAppRepo con el Repositorio de metodos para conseguir la información y enviarsela a la capa ViewModel
 */
package ioc.android.biblioapp.Model.Repositorio;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;

import java.io.IOException;

import ioc.android.biblioapp.Model.Clases.Login;
import ioc.android.biblioapp.Model.Clases.Registro;
import ioc.android.biblioapp.Model.Clases.Usuari;
import ioc.android.biblioapp.Model.Servicio.BiblioAppCliente;
import ioc.android.biblioapp.Model.Servicio.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Clase BiblioAppRepo
 */
public class BiblioAppRepo {
    private final String TAG = getClass().getSimpleName();


    /**
     * Constructor
     */
    public BiblioAppRepo() {
    }

    /**
     *
     * @param login  datos para realizar el login
     * @return mutableLiveData con la información devuelta por el API
     * Si la conexión resulta correcta, conseguiremos un objeto de la clase Usuari, en caso
     * contrario, nos devolvera null
     */
    public MutableLiveData<Usuari> requestLogin(Login login) {
        final MutableLiveData<Usuari> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable


        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, "login");// Generamos el servicio

        biblioAppCliente.Login(login).enqueue(new Callback<Usuari>() {//procesamos en segundo plano el metodo login del servicio
            @Override
            public void onResponse(Call<Usuari> call, Response<Usuari> response) {//si el correcto conseguimos un usuario con la auth key
                if (response.isSuccessful() && response.body() != null) {
                    Usuari usuari;
                    usuari = response.body();
                    mutableLiveData.setValue(usuari);

                } else {
                    Log.e(TAG, "Login incorrecto");
                    mutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<Usuari> call, Throwable t) {//si falla la conexión
                Log.e(TAG, "Error de conexion");
            }
        });

        return mutableLiveData;
    }

    /**
     *
     * @param usuari datos para realizar el registro
     * @return registro objeto con el mensaje de la API
     * Si el registro es correcto nos devuelve un mensaje diciendo que se ha registrado,
     * en caso contrario nos envia un mensaje de error
     */
    public MutableLiveData<Registro> registro(Usuari usuari) {
        final MutableLiveData<Registro> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable


        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, "registro");// Generamos el servicio

        biblioAppCliente.Registro(usuari).enqueue(new Callback<Registro>() {//procesamos en segundo plano el metodo Registro del servicio
            Registro registro = new Registro();

            @Override
            public void onResponse(Call<Registro> call, Response<Registro> response) { //si es correcto conseguimos una respuesta satisactoria, con informacion en body
                if (response.isSuccessful() && response.body() != null) {
                    //Log.d(TAG, response.body().getMessage());
                    registro = response.body();
                    mutableLiveData.setValue(registro);
                } else {//si la respuesta no es satisfactoria, conseguimos el error del errorBody y generamos un objeto Registro
                    try {
                        String json = response.errorBody().string();
                        Gson gson = new Gson();
                        registro = gson.fromJson(json, Registro.class);
                        mutableLiveData.setValue(registro);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "Error al crear usuario");
                }
            }

            @Override
            public void onFailure(Call<Registro> call, Throwable t) {
                Log.e(TAG, "Registro incorrecto");
            }
        });
        return mutableLiveData;
    }

}

