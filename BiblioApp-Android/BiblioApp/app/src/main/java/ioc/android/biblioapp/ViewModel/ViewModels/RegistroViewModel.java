/**
 * @Autor Saúl López Díez
 * Clase RegistroViewModel que gestiona las llamadas de la vista y trata con la parte del Modelo para conseguir la información
 * para registrar un nuevo usuario
 */
package ioc.android.biblioapp.ViewModel.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ioc.android.biblioapp.Model.Clases.Login;
import ioc.android.biblioapp.Model.Clases.Registro;
import ioc.android.biblioapp.Model.Clases.Usuari;
import ioc.android.biblioapp.Model.Repositorio.BiblioAppRepo;

public class RegistroViewModel  extends ViewModel {

    private BiblioAppRepo biblioAppRepo;
    private MutableLiveData<Registro> mutableLiveData;

    public RegistroViewModel(){
        biblioAppRepo = new BiblioAppRepo();
    }

    public LiveData<Registro> registro(Usuari usuari) {
        if (mutableLiveData==null) {
            mutableLiveData = biblioAppRepo.registro(usuari);
        }
        return mutableLiveData;
    }
}
