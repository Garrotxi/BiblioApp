/**
 * @Autor Saúl López Díez
 * Clase Usuario_GestionUsuariosViewModel con el ViewModel de la clase Usuario_GestionUsuarios
 */


package ioc.android.biblioapp.ViewModel.Usuario;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Usuario_GestionUsuariosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Usuario_GestionUsuariosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Gestion de usuarios");
    }

    public LiveData<String> getText() {
        return mText;
    }
}