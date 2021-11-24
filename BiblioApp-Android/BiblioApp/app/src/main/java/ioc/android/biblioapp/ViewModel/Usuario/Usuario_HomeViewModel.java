/**
 * @Autor Saúl López Díez
 * Clase Usuario_HomeViewModel con el ViewModel de la clase Usuario_Home
 */


package ioc.android.biblioapp.ViewModel.Usuario;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Usuario_HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Usuario_HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Bienvenido Usuario");
    }

    public LiveData<String> getText() {
        return mText;
    }
}