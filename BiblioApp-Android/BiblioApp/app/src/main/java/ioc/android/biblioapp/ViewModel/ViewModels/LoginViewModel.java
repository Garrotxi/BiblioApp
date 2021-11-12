package ioc.android.biblioapp.ViewModel.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ioc.android.biblioapp.Model.Clases.Login;
import ioc.android.biblioapp.Model.Clases.Usuari;
import ioc.android.biblioapp.Model.Repositorio.BiblioAppRepo;

public class LoginViewModel extends ViewModel {
    private BiblioAppRepo biblioAppRepo;
    private MutableLiveData<Usuari> mutableLiveData;

    public LoginViewModel(){
        biblioAppRepo = new BiblioAppRepo();
    }

    public LiveData<Usuari> getLogin(Login login) {
        if (mutableLiveData==null) {
            mutableLiveData = biblioAppRepo.requestLogin(login);
        }
        return mutableLiveData;
    }

}
