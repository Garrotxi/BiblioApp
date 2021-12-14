/**
 * @Autor Saúl López Díez
 * Clase PantallaInicioUsuarioViewModel con el ViewModel de la clase PantallaInicioUsuario
 */

package ioc.android.biblioapp.ViewModel.Usuario;

import androidx.lifecycle.ViewModel;

import ioc.android.biblioapp.Model.Clases.Usuari;

public class PantallaInicioUsuarioViewModel extends ViewModel {

    private String token;
    private Usuari usuari;

    public void setToken(String token){this.token=token;}



}
