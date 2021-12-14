/**
 * @Autor Saúl López Díez
 * Clase Administrador_PrestecViewModel con el ViewModel de la clase Administrador_PrestecFragment
 */

package ioc.android.biblioapp.ViewModel.Administrador.Prestamos;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import ioc.android.biblioapp.Model.Clases.Prestec;
import ioc.android.biblioapp.Model.Repositorio.BiblioAppRepo;

public class Administrador_PrestecViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<Collection<Prestec>> mensaje;
    private BiblioAppRepo biblioAppRepo;
    private MutableLiveData<Collection<Prestec>> mutableLiveData;
    private static Prestec[] prestecs;

    private static String tituloPrestecDetalle;

    public static void setIdPrestecDetalle(String idPrestecDetalle1) {
        tituloPrestecDetalle = idPrestecDetalle1;
    }

    public static String getIdPrestecDetalle() {
        return tituloPrestecDetalle;
    }

    public static Prestec[] getPrestecs() {
        return prestecs;
    }

    /**
     *
     * @return Pasa por el listado de prestecs y devuelve prestec
     */
    public static Prestec conseguirPrestecPorId() {
        Prestec prestec = null;
        Prestec[] listado = getPrestecs();
        String nombre = getIdPrestecDetalle();
        for (int i = 0; i <= listado.length - 1; i++) {
            if (listado[i].getIdPrestec().equalsIgnoreCase(nombre)) {
                prestec = listado[i];
            }
        }
        return prestec;
    }

    public Administrador_PrestecViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Gestión de prestecs");
        biblioAppRepo = new BiblioAppRepo();
    }

    public LiveData<String> getText() {
        return mText;
    }

    /**
     *
     * @param administrador_prestecViewModel viewModel
     * @param context Contexto del fragmento
     * @param token token de seguridad
     * @return devuelve listado con prestecs
     */
    public LiveData<Collection<Prestec>> getListaPrestecs(Administrador_PrestecViewModel administrador_prestecViewModel, Context context, String token) {

        mensaje = new MutableLiveData<>();
        administrador_prestecViewModel.ConseguirPrestecsRepositorio(token, context).observe((LifecycleOwner) context, new Observer<Collection<Prestec>>() {//Generamos un observer a la espera de la consulta con el repositorio
            @Override
            public void onChanged(Collection prestec) {//con la respuesta
                if (prestec != null) {//si no es null
                    Collection<Prestec> u = new ArrayList<>();
                    prestecs = new Prestec[prestec.size()];
                    Iterator it = prestec.iterator();
                    int j = 0;
                    while (it.hasNext()) {
                        prestecs[j] = (Prestec) it.next();
                        Prestec prueba = prestecs[j];
                        u.add(prueba);
                        j = j + 1;
                    }
                    mensaje.setValue(u);
                } else {
                    mensaje.setValue(null);
                }
            }
        });
        return mensaje;
    }


    /**
     * @param
     * @return datos de la consulta al repositorio, una instancia de Prestec
     */
    public LiveData<Collection<Prestec>> ConseguirPrestecsRepositorio(String token, Context context) {
        if (mutableLiveData == null) {
            mutableLiveData = biblioAppRepo.pidePrestecs(token, context);
        }
        return mutableLiveData;
    }
}