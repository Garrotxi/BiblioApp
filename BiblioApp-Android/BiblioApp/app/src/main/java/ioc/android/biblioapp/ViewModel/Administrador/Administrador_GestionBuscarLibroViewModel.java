package ioc.android.biblioapp.ViewModel.Administrador;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import ioc.android.biblioapp.Model.Clases.Llibre;
import ioc.android.biblioapp.Model.Repositorio.BiblioAppRepo;

public class Administrador_GestionBuscarLibroViewModel extends ViewModel {

    private MutableLiveData<String> mText,mText2;
    private MutableLiveData<Llibre> mensaje, mensaje2;
    private BiblioAppRepo biblioAppRepo;
    private MutableLiveData<Llibre> mutableLiveData;

    private Llibre llibre;


    public Administrador_GestionBuscarLibroViewModel() {

        biblioAppRepo = new BiblioAppRepo();
    }


    public LiveData<Llibre> buscarLibroId(Administrador_GestionBuscarLibroViewModel administrador_gestionBuscarLibroViewModel, Context context, String token, Llibre llibre) {
        //mText = new MutableLiveData<>();
        mensaje = new MutableLiveData<>();
        administrador_gestionBuscarLibroViewModel.buscaIdBook(llibre, token).observe((LifecycleOwner) context, new Observer<Llibre>() {
            @Override
            public void onChanged(Llibre llibre) {
                mensaje.setValue(llibre);
            }

        });


        return mensaje;
    }

    public LiveData<Llibre> buscaIdBook(Llibre llibre, String token) {
        if (mensaje2==null) {
            mensaje2 = biblioAppRepo.buscaLibroId(llibre, token);
        }
        return mensaje2;
    }

    public LiveData<Llibre> buscarLibroTitulo(Administrador_GestionBuscarLibroViewModel administrador_gestionBuscarLibroViewModel, Context context, String token, Llibre llibre) {
        //mText = new MutableLiveData<>();
        mensaje = new MutableLiveData<>();
        administrador_gestionBuscarLibroViewModel.buscaTituloBook(llibre, token).observe((LifecycleOwner) context, new Observer<Llibre>() {

            @Override
            public void onChanged(Llibre s) {
                mensaje.setValue(s);

            }
        });

        return mensaje;
    }

    public LiveData<Llibre> buscaTituloBook(Llibre llibre, String token) {
        if (mensaje2==null) {
            mensaje2 = biblioAppRepo.buscaLibroTitulo(llibre, token);
        }
        return mensaje2;
    }
}
