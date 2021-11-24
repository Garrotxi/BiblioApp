/**
 * @Autor Saúl López Díez
 * Clase Administrador_HomeViewModel con el ViewModel de la clase Administrador_Home
 */


package ioc.android.biblioapp.ViewModel.Administrador;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Administrador_HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;


    public Administrador_HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Bienvenido Administrador");




    }

    public LiveData<String> getText() {
        return mText;
    }

}