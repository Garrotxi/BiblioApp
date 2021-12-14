/**
 * @Autor Saúl López Díez
 * Clase Administrador_NuevoPrestecViewModel con el ViewModel de getion de nuevo prestamo
 */
package ioc.android.biblioapp.ViewModel.Administrador.Prestamos;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import ioc.android.biblioapp.Model.Clases.Prestec;
import ioc.android.biblioapp.Model.Clases.Registro;
import ioc.android.biblioapp.Model.Repositorio.BiblioAppRepo;

public class Administrador_NuevoPrestecViewModel {

    private BiblioAppRepo biblioAppRepo;
    private MutableLiveData<Registro> mutableLiveData;

    /**
     * Constructor de RegistroViewModel
     */

    public Administrador_NuevoPrestecViewModel(){
        biblioAppRepo = new BiblioAppRepo();
    }

    /**
     *
     * @param prestec daos de prestamo para registro
     * @param context contexto
     * @param administrador_nuevoPrestecViewModel instancia ViewModel
     * @param token
     * @return
     */
    public MutableLiveData<String> nuevoPrestec (Prestec prestec, Context context, Administrador_NuevoPrestecViewModel administrador_nuevoPrestecViewModel, String token){
        MutableLiveData <String>  mensaje = new MutableLiveData<>();
        administrador_nuevoPrestecViewModel.conseguirRegistroRepositorio(prestec,token, context).observe((LifecycleOwner) context, new Observer<Registro>() {//Generamos un observer a la espera de la consulta con el repositorio
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
     * @param prestec datos prestec
     * @return datos de la consulta al repositorio, una instancia de Registro
     */
    public LiveData<Registro> conseguirRegistroRepositorio(Prestec prestec, String token, Context context) {
        if (mutableLiveData==null) {
            mutableLiveData = biblioAppRepo.añadePrestec(prestec, token, context);
        }
        return mutableLiveData;
    }

}
