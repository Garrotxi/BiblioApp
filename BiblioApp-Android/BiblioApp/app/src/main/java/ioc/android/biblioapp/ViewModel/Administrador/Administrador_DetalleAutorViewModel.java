/**
 * @Autor Saúl López Díez
 * Clase Administrador_GestionDetalleLibroViewModel con el viewModel del detalle de un libro y metodos para modificar y eliminar
 */

package ioc.android.biblioapp.ViewModel.Administrador;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import ioc.android.biblioapp.Model.Clases.Autor;
import ioc.android.biblioapp.Model.Repositorio.BiblioAppRepo;

public class Administrador_DetalleAutorViewModel extends ViewModel {

    private MutableLiveData<String> mText,mText2;
    private MutableLiveData<Autor> mensaje;
    private BiblioAppRepo biblioAppRepo;
    private MutableLiveData<Autor> mutableLiveData;
    private Autor autor;


    public Administrador_DetalleAutorViewModel() {

        biblioAppRepo = new BiblioAppRepo();
    }

    /**
     *
     * @param administrador_detalleAutorViewModel
     * @param context
     * @param token
     * @return consigue el autor seleccionado de la lista guardada en el viewmodel de Administrador_AutoresViewModel
     */
    public LiveData<Autor> conseguirAutor(Administrador_DetalleAutorViewModel administrador_detalleAutorViewModel, Context context, String token) {
        mensaje = new MutableLiveData<>();
        autor=Administrador_AutoresViewModel.conseguirAutorPorNombre();
        mensaje.setValue(autor);
        return mensaje;
    }

    /**
     *
     * @param administradorDetalleAutorViewModel viewmodel
     * @param context contexto del fragmento
     * @param token token de seguridad
     * @param autor autor con datos a modificados
     * @return libro modificado
     */
    public LiveData<Autor> modificarAutor(Administrador_DetalleAutorViewModel administradorDetalleAutorViewModel, Context context, String token, Autor autor) {
        mensaje = new MutableLiveData<>();
        administradorDetalleAutorViewModel.modificarAutores(autor, token).observe((LifecycleOwner) context, new Observer<Autor>() {
            @Override
            public void onChanged(Autor s) {
                mensaje.setValue(s);
            }
        });
        return mensaje;
    }

    /**
     *
     * @param autor autor con datos a modificados
     * @param token token de seguridad
     * @return llamada a repositorio que devuelve resultado
     */

    public LiveData<Autor> modificarAutores(Autor autor, String token) {
        if (mutableLiveData==null) {
            mutableLiveData = biblioAppRepo.modificarAutor(autor, token);
        }
        return mutableLiveData;
    }

    /**
     *
     * @param administrador_detalleAutorViewModel viewmodel
     * @param context contexto del fragmento
     * @param token token de seguridad
     * @param autor Autor a borrar
     * @return mensaje de API
     */
    public LiveData<String> borrarAutor(Administrador_DetalleAutorViewModel administrador_detalleAutorViewModel, Context context, String token, Autor autor) {
        mText = new MutableLiveData<>();
        administrador_detalleAutorViewModel.borraAuthor(autor, token).observe((LifecycleOwner) context, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mText.setValue(s);
            }
        });
        return mText;
    }

    /**
     *
     * @param autor Autor a borrar
     * @param token token de seguridad
     * @return llamada a repositorio que devuelve resultado
     */
    public LiveData<String> borraAuthor(Autor autor, String token) {
        if (mText2==null) {
            mText2 = biblioAppRepo.borraAutor(autor, token);
        }
        return mText2;
    }
}
