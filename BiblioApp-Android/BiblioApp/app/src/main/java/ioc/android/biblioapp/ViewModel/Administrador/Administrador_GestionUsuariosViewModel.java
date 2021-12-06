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

import ioc.android.biblioapp.Model.Clases.Usuari;
import ioc.android.biblioapp.Model.Repositorio.BiblioAppRepo;

public class Administrador_GestionUsuariosViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<Collection<Usuari>> mensaje;
    private BiblioAppRepo biblioAppRepo;
    private MutableLiveData<Collection<Usuari>> mutableLiveData;
    private static Usuari[] usuaris;
    private static String nombreUsuarioDetalle;

    public static void setNombreUsuarioDetalle(String nombreUsuarioDetalle1) {
        nombreUsuarioDetalle = nombreUsuarioDetalle1;
    }

    public static String getNombreUsuarioDetalle() {
        return nombreUsuarioDetalle;
    }

    public static Usuari[] getUsuaris() {
        return usuaris;
    }

    public static Usuari conseguirUsuarioPorNombre () {
       Usuari usuario = null;
        Usuari[] listado= getUsuaris();
        String nombre= getNombreUsuarioDetalle();
       for (int i=0; i<=listado.length-1;i++){

           if (listado[i].getNom().equalsIgnoreCase(nombre)){
               usuario=listado[i];
           }

       }

       return usuario;



    }

    /**
     * Constructor de Administrador_GestionUsuariosViewModel
     */


    public Administrador_GestionUsuariosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Gestion de usuarios");
        biblioAppRepo = new BiblioAppRepo();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<Collection<Usuari>> getListaUsuarios(Administrador_GestionUsuariosViewModel administrador_gestionUsuariosViewModel, Context context, String token) {

        mensaje = new MutableLiveData<>();

        administrador_gestionUsuariosViewModel.ConseguirUsuariosRepositorio(token).observe((LifecycleOwner) context, new Observer<Collection<Usuari>>() {//Generamos un observer a la espera de la consulta con el repositorio
            @Override
            public void onChanged(Collection usuari) {//con la respuesta
                if (usuari != null) {//si no es null
                    Collection<Usuari> u= new ArrayList<>();
                    usuaris= new Usuari[usuari.size()];
                    Iterator it = usuari.iterator();

                    int j=0;
                    while (it.hasNext()){
                        //usuaris.add((Usuari) it.next());

                        usuaris[j]= (Usuari) it.next();
                        Usuari prueba= usuaris[j];
                        u.add(prueba);
                        j= j+1;
                    }

                    mensaje.setValue(u);

                }else {
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
    public LiveData<Collection<Usuari>> ConseguirUsuariosRepositorio(String token) {

        if (mutableLiveData == null) {
            mutableLiveData = biblioAppRepo.pideUsuarios(token);
        }
        return mutableLiveData;
    }


}