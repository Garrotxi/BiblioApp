/**
 * @Autor Saúl López Díez
 * Clase Administrador_DetallePrestecViewModel con el viewModel del detalle de un prestamo y metodos para modificar y eliminar
 */

package ioc.android.biblioapp.ViewModel.Administrador;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import ioc.android.biblioapp.Model.Clases.Prestec;
import ioc.android.biblioapp.Model.Repositorio.BiblioAppRepo;

public class Administrador_DetallePrestecViewModel extends ViewModel {

    private MutableLiveData<String> mText,mText2;
    private MutableLiveData<Prestec> mensaje;
    private BiblioAppRepo biblioAppRepo;
    private MutableLiveData<Prestec> mutableLiveData;
    private Prestec prestec;


    public Administrador_DetallePrestecViewModel() {

        biblioAppRepo = new BiblioAppRepo();
    }

    /**
     *
     * @param administradorDetallePrestecViewModel
     * @param context
     * @param token
     * @return consigue el prestamo seleccionado de la lista guardada en el viewmodel de Administrador_PrestecViewModel
     */
    public LiveData<Prestec> conseguirPrestec(Administrador_DetallePrestecViewModel administradorDetallePrestecViewModel, Context context, String token) {
        mensaje = new MutableLiveData<>();
        prestec=Administrador_PrestecViewModel.conseguirPrestecPorId();
        mensaje.setValue(prestec);
        return mensaje;
    }

    /**
     *
     * @param administrador_detallePrestecViewModel viewmodel
     * @param context contexto del fragmento
     * @param token token de seguridad
     * @param prestec prestamo con datos a modificados
     * @return libro modificado
     */
    public LiveData<Prestec> modificarPrestec(Administrador_DetallePrestecViewModel administrador_detallePrestecViewModel, Context context, String token, Prestec prestec) {
        mensaje = new MutableLiveData<>();
        administrador_detallePrestecViewModel.modificarPrestec(prestec, token).observe((LifecycleOwner) context, new Observer<Prestec>() {
            @Override
            public void onChanged(Prestec s) {
                mensaje.setValue(s);
            }
        });
        return mensaje;
    }

    /**
     *
     * @param prestec prestamo con datos a modificados
     * @param token token de seguridad
     * @return llamada a repositorio que devuelve resultado
     */

    public LiveData<Prestec> modificarPrestec(Prestec prestec, String token) {
        if (mutableLiveData==null) {
            mutableLiveData = biblioAppRepo.modificarPrestec(prestec, token);
        }
        return mutableLiveData;
    }

    /**
     *
     * @param administrador_detallePrestecViewModel viewmodel
     * @param context contexto del fragmento
     * @param token token de seguridad
     * @param prestec Prestamo a borrar
     * @return mensaje de API
     */
    public LiveData<String> borrarPrestec(Administrador_DetallePrestecViewModel administrador_detallePrestecViewModel, Context context, String token, Prestec prestec) {
        mText = new MutableLiveData<>();
        administrador_detallePrestecViewModel.borraLoan(prestec, token).observe((LifecycleOwner) context, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mText.setValue(s);
            }
        });
        return mText;
    }

    /**
     *
     * @param prestec Prestamo a borrar
     * @param token token de seguridad
     * @return llamada a repositorio que devuelve resultado
     */
    public LiveData<String> borraLoan(Prestec prestec, String token) {
        if (mText2==null) {
            mText2 = biblioAppRepo.borraPrestec(prestec, token);
        }
        return mText2;
    }
}
