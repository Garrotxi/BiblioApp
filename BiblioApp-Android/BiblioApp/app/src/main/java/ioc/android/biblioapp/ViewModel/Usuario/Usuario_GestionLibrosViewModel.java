/**
 * @Autor Saúl López Díez
 * Clase Usuario_GestionLibrosViewModel con el ViewModel de la clase Usuario_GestionLibros
 */

package ioc.android.biblioapp.ViewModel.Usuario;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import java.util.Collection;
import java.util.List;

import ioc.android.biblioapp.Model.Clases.Llibre;
import ioc.android.biblioapp.Model.Repositorio.BiblioAppRepo;

public class Usuario_GestionLibrosViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<Collection<Llibre>> mensaje;
    private BiblioAppRepo biblioAppRepo;
    private MutableLiveData<Collection<Llibre>> mutableLiveData;
    private List<Llibre> llibres;

    public Usuario_GestionLibrosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Gestión de libros");
        biblioAppRepo = new BiblioAppRepo();
    }

    public LiveData<String> getText() {
        return mText;
    }

    /**
     *
     * @param usuario_gestionLibrosViewModel viewmodel
     * @param context contexto del fragmento
     * @param token token de seguridad
     * @return devuelve lista de libros
     */
    public LiveData<Collection<Llibre>> getListaLibros(Usuario_GestionLibrosViewModel usuario_gestionLibrosViewModel, Context context, String token) {

        mensaje = new MutableLiveData<>();

        usuario_gestionLibrosViewModel.ConseguirLibrosRepositorio(token).observe((LifecycleOwner) context, new Observer<Collection<Llibre>>() {//Generamos un observer a la espera de la consulta con el repositorio
            @Override
            public void onChanged(Collection llibre) {//con la respuesta
                if (llibre != null) {//si no es null
                    mensaje.setValue(llibre);
                }else{
                    mensaje.setValue(null);
                }
            }
        });
        return mensaje;
    }


    /**
     * @param
     * @return datos de la consulta al repositorio, una instancia de Usuari
     */
    public LiveData<Collection<Llibre>> ConseguirLibrosRepositorio(String token) {

        if (mutableLiveData == null) {
            mutableLiveData = biblioAppRepo.pideLibros(token);
        }
        return mutableLiveData;
    }
}