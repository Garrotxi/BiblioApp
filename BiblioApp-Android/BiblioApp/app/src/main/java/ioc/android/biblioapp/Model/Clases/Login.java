/**
 @Autor Saúl López Díez
 Clase Login_Activity con datos del login
 */
package ioc.android.biblioapp.Model.Clases;

public class Login {
    private String nomUsuari;
    private String contrasenya;

    public Login (String nomUsuari, String contrasenya){
        this.nomUsuari = nomUsuari;
        this.contrasenya = contrasenya;
    }

    public String getNomUsuari() {
        return nomUsuari;
    }

    public void setNomUsuari(String nomUsuari) {
        this.nomUsuari = nomUsuari;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }
}
