/**
 * @Autor Saúl López Díez
 * Clase Administrador_GestionLibrosViewModel con el ViewModel de la clase Administrador_GestionLibros
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

import ioc.android.biblioapp.Model.Clases.Llibre;
import ioc.android.biblioapp.Model.Repositorio.BiblioAppRepo;

public class Administrador_GestionLibrosViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<Collection<Llibre>> mensaje;
    private BiblioAppRepo biblioAppRepo;
    private MutableLiveData<Collection<Llibre>> mutableLiveData;
    private static Llibre[] llibres;

    private static String tituloLibroDetalle;

    public static void setTituloLibroDetalle(String nombreUsuarioDetalle1) {
        tituloLibroDetalle = nombreUsuarioDetalle1;
    }

    public static String getTituloLibroDetalle() {
        return tituloLibroDetalle;
    }

    public static Llibre[] getLlibres() {
        return llibres;
    }

    /**
     *
     * @return Pasa por el listado de libros y devuelve libro
     */
    public static Llibre conseguirLibroPorTitulo() {
        Llibre libro = null;
        Llibre[] listado = getLlibres();
        String nombre = getTituloLibroDetalle();
        for (int i = 0; i <= listado.length - 1; i++) {
            if (listado[i].getTitulLlibre().equalsIgnoreCase(nombre)) {
                libro = listado[i];
            }
        }
        return libro;
    }

    public Administrador_GestionLibrosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Gestión de libros");
        biblioAppRepo = new BiblioAppRepo();
    }

    public LiveData<String> getText() {
        return mText;
    }

    /**
     *
     * @param administrador_gestionLibrosViewModel viewModel
     * @param context Contexto del fragmento
     * @param token token de seguridad
     * @return devuelve listado con libros
     */
    public LiveData<Collection<Llibre>> getListaLibros(Administrador_GestionLibrosViewModel administrador_gestionLibrosViewModel, Context context, String token) {

        mensaje = new MutableLiveData<>();
        administrador_gestionLibrosViewModel.ConseguirLibrosRepositorio(token).observe((LifecycleOwner) context, new Observer<Collection<Llibre>>() {//Generamos un observer a la espera de la consulta con el repositorio
            @Override
            public void onChanged(Collection llibre) {//con la respuesta
                if (llibre != null) {//si no es null
                    Collection<Llibre> u = new ArrayList<>();
                    llibres = new Llibre[llibre.size()];
                    Iterator it = llibre.iterator();
                    int j = 0;
                    while (it.hasNext()) {
                        llibres[j] = (Llibre) it.next();
                        Llibre prueba = llibres[j];
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
     * @return datos de la consulta al repositorio, una instancia de Usuari
     */
    public LiveData<Collection<Llibre>> ConseguirLibrosRepositorio(String token) {
        if (mutableLiveData == null) {
            mutableLiveData = biblioAppRepo.pideLibros(token);
        }
        return mutableLiveData;
    }
}