/**
 * @Autor Saúl López Díez
 * Clase Administrador_GestionUsuariosViewModel con el ViewModel de la clase Administrador_GestionUsuarios
 */

package ioc.android.biblioapp.ViewModel.Administrador;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import ioc.android.biblioapp.Model.Clases.Autor;
import ioc.android.biblioapp.Model.Repositorio.BiblioAppRepo;

public class Administrador_AutoresViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<Collection<Autor>> mensaje;
    private BiblioAppRepo biblioAppRepo;
    private MutableLiveData<Collection<Autor>> mutableLiveData;
    private static Autor[] autors;
    private static String nombreAutorDetalle;

    public static void setNombreAutorDetalle(String nombreAutorDetalle1) {
        nombreAutorDetalle = nombreAutorDetalle1;
    }

    public static String getNombreAutorDetalle() {
        return nombreAutorDetalle;
    }

    public static Autor[] getAutors() {
        return autors;
    }

    public static Autor conseguirAutorPorNombre() {
        Autor autor = null;
        Autor[] listado = getAutors();
        String nombre = getNombreAutorDetalle();
        for (int i = 0; i <= listado.length - 1; i++) {
            if (listado[i].getNom().equalsIgnoreCase(nombre)) {
                autor = listado[i];
            }
        }
        return autor;
    }

    /**
     * Constructor de Administrador_GestionUsuariosViewModel
     */


    public Administrador_AutoresViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Gestion de autores");
        biblioAppRepo = new BiblioAppRepo();
    }

    public LiveData<String> getText() {
        return mText;
    }

    /**
     *
     * @param administrador_autoresViewModel viewModel
     * @param context contexto del fragmento
     * @param token token de seguridad
     * @return lista de autores
     */
    public LiveData<Collection<Autor>> getListaAutores(Administrador_AutoresViewModel administrador_autoresViewModel, Context context, String token) {

        mensaje = new MutableLiveData<>();

        administrador_autoresViewModel.ConseguirAutoresRepositorio(token).observe((LifecycleOwner) context, new Observer<Collection<Autor>>() {//Generamos un observer a la espera de la consulta con el repositorio
            @Override
            public void onChanged(Collection autor) {//con la respuesta
                if (autor != null) {//si no es null
                    Collection<Autor> u = new ArrayList<>();
                    autors = new Autor[autor.size()];
                    Iterator it = autor.iterator();

                    int j = 0;
                    while (it.hasNext()) {
                        autors[j] = (Autor) it.next();
                        Autor prueba = autors[j];
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
     * @return datos de la consulta al repositorio, una instancia de Autor
     */
    public LiveData<Collection<Autor>> ConseguirAutoresRepositorio(String token) {
        if (mutableLiveData == null) {
            mutableLiveData = biblioAppRepo.pideAutores(token);
        }
        return mutableLiveData;
    }
}