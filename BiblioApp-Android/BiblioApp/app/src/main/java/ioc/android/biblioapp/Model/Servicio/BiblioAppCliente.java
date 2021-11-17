/**
 * @Autor Saúl López Díez
 * Interfaz BiblioAppCliente con las llamadas al API
 */

package ioc.android.biblioapp.Model.Servicio;

import ioc.android.biblioapp.Model.Clases.Login;
import ioc.android.biblioapp.Model.Clases.Registro;
import ioc.android.biblioapp.Model.Clases.Usuari;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BiblioAppCliente {

    @POST("/api/auth/login")//llamada para realizar un login
    Call<Usuari> Login(
            @Body Login login
    );

    @POST("/api/auth/register")//llamada para realizar un registro
    Call<Registro> Registro(
            @Body Usuari registro
    );


}
