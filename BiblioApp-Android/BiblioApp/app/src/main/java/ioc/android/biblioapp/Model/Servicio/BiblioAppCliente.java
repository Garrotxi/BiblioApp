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
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

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

    @POST("/llibre/crearLlibre")//llamada para crear un libro
    Call<Registro> Registro(
            @Body Llibre registro
    );


    @PUT ("/auth/actualitzarUsuari/{id}")
    Call<Usuari> ModificaUsuarios(@Header("Authorization") String token,
                         @Path("id") String id,
                         @Body Usuari user
    );

    @DELETE ("/auth/eliminarUsuari/{id}")
    Call <Registro> BorraUsuario(@Header("Authorization") String token,
                                     @Path("id") String id
    );

    @PUT ("/llibre/actualizarLlibre/{id}")
    Call<Llibre> ModificaLibros(@Header("Authorization") String token,
                                  @Path("id") String id,
                                  @Body Llibre book
    );

    @DELETE ("/llibre/eliminarLlibre/{id}")
    Call <Registro> BorraLibro(@Header("Authorization") String token,
                                 @Path("id") String id
    );

    @GET ("/llibre/detallLlibre/{id}")
    Call <Llibre> BuscaLibroId(@Header("Authorization") String token,
                               @Path("id") String id
    );
    @GET ("/llibre/detallTitul/{titol}")
    Call <Llibre> BuscaLibroTitulo(@Header("Authorization") String token,
                               @Path("titol") String titol
    );


}
