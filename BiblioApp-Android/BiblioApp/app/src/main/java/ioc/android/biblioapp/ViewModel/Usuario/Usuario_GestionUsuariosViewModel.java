/**
 * @Autor Saúl López Díez
 * Clase Usuario_GestionUsuariosViewModel con el ViewModel de la clase Usuario_GestionUsuarios
 */


package ioc.android.biblioapp.ViewModel.Usuario;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import ioc.android.biblioapp.Model.Clases.Usuari;
import ioc.android.biblioapp.Model.Repositorio.BiblioAppRepo;

public class Usuario_GestionUsuariosViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String> mText2;
    private MutableLiveData<Usuari> mensaje, mensaje2;
    private BiblioAppRepo biblioAppRepo;
    private MutableLiveData<Usuari> mutableLiveData;
    private Usuari usuario;

    public void setUsuario(Usuari usuario) {
        this.usuario = usuario;
    }

    public Usuario_GestionUsuariosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Gestion de usuarios");
        biblioAppRepo = new BiblioAppRepo();
    }

    public LiveData<String> getText() {
        return mText;
    }



/*
    *//**
     *
     * @param usuari viewModel
     * @param context Contexto del fragmento
     * @param token token de seguridad
     * @param usuari Usuari a buscar, con ID
     * @return
     *//*
    public LiveData<Usuari> conseguirUsuario(Usuario_GestionUsuariosViewModel usuario_gestionUsuariosViewModel, Context context, String token, Usuari usuari) {
        mensaje = new MutableLiveData<>();
        usuario_gestionUsuariosViewModel.buscaIdUsuari(usuari, token).observe((LifecycleOwner) context, new Observer<Usuari>() {
            @Override
            public void onChanged(Usuari usuari1) {
                mensaje.setValue(usuari1);
                setUsuario(usuari1);
            }
        });
        return mensaje;
    }

    *//**
     *
     * @param usuari usuario a buscar, con ID
     * @param token token de seguridad
     * @return
     *//*
    public LiveData<Usuari> buscaIdUsuari(Usuari usuari, String token) {
        if (mensaje2==null) {
            mensaje2 = biblioAppRepo.buscaUsuarioId(usuari, token);
        }
        return mensaje2;
    }*/



    /**
     *
     * @param usuario_gestionUsuariosViewModel viewModel
     * @param context Contexto del fragmento
     * @param token token de seguretat
     * @param usuari Usuario con datos modificados
     * @return usuario modificado
     */
    public LiveData<Usuari> modificarUsuario(Usuario_GestionUsuariosViewModel usuario_gestionUsuariosViewModel, Context context, String token, Usuari usuari) {
        mensaje = new MutableLiveData<>();
        usuario_gestionUsuariosViewModel.modificarUsuarios(usuari, token, context).observe((LifecycleOwner) context, new Observer<Usuari>() {
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
    public LiveData<Usuari> modificarUsuarios(Usuari usuari, String token, Context context) {
        if (mutableLiveData==null) {
            mutableLiveData = biblioAppRepo.modificarUsuario(usuari, token,context);
        }
        return mutableLiveData;
    }

}