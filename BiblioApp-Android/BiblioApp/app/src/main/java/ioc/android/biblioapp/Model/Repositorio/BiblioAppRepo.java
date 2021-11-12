package ioc.android.biblioapp.Model.Repositorio;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import ioc.android.biblioapp.Model.Clases.Login;
import ioc.android.biblioapp.Model.Clases.Usuari;
import ioc.android.biblioapp.Model.Servicio.BiblioAppCliente;
import ioc.android.biblioapp.Model.Servicio.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BiblioAppRepo {
    private final String TAG = getClass().getSimpleName();


    public BiblioAppRepo() {
    }


    public MutableLiveData<Usuari> requestLogin(Login login) {
        final MutableLiveData<Usuari> mutableLiveData = new MutableLiveData<>();


        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, null);

        biblioAppCliente.Login(login).enqueue(new Callback<Usuari>() {
            @Override
            public void onResponse(Call<Usuari> call, Response<Usuari> response) {
                if (response.isSuccessful()  && response.body()!=null ){

                    Usuari usuari = new Usuari ();
                    usuari.setAccessToken(response.body().getAccessToken());

                    mutableLiveData.setValue(usuari);

                }else{

                    Log.e(TAG,"Login incorrecto");
                    mutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<Usuari> call, Throwable t) {
                Log.e(TAG,"Error de conexion");

            }
        });

        return mutableLiveData;
    }


}

