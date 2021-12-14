/**
 * @Autor Saúl López Díez
 * Interfaz BiblioAppCliente con las llamadas al API
 */

package ioc.android.biblioapp.Model.Servicio;

import java.util.Collection;

import ioc.android.biblioapp.Model.Clases.Autor;
import ioc.android.biblioapp.Model.Clases.Categoria;
import ioc.android.biblioapp.Model.Clases.Llibre;
import ioc.android.biblioapp.Model.Clases.Login;
import ioc.android.biblioapp.Model.Clases.Prestec;
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
    Call<Collection<Usuari>> ListadoUsuarios(@Header("Authorization") String token
    );

    @GET("/llibre/llistaLlibres")//llamada para conseguir lista de libros
    Call<Collection<Llibre>> ListadoLibros(@Header("Authorization") String token
    );

    @POST("/llibre/crearLlibre")//llamada para crear un libro
    Call<Registro> Registro(
            @Body Llibre registro
    );


    @PUT ("/auth/actualitzarUsuari/{id}")//llamada para actualizar un usuario
    Call<Usuari> ModificaUsuarios(@Header("Authorization") String token,
                         @Path("id") String id,
                         @Body Usuari user
    );

    @DELETE ("/auth/eliminarUsuari/{id}")//llamada para eliminar un usuario
    Call <Registro> BorraUsuario(@Header("Authorization") String token,
                                     @Path("id") String id
    );

    @PUT ("/llibre/actualizarLlibre/{id}")//llamada para actualizar un libro
    Call<Llibre> ModificaLibros(@Header("Authorization") String token,
                                  @Path("id") String id,
                                  @Body Llibre book
    );

    @DELETE ("/llibre/eliminarLlibre/{id}")//llamada para eliminar un libro
    Call <Registro> BorraLibro(@Header("Authorization") String token,
                                 @Path("id") String id
    );

    @GET ("/llibre/detallLlibre/{id}")//llamada para conseguir un libro por su id
    Call <Llibre> BuscaLibroId(@Header("Authorization") String token,
                               @Path("id") String id
    );
    @GET ("/llibre/detallTitul/{titol}")//llamada para conseguir un libro por su titulo
    Call <Llibre> BuscaLibroTitulo(@Header("Authorization") String token,
                               @Path("titol") String titol
    );

    @GET("/autor/llistaAutors")//llamada para conseguir lista de autores
    Call<Collection<Autor>> ListadoAutores(@Header("Authorization") String token
    );

    @PUT ("/autor/actualizarAutor/{id}")//llamada para actualizar un autor
    Call<Autor> ModificaAutores(@Header("Authorization") String token,
                                @Path("id") String id,
                                @Body Autor autor
    );

    @DELETE ("/autor/eliminarAutor/{id}")//llamada para eliminar un autor
    Call <Registro> BorraAutor(@Header("Authorization") String token,
                               @Path("id") String id
    );

    @POST("/autor/crearAutor")//llamada para crear un autor
    Call<Registro> NuevoAutor(
            @Body Autor registro
    );

    @GET("/prestec/llistaPrestecs")//llamada para conseguir lista de prestamos
    Call<Collection<Prestec>> ListadoPrestecs(@Header("Authorization") String token
    );

    @POST("/prestec/crearPrestec")//llamada para crear un prestamo
    Call<Registro> NouPrestec (@Body Prestec prestec
    );

    @PUT ("/prestec/actualizarPrestec/{id}")//llamada para actualizar un prestamo
    Call<Prestec> ModificaPrestec(@Header("Authorization") String token,
                                @Path("id") String id,
                                @Body Prestec loan
    );

    @DELETE ("/prestec/eliminarPrestec/{id}")//llamada para eliminar un prestamo
    Call <Registro> BorraPrestec(@Header("Authorization") String token,
                               @Path("id") String id
    );

    @GET ("/prestec/detallPrestecId/{id}")//llamada para conseguir un prestamo por su id
    Call <Prestec> BuscaPrestecId(@Header("Authorization") String token,
                               @Path("id") String id
    );

    @GET("/categoria/llistaCategories")//llamada para conseguir lista de Categorias
    Call<Collection<Categoria>> ListadoCategorias(@Header("Authorization") String token
    );

    @PUT ("/categoria/actualizarCategoria/{id}")//llamada para actualizar una Categoria
    Call<Categoria> ModificaCategorias(@Header("Authorization") String token,
                                @Path("id") String id,
                                @Body Categoria categoria
    );

    @DELETE ("/categoria/eliminarCategoria/{id}")//llamada para eliminar una Categoria
    Call <Registro> BorraCategoria(@Header("Authorization") String token,
                               @Path("id") String id
    );

    @POST("/categoria/crearCategoria")//llamada para crear una Categoria
    Call<Registro> NuevaCategoria(
            @Body Categoria registro
    );

    @GET ("/usuari/detallUsuari/{id}")//llamada para conseguir un libro por su id
    Call <Usuari> BuscaUsuarioId(@Header("Authorization") String token,
                               @Path("id") String id
    );


}
