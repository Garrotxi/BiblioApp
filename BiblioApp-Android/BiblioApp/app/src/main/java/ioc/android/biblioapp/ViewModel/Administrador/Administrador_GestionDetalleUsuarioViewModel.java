package ioc.android.biblioapp.ViewModel.Administrador;

import static ioc.android.biblioapp.ViewModel.Administrador.Administrador_GestionUsuariosViewModel.conseguirUsuarioPorNombre;
import static ioc.android.biblioapp.ViewModel.Administrador.Administrador_GestionUsuariosViewModel.getNombreUsuarioDetalle;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Collection;

import ioc.android.biblioapp.Model.Clases.Usuari;
import ioc.android.biblioapp.Model.Repositorio.BiblioAppRepo;

public class Administrador_GestionDetalleUsuarioViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<Usuari> mensaje;
    private BiblioAppRepo biblioAppRepo;
    private MutableLiveData<Collection<Usuari>> mutableLiveData;
    private Usuari usuario;


    public Administrador_GestionDetalleUsuarioViewModel() {

        biblioAppRepo = new BiblioAppRepo();
    }


    public LiveData<Usuari> conseguirUsuario(Administrador_GestionDetalleUsuarioViewModel administrador_gestionDetalleUsuarioViewModel, Context context, String token) {
        mensaje = new MutableLiveData<>();
        String nombre= getNombreUsuarioDetalle();
        usuario=conseguirUsuarioPorNombre();

        mensaje.setValue(usuario);
        return mensaje;
    }
}
