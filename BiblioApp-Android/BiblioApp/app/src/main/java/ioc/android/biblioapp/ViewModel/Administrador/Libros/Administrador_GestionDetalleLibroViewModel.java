/**
 * @Autor Saúl López Díez
 * Clase Administrador_GestionDetalleLibroViewModel con el viewModel del detalle de un libro y metodos para modificar y eliminar
 */

package ioc.android.biblioapp.ViewModel.Administrador.Libros;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import ioc.android.biblioapp.Model.Clases.Llibre;
import ioc.android.biblioapp.Model.Repositorio.BiblioAppRepo;

public class Administrador_GestionDetalleLibroViewModel extends ViewModel {

    private MutableLiveData<String> mText,mText2;
    private MutableLiveData<Llibre> mensaje;
    private BiblioAppRepo biblioAppRepo;
    private MutableLiveData<Llibre> mutableLiveData;
    private Llibre llibre;


    public Administrador_GestionDetalleLibroViewModel() {

        biblioAppRepo = new BiblioAppRepo();
    }

    /**
     *
     * @param administrador_gestionDetalleLibroViewModel
     * @param context
     * @param token
     * @return consigue el libro seleccionado de la lista guardada en el viewmodel de Administrador_GestionLibrosViewModel
     */
    public LiveData<Llibre> conseguirLibro(Administrador_GestionDetalleLibroViewModel administrador_gestionDetalleLibroViewModel, Context context, String token) {
        mensaje = new MutableLiveData<>();
        llibre=Administrador_GestionLibrosViewModel.conseguirLibroPorTitulo();
        mensaje.setValue(llibre);
        return mensaje;
    }

    /**
     *
     * @param administrador_gestionDetalleLibroViewModel viewmodel
     * @param context contexto del fragmento
     * @param token token de seguridad
     * @param llibre libro con datos a modificados
     * @return libro modificado
     */
    public LiveData<Llibre> modificarLibro(Administrador_GestionDetalleLibroViewModel administrador_gestionDetalleLibroViewModel, Context context, String token, Llibre llibre) {
        mensaje = new MutableLiveData<>();
        administrador_gestionDetalleLibroViewModel.modificarLibros(llibre, token, context).observe((LifecycleOwner) context, new Observer<Llibre>() {
            @Override
            public void onChanged(Llibre s) {
                mensaje.setValue(s);
            }
        });
        return mensaje;
    }

    /**
     *
     * @param llibre libro con datos a modificados
     * @param token token de seguridad
     * @return llamada a repositorio que devuelve resultado
     */

    public LiveData<Llibre> modificarLibros(Llibre llibre, String token, Context context) {
        if (mutableLiveData==null) {
            mutableLiveData = biblioAppRepo.modificarLlibre(llibre, token, context);
        }
        return mutableLiveData;
    }

    /**
     *
     * @param administrador_gestionDetalleLibroViewModel viewmodel
     * @param context contexto del fragmento
     * @param token token de seguridad
     * @param llibre Libro a borrar
     * @return mensaje de API
     */
    public LiveData<String> borrarLibro(Administrador_GestionDetalleLibroViewModel administrador_gestionDetalleLibroViewModel, Context context, String token, Llibre llibre) {
        mText = new MutableLiveData<>();
        administrador_gestionDetalleLibroViewModel.borraBook(llibre, token, context).observe((LifecycleOwner) context, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mText.setValue(s);
            }
        });
        return mText;
    }

    /**
     *
     * @param llibre Libro a borrar
     * @param token token de seguridad
     * @return llamada a repositorio que devuelve resultado
     */
    public LiveData<String> borraBook(Llibre llibre, String token, Context context) {
        if (mText2==null) {
            mText2 = biblioAppRepo.borraLibro(llibre, token, context);
        }
        return mText2;
    }
}
