/**
 * @Autor Saúl López Díez
 * Clase Administrador_NuevoLibroViewModel con el ViewModel de getion de nuevo libro
 */
package ioc.android.biblioapp.ViewModel.Administrador.Autores;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import ioc.android.biblioapp.Model.Clases.Autor;
import ioc.android.biblioapp.Model.Clases.Registro;
import ioc.android.biblioapp.Model.Repositorio.BiblioAppRepo;

public class Administrador_NuevoAutorViewModel {

    private BiblioAppRepo biblioAppRepo;
    private MutableLiveData<Registro> mutableLiveData;

    /**
     * Constructor de RegistroViewModel
     */

    public Administrador_NuevoAutorViewModel(){
        biblioAppRepo = new BiblioAppRepo();
    }

    /**
     *
     * @param autor damos de autor para registro
     * @param context contexto
     * @param administrador_nuevoAutorViewModel instancia registerViewModel
     * @param token
     * @return
     */
    public MutableLiveData<String> nuevoAutor(Autor autor, Context context, Administrador_NuevoAutorViewModel administrador_nuevoAutorViewModel, String token){
        MutableLiveData <String>  mensaje = new MutableLiveData<>();
        administrador_nuevoAutorViewModel.nuevoAuthor(autor,token, context).observe((LifecycleOwner) context, new Observer<Registro>() {//Generamos un observer a la espera de la consulta con el repositorio
            @Override
            public void onChanged(Registro registro) {
                if (registro != null) {
                    //Log.e("Registro_Activity", registro.getMessage());
                    mensaje.setValue(registro.getMissatge());
                    //Toast.makeText(getApplicationContext(), mensaje.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("Registro_Activity", "Error en registro autor");
                    mensaje.setValue(null);
                }
            }
        });
        return mensaje;
    }

    /**
     *
     * @param autor datos autor
     * @return datos de la consulta al repositorio, una instancia de Registro
     */
    public LiveData<Registro> nuevoAuthor(Autor autor, String token, Context context) {
        if (mutableLiveData==null) {
            mutableLiveData = biblioAppRepo.añadeAutor(autor, token, context);
        }
        return mutableLiveData;
    }

}
