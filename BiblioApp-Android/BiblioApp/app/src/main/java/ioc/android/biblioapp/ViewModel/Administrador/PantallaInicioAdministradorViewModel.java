/**
 * @Autor Saúl López Díez
 * Clase PantallaInicioAdministradorViewModel con el ViewModel de la clase PantallaInicioAdministrador
 */

package ioc.android.biblioapp.ViewModel.Administrador;

import androidx.lifecycle.ViewModel;

public class PantallaInicioAdministradorViewModel extends ViewModel {

    private String token;

    public void setToken(String token){this.token=token;}

    public String getToken() {
        return token;
    }
}
