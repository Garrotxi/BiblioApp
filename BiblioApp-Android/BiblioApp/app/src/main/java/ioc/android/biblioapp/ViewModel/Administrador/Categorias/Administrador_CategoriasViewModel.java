/**
 * @Autor Saúl López Díez
 * Clase Administrador_CategoriasViewModel con el ViewModel de la clase Administrador_CategoriasFragment
 */

package ioc.android.biblioapp.ViewModel.Administrador.Categorias;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import ioc.android.biblioapp.Model.Clases.Categoria;
import ioc.android.biblioapp.Model.Repositorio.BiblioAppRepo;

public class Administrador_CategoriasViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<Collection<Categoria>> mensaje;
    private BiblioAppRepo biblioAppRepo;
    private MutableLiveData<Collection<Categoria>> mutableLiveData;
    private static Categoria[] categorias;
    private static String nombreCategoriaDetalle;

    public static void setNombreCategoriaDetalle(String nombreCategoriaDetalle1) {
        nombreCategoriaDetalle = nombreCategoriaDetalle1;
    }

    public static String getNombreCategoriaDetalle() {
        return nombreCategoriaDetalle;
    }

    public static Categoria[] getCategorias() {
        return categorias;
    }

    public static Categoria conseguirCategoriaPorNombre() {
        Categoria categoria = null;
        Categoria[] listado = getCategorias();
        String nombre = getNombreCategoriaDetalle();
        for (int i = 0; i <= listado.length - 1; i++) {
            if (listado[i].getNom().equalsIgnoreCase(nombre)) {
                categoria = listado[i];
            }
        }
        return categoria;
    }

    /**
     * Constructor de Administrador_CategoriasViewModel
     */


    public Administrador_CategoriasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Gestion de Categoria");
        biblioAppRepo = new BiblioAppRepo();
    }

    public LiveData<String> getText() {
        return mText;
    }

    /**
     *
     * @param administrador_categoriasViewModel viewModel
     * @param context contexto del fragmento
     * @param token token de seguridad
     * @return lista de autores
     */
    public LiveData<Collection<Categoria>> getListaCategorias(Administrador_CategoriasViewModel administrador_categoriasViewModel, Context context, String token) {

        mensaje = new MutableLiveData<>();

        administrador_categoriasViewModel.ConseguirCategoriasRepositorio(token,context).observe((LifecycleOwner) context, new Observer<Collection<Categoria>>() {//Generamos un observer a la espera de la consulta con el repositorio
            @Override
            public void onChanged(Collection cat) {//con la respuesta
                if (cat != null) {//si no es null
                    Collection<Categoria> u = new ArrayList<>();
                    categorias = new Categoria[cat.size()];
                    Iterator it = cat.iterator();

                    int j = 0;
                    while (it.hasNext()) {
                        categorias[j] = (Categoria) it.next();
                        Categoria prueba = categorias[j];
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
     * @return datos de la consulta al repositorio, una instancia de Categoria
     */
    public LiveData<Collection<Categoria>> ConseguirCategoriasRepositorio(String token, Context context) {
        if (mutableLiveData == null) {
            mutableLiveData = biblioAppRepo.pideCategorias(token,context);
        }
        return mutableLiveData;
    }
}