/**
 * @Autor Saúl López Díez
 * Clase Administrador_RegistroUsuarioViewModel con el ViewModel de getion de nuevo usuario
 */
package ioc.android.biblioapp.ViewModel.Administrador.Usuarios;

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

public class Administrador_RegistroUsuarioViewModel extends ViewModel {
    private BiblioAppRepo biblioAppRepo;
    private MutableLiveData<Registro> mutableLiveData;

    /**
     * Constructor de RegistroViewModel
     */

    public Administrador_RegistroUsuarioViewModel(){
        biblioAppRepo = new BiblioAppRepo();
    }


    /**
     *
     * @param usuari daos de usuario para registro
     * @param context contexto
     * @param administrador_registroUsuarioViewModel instancia registerViewModel
     * @return
     */
    public MutableLiveData<String> registroViewModel (Usuari usuari, Context context, Administrador_RegistroUsuarioViewModel administrador_registroUsuarioViewModel){
        MutableLiveData <String>  mensaje = new MutableLiveData<>();
        administrador_registroUsuarioViewModel.conseguirRegistroRepositorio(usuari, context).observe((LifecycleOwner) context, new Observer<Registro>() {//Generamos un observer a la espera de la consulta con el repositorio
            @Override
            public void onChanged(Registro registro) {
                if (registro != null) {
                    mensaje.setValue(registro.getMissatge());
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
    public LiveData<Registro> conseguirRegistroRepositorio(Usuari usuari, Context context) {
        if (mutableLiveData==null) {
            mutableLiveData = biblioAppRepo.pideRegistro(usuari, context);
        }
        return mutableLiveData;
    }



}
