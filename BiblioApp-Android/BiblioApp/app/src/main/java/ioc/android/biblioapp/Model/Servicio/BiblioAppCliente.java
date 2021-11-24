/**
 * @Autor Saúl López Díez
 * Interfaz BiblioAppCliente con las llamadas al API
 */

package ioc.android.biblioapp.Model.Servicio;

import java.util.Collection;

import ioc.android.biblioapp.Model.Clases.Llibre;
import ioc.android.biblioapp.Model.Clases.Login;
import ioc.android.biblioapp.Model.Clases.Registro;
import ioc.android.biblioapp.Model.Clases.Usuari;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface BiblioAppCliente {

    @POST("/auth/login")//llamada para realizar un login
    Call<Usuari> Login(
            @Body Login login
    );

    @POST("/auth/registrarUsuari")//llamada para realizar un registro
    Call<Registro> Registro(
            @Body Usuari registro
    );

    @GET("/auth/llistaUsuaris")//llamada para conseguir lista de usuarios
    Call<Collection<Usuari>> ListadoUsuarios(@Header("Authorization") String token);

    @GET("/llibre/llistaLlibres")//llamada para conseguir lista de libros
    Call<Collection<Llibre>> ListadoLibros(@Header("Authorization") String token);




}
