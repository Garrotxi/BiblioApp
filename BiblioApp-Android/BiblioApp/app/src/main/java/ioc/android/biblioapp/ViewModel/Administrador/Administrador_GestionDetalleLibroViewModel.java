package ioc.android.biblioapp.ViewModel.Administrador;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import ioc.android.biblioapp.Model.Clases.Llibre;
import ioc.android.biblioapp.Model.Repositorio.BiblioAppRepo;

public class Administrador_GestionDetalleLibroViewModel extends ViewModel {

    private MutableLiveData<String> mText,mText2;
    private MutableLiveData<Llibre> mensaje;
    private BiblioAppRepo biblioAppRepo;
    private MutableLiveData<Llibre> mutableLiveData;

    private Llibre llibre;


    public Administrador_GestionDetalleLibroViewModel() {

        biblioAppRepo = new BiblioAppRepo();
    }


    public LiveData<Llibre> conseguirLibro(Administrador_GestionDetalleLibroViewModel administrador_gestionDetalleLibroViewModel, Context context, String token) {
        mensaje = new MutableLiveData<>();
        String titulo=Administrador_GestionLibrosViewModel.getTituloLibroDetalle();
        llibre=Administrador_GestionLibrosViewModel.conseguirLibroPorTitulo();

        mensaje.setValue(llibre);
        return mensaje;
    }


    public LiveData<Llibre> modificarLibro(Administrador_GestionDetalleLibroViewModel administrador_gestionDetalleLibroViewModel, Context context, String token, Llibre llibre) {
        //mText = new MutableLiveData<>();
        mensaje = new MutableLiveData<>();
        administrador_gestionDetalleLibroViewModel.modificarLibros(llibre, token).observe((LifecycleOwner) context, new Observer<Llibre>() {

            @Override
            public void onChanged(Llibre s) {
                mensaje.setValue(s);

            }
        });



        return mensaje;
    }

    public LiveData<Llibre> modificarLibros(Llibre llibre, String token) {
        if (mutableLiveData==null) {
            mutableLiveData = biblioAppRepo.modificarLlibre(llibre, token);
        }
        return mutableLiveData;
    }

    public LiveData<String> borrarLibro(Administrador_GestionDetalleLibroViewModel administrador_gestionDetalleUsuarioViewModel, Context context, String token, Llibre llibre) {
        mText = new MutableLiveData<>();
        //mensaje = new MutableLiveData<>();
        administrador_gestionDetalleUsuarioViewModel.borraBook(llibre, token).observe((LifecycleOwner) context, new Observer<String>() {

            @Override
            public void onChanged(String s) {
                mText.setValue(s);

            }
        });

        return mText;
    }

    public LiveData<String> borraBook(Llibre llibre, String token) {
        if (mText2==null) {
            mText2 = biblioAppRepo.borraLibro(llibre, token);
        }
        return mText2;
    }
}
