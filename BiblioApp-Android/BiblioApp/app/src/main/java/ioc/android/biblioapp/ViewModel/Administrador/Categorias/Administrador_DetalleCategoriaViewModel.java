/**
 * @Autor Saúl López Díez
 * Clase Administrador_DetalleCategoriaViewModel con el viewModel del detalle de una cateforia y metodos para modificar y eliminar
 */

package ioc.android.biblioapp.ViewModel.Administrador.Categorias;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import ioc.android.biblioapp.Model.Clases.Categoria;
import ioc.android.biblioapp.Model.Repositorio.BiblioAppRepo;

public class Administrador_DetalleCategoriaViewModel extends ViewModel {

    private MutableLiveData<String> mText,mText2;
    private MutableLiveData<Categoria> mensaje;
    private BiblioAppRepo biblioAppRepo;
    private MutableLiveData<Categoria> mutableLiveData;
    private Categoria categoria;


    public Administrador_DetalleCategoriaViewModel() {

        biblioAppRepo = new BiblioAppRepo();
    }

    /**
     *
     * @param administrador_detalleCategoriaViewModel
     * @param context
     * @param token
     * @return consigue la categoria seleccionado de la lista guardada en el viewmodel de Administrador_AutoresViewModel
     */
    public LiveData<Categoria> conseguirCategoria(Administrador_DetalleCategoriaViewModel administrador_detalleCategoriaViewModel, Context context, String token) {
        mensaje = new MutableLiveData<>();
        categoria=Administrador_CategoriasViewModel.conseguirCategoriaPorNombre();
        mensaje.setValue(categoria);
        return mensaje;
    }

    /**
     *
     * @param administrador_detalleCategoriaViewModel viewmodel
     * @param context contexto del fragmento
     * @param token token de seguridad
     * @param categoria categoria con datos a modificados
     * @return categoria modificada
     */
    public LiveData<Categoria> modificarCategoria(Administrador_DetalleCategoriaViewModel administrador_detalleCategoriaViewModel, Context context, String token, Categoria categoria) {
        mensaje = new MutableLiveData<>();
        administrador_detalleCategoriaViewModel.modificarCategorias(categoria, token,context).observe((LifecycleOwner) context, new Observer<Categoria>() {
            @Override
            public void onChanged(Categoria s) {
                mensaje.setValue(s);
            }
        });
        return mensaje;
    }

    /**
     *
     * @param categoria categoria con datos a modificados
     * @param token token de seguridad
     * @return llamada a repositorio que devuelve resultado
     */

    public LiveData<Categoria> modificarCategorias(Categoria categoria, String token, Context context) {
        if (mutableLiveData==null) {
            mutableLiveData = biblioAppRepo.modificarCategoria(categoria, token, context);
        }
        return mutableLiveData;
    }

    /**
     *
     * @param administrador_detalleCategoriaViewModel viewmodel
     * @param context contexto del fragmento
     * @param token token de seguridad
     * @param categoria Categoria a borrar
     * @return mensaje de API
     */
    public LiveData<String> borrarCategoria(Administrador_DetalleCategoriaViewModel administrador_detalleCategoriaViewModel, Context context, String token, Categoria categoria) {
        mText = new MutableLiveData<>();
        administrador_detalleCategoriaViewModel.borraCathegory(categoria, token, context).observe((LifecycleOwner) context, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mText.setValue(s);
            }
        });
        return mText;
    }

    /**
     *
     * @param categoria Categoria a borrar
     * @param token token de seguridad
     * @return llamada a repositorio que devuelve resultado
     */
    public LiveData<String> borraCathegory(Categoria categoria, String token, Context context) {
        if (mText2==null) {
            mText2 = biblioAppRepo.borraCategoria(categoria, token, context);
        }
        return mText2;
    }
}
