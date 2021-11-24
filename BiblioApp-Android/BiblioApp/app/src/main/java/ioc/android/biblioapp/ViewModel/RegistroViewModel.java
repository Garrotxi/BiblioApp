/**
 * @Autor Saúl López Díez
 * Clase RegistroViewModel que gestiona las llamadas de la vista y trata con la parte del Modelo para conseguir la información
 * para registrar un nuevo usuario
 */
package ioc.android.biblioapp.ViewModel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import ioc.android.biblioapp.Model.Clases.Registro;
import ioc.android.biblioapp.Model.Clases.Usuari;
import ioc.android.biblioapp.Model.Repositorio.BiblioAppRepo;

public class RegistroViewModel  extends ViewModel {

    private BiblioAppRepo biblioAppRepo;
    private MutableLiveData<Registro> mutableLiveData;

    /**
     * Constructor de RegistroViewModel
     */

    public RegistroViewModel(){
        biblioAppRepo = new BiblioAppRepo();
    }


    /**
     *
     * @param usuari daos de usuario para registro
     * @param context contexto
     * @param registroViewModel instancia registerViewModel
     * @return
     */
    public MutableLiveData<String> registroViewModel (Usuari usuari, Context context, RegistroViewModel registroViewModel){
        MutableLiveData <String>  mensaje = new MutableLiveData<>();
        registroViewModel.conseguirRegistroRepositorio(usuari).observe((LifecycleOwner) context, new Observer<Registro>() {//Generamos un observer a la espera de la consulta con el repositorio
            @Override
            public void onChanged(Registro registro) {
                if (registro != null) {
                    //Log.e("Registro_Activity", registro.getMessage());
                    mensaje.setValue(registro.getMissatge());
                    //Toast.makeText(getApplicationContext(), mensaje.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("Registro_Activity", "Error en registro");
                    mensaje.setValue(null);
                }
            }
        });
        return mensaje;
    }


    /**
     *
     * @param usuari daotso usario
     * @return datos de la consulta al repositorio, una instancia de Registro
     */
    public LiveData<Registro> conseguirRegistroRepositorio(Usuari usuari) {
        if (mutableLiveData==null) {
            mutableLiveData = biblioAppRepo.pideRegistro(usuari);
        }
        return mutableLiveData;
    }
}
