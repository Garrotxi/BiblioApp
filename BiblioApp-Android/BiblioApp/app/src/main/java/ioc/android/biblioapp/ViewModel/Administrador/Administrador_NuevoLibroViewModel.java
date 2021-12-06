package ioc.android.biblioapp.ViewModel.Administrador;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import ioc.android.biblioapp.Model.Clases.Llibre;
import ioc.android.biblioapp.Model.Clases.Registro;
import ioc.android.biblioapp.Model.Repositorio.BiblioAppRepo;

public class Administrador_NuevoLibroViewModel {

    private BiblioAppRepo biblioAppRepo;
    private MutableLiveData<Registro> mutableLiveData;

    /**
     * Constructor de RegistroViewModel
     */

    public Administrador_NuevoLibroViewModel(){
        biblioAppRepo = new BiblioAppRepo();
    }

    /**
     *
     * @param libro daos de usuario para registro
     * @param context contexto
     * @param administrador_nuevoLibroViewModel instancia registerViewModel
     * @param token
     * @return
     */
    public MutableLiveData<String> nuevoLibro (Llibre libro, Context context, Administrador_NuevoLibroViewModel administrador_nuevoLibroViewModel, String token){
        MutableLiveData <String>  mensaje = new MutableLiveData<>();
        administrador_nuevoLibroViewModel.conseguirRegistroRepositorio(libro,token).observe((LifecycleOwner) context, new Observer<Registro>() {//Generamos un observer a la espera de la consulta con el repositorio
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
     * @param libro daotso usario
     * @return datos de la consulta al repositorio, una instancia de Registro
     */
    public LiveData<Registro> conseguirRegistroRepositorio(Llibre libro, String token) {
        if (mutableLiveData==null) {
            mutableLiveData = biblioAppRepo.añadeLibro(libro, token);
        }
        return mutableLiveData;
    }

}
