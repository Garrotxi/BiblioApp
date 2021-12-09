/**
 * @Autor Saúl López Díez
 * Clase Administrador_GestionBuscarLibroViewModel con el viewModel de la busqueda de libros
 */

package ioc.android.biblioapp.ViewModel.Administrador;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import ioc.android.biblioapp.Model.Clases.Llibre;
import ioc.android.biblioapp.Model.Repositorio.BiblioAppRepo;

public class Administrador_GestionBuscarLibroViewModel extends ViewModel {


    private MutableLiveData<Llibre> mensaje, mensaje2;
    private BiblioAppRepo biblioAppRepo;

    private Llibre llibre;

    public void setLlibre(Llibre llibre) {
        this.llibre = llibre;
    }

    public Administrador_GestionBuscarLibroViewModel() {

        biblioAppRepo = new BiblioAppRepo();
    }

    /**
     *
     * @param administrador_gestionBuscarLibroViewModel viewModel
     * @param context Contexto del fragmento
     * @param token token de seguridad
     * @param llibre libro a buscar, con ID
     * @return
     */
    public LiveData<Llibre> buscarLibroId(Administrador_GestionBuscarLibroViewModel administrador_gestionBuscarLibroViewModel, Context context, String token, Llibre llibre) {
        mensaje = new MutableLiveData<>();
        administrador_gestionBuscarLibroViewModel.buscaIdBook(llibre, token).observe((LifecycleOwner) context, new Observer<Llibre>() {
            @Override
            public void onChanged(Llibre llibre1) {
                mensaje.setValue(llibre1);
                setLlibre(llibre1);
            }
        });
        return mensaje;
    }

    /**
     *
     * @param llibre libro a buscar, con ID
     * @param token token de seguridad
     * @return
     */
    public LiveData<Llibre> buscaIdBook(Llibre llibre, String token) {
        if (mensaje2==null) {
            mensaje2 = biblioAppRepo.buscaLibroId(llibre, token);
        }
        return mensaje2;
    }

    /**
     *
     * @param administrador_gestionBuscarLibroViewModel viewModel
     * @param context contexto del fragmento
     * @param token token de seguridad
     * @param llibre libro con titulo a buscar
     * @return llamada a repositorio que devuelve resultado
     */
    public LiveData<Llibre> buscarLibroTitulo(Administrador_GestionBuscarLibroViewModel administrador_gestionBuscarLibroViewModel, Context context, String token, Llibre llibre) {
        mensaje = new MutableLiveData<>();
        administrador_gestionBuscarLibroViewModel.buscaTituloBook(llibre, token).observe((LifecycleOwner) context, new Observer<Llibre>() {
            @Override
            public void onChanged(Llibre s) {
                mensaje.setValue(s);
                setLlibre(s);
            }
        });
        return mensaje;
    }

    /**
     *
     * @param llibre libro con titulo a buscar
     * @param token token de seguridad
     * @return llamada a repositorio que devuelve resultado
     */
    public LiveData<Llibre> buscaTituloBook(Llibre llibre, String token) {
        if (mensaje2==null) {
            mensaje2 = biblioAppRepo.buscaLibroTitulo(llibre, token);
        }
        return mensaje2;
    }
}
