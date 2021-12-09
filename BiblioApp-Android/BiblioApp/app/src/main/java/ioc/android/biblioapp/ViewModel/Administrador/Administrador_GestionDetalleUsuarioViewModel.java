/**
 * @Autor Saúl López Díez
 * Clase Administrador_GestionDetalleUsuarioViewModel con el viewModel del detalle de un usuario y metodos para modificar y eliminar
 */

package ioc.android.biblioapp.ViewModel.Administrador;

import static ioc.android.biblioapp.ViewModel.Administrador.Administrador_GestionUsuariosViewModel.conseguirUsuarioPorNombre;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import ioc.android.biblioapp.Model.Clases.Usuari;
import ioc.android.biblioapp.Model.Repositorio.BiblioAppRepo;

public class Administrador_GestionDetalleUsuarioViewModel extends ViewModel {

    private MutableLiveData<String> mText,mText2;
    private MutableLiveData<Usuari> mensaje;
    private BiblioAppRepo biblioAppRepo;
    private MutableLiveData<Usuari> mutableLiveData;
    private Usuari usuario;


    public Administrador_GestionDetalleUsuarioViewModel() {

        biblioAppRepo = new BiblioAppRepo();
    }

    /**
     *
     * @param administrador_gestionDetalleUsuarioViewModel viewModel
     * @param context Contexto del fragmento
     * @param token token de seguretat
     * @return usuario encontrado o null
     */
    public LiveData<Usuari> conseguirUsuario(Administrador_GestionDetalleUsuarioViewModel administrador_gestionDetalleUsuarioViewModel, Context context, String token) {
        mensaje = new MutableLiveData<>();
        usuario=conseguirUsuarioPorNombre();
        mensaje.setValue(usuario);
        return mensaje;
    }


    /**
     *
     * @param administrador_gestionDetalleUsuarioViewModel viewModel
     * @param context Contexto del fragmento
     * @param token token de seguretat
     * @param usuari Usuario con datos modificados
     * @return usuario modificado
     */
    public LiveData<Usuari> modificarUsuario(Administrador_GestionDetalleUsuarioViewModel administrador_gestionDetalleUsuarioViewModel, Context context, String token, Usuari usuari) {
        mensaje = new MutableLiveData<>();
        administrador_gestionDetalleUsuarioViewModel.modificarUsuarios(usuari, token).observe((LifecycleOwner) context, new Observer<Usuari>() {
            @Override
            public void onChanged(Usuari s) {
                mensaje.setValue(s);
            }
        });
        return mensaje;
    }

    /**
     *
     * @param usuari Usuario con datos modificados
     * @param token token de seguretat
     * @return respuesta API
     */
    public LiveData<Usuari> modificarUsuarios(Usuari usuari, String token) {
        if (mutableLiveData==null) {
            mutableLiveData = biblioAppRepo.modificarUsuario(usuari, token);
        }
        return mutableLiveData;
    }

    /**
     *
     * @param administrador_gestionDetalleUsuarioViewModel viewModel
     * @param context Contexto del fragmento
     * @param token token de seguretat
     * @param usuari Usuario a borrar
     * @return Mensaje satisfactorio o error
     */
    public LiveData<String> borrarUsuario(Administrador_GestionDetalleUsuarioViewModel administrador_gestionDetalleUsuarioViewModel, Context context, String token, Usuari usuari) {
        mText = new MutableLiveData<>();
        administrador_gestionDetalleUsuarioViewModel.borraUser(usuari, token).observe((LifecycleOwner) context, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mText.setValue(s);
            }
        });
        return mText;
    }

    /**
     *
     * @param usuari Usuario a borrar
     * @param token token de seguretat
     * @return respuesta API
     */
    public LiveData<String> borraUser(Usuari usuari, String token) {
        if (mText2==null) {
            mText2 = biblioAppRepo.borraUsuario(usuari, token);
        }
        return mText2;
    }
}
