/**
 * @Autor Saúl López Díez
 * Clase Administrador_NuevaCategoriaViewModel con el ViewModel de getion de nueva categoria
 */
package ioc.android.biblioapp.ViewModel.Administrador.Categorias;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import ioc.android.biblioapp.Model.Clases.Categoria;
import ioc.android.biblioapp.Model.Clases.Registro;
import ioc.android.biblioapp.Model.Repositorio.BiblioAppRepo;

public class Administrador_NuevaCategoriaViewModel {

    private BiblioAppRepo biblioAppRepo;
    private MutableLiveData<Registro> mutableLiveData;

    /**
     * Constructor de RegistroViewModel
     */

    public Administrador_NuevaCategoriaViewModel(){
        biblioAppRepo = new BiblioAppRepo();
    }

    /**
     *
     * @param categoria damos categoria para registro
     * @param context contexto
     * @param administrador_nuevaCategoriaViewModel instancia registerViewModel
     * @param token
     * @return
     */
    public MutableLiveData<String> nuevaCategoria(Categoria categoria, Context context, Administrador_NuevaCategoriaViewModel administrador_nuevaCategoriaViewModel, String token){
        MutableLiveData <String>  mensaje = new MutableLiveData<>();
        administrador_nuevaCategoriaViewModel.nuevaCathegory(categoria,token, context).observe((LifecycleOwner) context, new Observer<Registro>() {//Generamos un observer a la espera de la consulta con el repositorio
            @Override
            public void onChanged(Registro registro) {
                if (registro != null) {
                    mensaje.setValue(registro.getMissatge());

                } else {
                    Log.e("Registro_Activity", "Error en registro categoria");
                    mensaje.setValue(null);
                }
            }
        });
        return mensaje;
    }

    /**
     *
     * @param categoria datos categoria
     * @return datos de la consulta al repositorio, una instancia de Registro
     */
    public LiveData<Registro> nuevaCathegory(Categoria categoria, String token, Context context) {
        if (mutableLiveData==null) {
            mutableLiveData = biblioAppRepo.añadeCategoria(categoria, token,context);
        }
        return mutableLiveData;
    }

}
