/**
 * @Autor Saúl López Díez
 * Clase Administrador_BuscarPrestecViewModel con el viewModel de la busqueda de prestamos
 */

package ioc.android.biblioapp.ViewModel.Administrador.Prestamos;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import ioc.android.biblioapp.Model.Clases.Prestec;
import ioc.android.biblioapp.Model.Repositorio.BiblioAppRepo;

public class Administrador_BuscarPrestecViewModel extends ViewModel {


    private MutableLiveData<Prestec> mensaje, mensaje2;
    private BiblioAppRepo biblioAppRepo;

    private Prestec prestec;

    public void setPrestec(Prestec prestec) {
        this.prestec = prestec;
    }

    public Administrador_BuscarPrestecViewModel() {

        biblioAppRepo = new BiblioAppRepo();
    }

    /**
     *
     * @param administrador_gestionBuscarLibroViewModel viewModel
     * @param context Contexto del fragmento
     * @param token token de seguridad
     * @param prestec prestamo a buscar, con ID
     * @return
     */
    public LiveData<Prestec> buscarPrestecId(Administrador_BuscarPrestecViewModel administrador_gestionBuscarLibroViewModel, Context context, String token, Prestec prestec) {
        mensaje = new MutableLiveData<>();
        administrador_gestionBuscarLibroViewModel.buscaIdLoan(prestec, token, context).observe((LifecycleOwner) context, new Observer<Prestec>() {
            @Override
            public void onChanged(Prestec prestec1) {
                mensaje.setValue(prestec1);
                setPrestec(prestec1);
            }
        });
        return mensaje;
    }

    /**
     *
     * @param prestec prestamo a buscar, con ID
     * @param token token de seguridad
     * @return
     */
    public LiveData<Prestec> buscaIdLoan(Prestec prestec, String token, Context context) {
        if (mensaje2==null) {
            mensaje2 = biblioAppRepo.buscaPrestecId(prestec, token, context);
        }
        return mensaje2;
    }


}
