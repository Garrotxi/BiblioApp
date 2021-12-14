/**
 * @Autor Saúl López Díez
 * Clase BiblioAppRepo con el Repositorio de metodos para conseguir la información y enviarsela a la capa ViewModel
 */
package ioc.android.biblioapp.Model.Repositorio;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Collection;

import ioc.android.biblioapp.Model.Clases.Autor;
import ioc.android.biblioapp.Model.Clases.Categoria;
import ioc.android.biblioapp.Model.Clases.Llibre;
import ioc.android.biblioapp.Model.Clases.Login;
import ioc.android.biblioapp.Model.Clases.Prestec;
import ioc.android.biblioapp.Model.Clases.Registro;
import ioc.android.biblioapp.Model.Clases.Usuari;
import ioc.android.biblioapp.Model.Servicio.BiblioAppCliente;
import ioc.android.biblioapp.Model.Servicio.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Clase BiblioAppRepo
 */
public class BiblioAppRepo {
    private final String TAG = getClass().getSimpleName();



    /**
     * Constructor
     */
    public BiblioAppRepo() {
    }

    /**
     *
     * @param login  datos para realizar el login
     * @return mutableLiveData con la información devuelta por el API
     * Si la conexión resulta correcta, conseguiremos un objeto de la clase Usuari, en caso
     * contrario, nos devolvera null
     */
    public MutableLiveData<Usuari> pideLogin(Login login, Context context) {
        final MutableLiveData<Usuari> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable


        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, "login", context);// Generamos el servicio
               // ServiceGenerator.createService(BiblioAppCliente.class, "login");// Generamos el servicio

        biblioAppCliente.Login(login).enqueue(new Callback<Usuari>() {//procesamos en segundo plano el metodo login del servicio
            @Override
            public void onResponse(Call<Usuari> call, Response<Usuari> response) {//si el correcto conseguimos un usuario con la auth key
                if (response.isSuccessful() && response.body() != null) {
                    Usuari usuari;
                    usuari = response.body();
                    mutableLiveData.setValue(usuari);

                } else {
                    Log.e(TAG, "Login_Activity incorrecto");
                    mutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<Usuari> call, Throwable t) {//si falla la conexión
                Log.e(TAG, "Error de conexion");
            }
        });

        return mutableLiveData;
    }

    /**
     *
     * @param usuari datos para realizar el registro
     * @return registro objeto con el mensaje de la API
     * Si el registro es correcto nos devuelve un mensaje diciendo que se ha registrado,
     * en caso contrario nos envia un mensaje de error
     */
    public MutableLiveData<Registro> pideRegistro(Usuari usuari, Context context) {
        final MutableLiveData<Registro> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable


        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, "registro", context);// Generamos el servicio

        biblioAppCliente.Registro(usuari).enqueue(new Callback<Registro>() {//procesamos en segundo plano el metodo Registro_Activity del servicio
            Registro registro = new Registro();

            @Override
            public void onResponse(Call<Registro> call, Response<Registro> response) { //si es correcto conseguimos una respuesta satisactoria, con informacion en body
                if (response.isSuccessful() && response.body() != null) {
                    //Log.d(TAG, response.body().getMessage());
                    registro = response.body();
                    mutableLiveData.setValue(registro);
                } else {//si la respuesta no es satisfactoria, conseguimos el error del errorBody y generamos un objeto Registro_Activity
                    try {
                        String json = response.errorBody().string();
                        Gson gson = new Gson();
                        registro = gson.fromJson(json, Registro.class);
                        mutableLiveData.setValue(registro);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "Error al crear usuario");
                }
            }

            @Override
            public void onFailure(Call<Registro> call, Throwable t) {
                Log.e(TAG, "Registro_Activity incorrecto");
            }
        });
        return mutableLiveData;
    }

    /**
     *
     *
     * @return mutableLiveData con la información devuelta por el API
     * Si la conexión resulta correcta, conseguiremos un objeto Collection con los usuarios, en caso
     * contrario, nos devolvera null
     */
    public MutableLiveData<Collection<Usuari>> pideUsuarios(String token, Context context) {
        final MutableLiveData<Collection<Usuari>> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable


        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, token, context);// Generamos el servicio

        biblioAppCliente.ListadoUsuarios(token).enqueue(new Callback<Collection<Usuari>>() {//procesamos en segundo plano el metodo login del servicio
            @Override
            public void onResponse(Call<Collection<Usuari>> call, Response<Collection<Usuari>> response) {//si el correcto conseguimos un usuario con la auth key
                if (response.isSuccessful() && response.body() != null) {
                    Collection usuari;
                    usuari = response.body();
                    mutableLiveData.setValue(usuari);

                } else {
                    Log.e(TAG, "Error en BiblioAppREpo");
                    mutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<Collection<Usuari>> call, Throwable t) {//si falla la conexión
                Log.e(TAG, "Error de conexion  en BiblioAppREpo");
            }
        });

        return mutableLiveData;
    }


    /**
     *
     *
     * @return mutableLiveData con la información devuelta por el API
     * Si la conexión resulta correcta, conseguiremos un objeto Collection con los libros, en caso
     * contrario, nos devolvera null
     */
    public MutableLiveData<Collection<Llibre>> pideLibros(String token, Context context) {
        final MutableLiveData<Collection<Llibre>> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable


        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, token, context);// Generamos el servicio

        biblioAppCliente.ListadoLibros(token).enqueue(new Callback<Collection<Llibre>>() {//procesamos en segundo plano el metodo login del servicio
            @Override
            public void onResponse(Call<Collection<Llibre>> call, Response<Collection<Llibre>> response) {//si el correcto conseguimos un usuario con la auth key
                if (response.isSuccessful() && response.body() != null) {
                    Collection libros;
                    libros = response.body();
                    mutableLiveData.setValue(libros);

                } else {
                    Log.e(TAG, "Error en BiblioAppREpo");
                    mutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<Collection<Llibre>> call, Throwable t) {//si falla la conexión
                Log.e(TAG, "Error de conexion  en BiblioAppREpo");
            }
        });

        return mutableLiveData;
    }

    /**
     *
     * @param libro datos para realizar el registro
     * @return registro objeto con el mensaje de la API
     * Si el registro es correcto nos devuelve un mensaje diciendo que se ha registrado,
     * en caso contrario nos envia un mensaje de error
     */
    public MutableLiveData<Registro> añadeLibro(Llibre libro, String token, Context context) {
        final MutableLiveData<Registro> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable


        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, token, context);// Generamos el servicio

        biblioAppCliente.Registro(libro).enqueue(new Callback<Registro>() {//procesamos en segundo plano el metodo Registro_Activity del servicio
            Registro registro = new Registro();

            @Override
            public void onResponse(Call<Registro> call, Response<Registro> response) { //si es correcto conseguimos una respuesta satisactoria, con informacion en body
                if (response.isSuccessful() && response.body() != null) {
                    //Log.d(TAG, response.body().getMessage());
                    registro = response.body();
                    mutableLiveData.setValue(registro);
                } else {//si la respuesta no es satisfactoria, conseguimos el error del errorBody y generamos un objeto Registro_Activity
                    try {
                        String json = response.errorBody().string();
                        Gson gson = new Gson();
                        registro = gson.fromJson(json, Registro.class);
                        mutableLiveData.setValue(registro);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "Error al crear libro");
                }
            }

            @Override
            public void onFailure(Call<Registro> call, Throwable t) {
                Log.e(TAG, "Añadir Libro incorrecto");
            }
        });
        return mutableLiveData;
    }

    /**
     *
     * @param usuari Usuario a modificar, con todos los atributos
     * @param token
     * @return Usuario modificado
     */
    public MutableLiveData<Usuari> modificarUsuario(Usuari usuari, String token, Context context) {
        final MutableLiveData<Usuari> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable


        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, token, context);// Generamos el servicio

        biblioAppCliente.ModificaUsuarios(token, usuari.getIdUsuari(), usuari).enqueue(new Callback<Usuari>() {
            @Override
            public void onResponse(Call<Usuari> call, Response<Usuari> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Usuari usuari1= response.body();
                    mutableLiveData.setValue(usuari1);
                }
            }
            @Override
            public void onFailure(Call<Usuari> call, Throwable t) {
                Log.e(TAG, "Modificar usuario incorrecto");
            }

        });
        return mutableLiveData;
    }

    /**
     *
     * @param usuari Usuario a borrar
     * @param token
     * @return Mensaje retornado por API, satisfactorio o no
     */
    public MutableLiveData<String> borraUsuario(Usuari usuari, String token, Context context) {
        final MutableLiveData<String> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable

        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, token,context);// Generamos el servicio

        biblioAppCliente.BorraUsuario(token, usuari.getIdUsuari()).enqueue(new Callback<Registro>() {
            @Override
            public void onResponse(Call<Registro> call, Response<Registro> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Registro r= new Registro();
                    r=response.body();
                    String respuesta= r.getMissatge();
                    mutableLiveData.setValue(respuesta);
                }else{

                     String respuesta= response.errorBody().toString();
                    mutableLiveData.setValue(respuesta);
                }
            }
            @Override
            public void onFailure(Call<Registro> call, Throwable t) {
                Log.e(TAG, "Borrar usuario incorrecto");
            }
        });
        return mutableLiveData;
    }

    /**
     *
     * @param llibre Libro a modificar, con todos los atributos
     * @param token
     * @return Libro modificado
     */

    public MutableLiveData<Llibre> modificarLlibre(Llibre llibre, String token, Context context) {
        final MutableLiveData<Llibre> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable

        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, token, context);// Generamos el servicio

        biblioAppCliente.ModificaLibros(token, llibre.getIdLlibre(), llibre).enqueue(new Callback<Llibre>() {
            @Override
            public void onResponse(Call<Llibre> call, Response<Llibre> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Llibre llibre1= response.body();
                    mutableLiveData.setValue(llibre1);
                }
            }
            @Override
            public void onFailure(Call<Llibre> call, Throwable t) {
                Log.e(TAG, "Modificar libro incorrecto");
            }
        });
        return mutableLiveData;
    }

    /**
     *
     * @param llibre Libro a borrar
     * @param token
     * @return Mensaje retornado por API, satisfactorio o no
     */

    public MutableLiveData<String> borraLibro(Llibre llibre, String token, Context context) {
        final MutableLiveData<String> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable

        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, token, context);// Generamos el servicio

        biblioAppCliente.BorraLibro(token, llibre.getIdLlibre()).enqueue(new Callback<Registro>() {
            @Override
            public void onResponse(Call<Registro> call, Response<Registro> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Registro r= new Registro();
                    r=response.body();
                    String respuesta= r.getMissatge();
                    mutableLiveData.setValue(respuesta);
                }else{
                    String respuesta= response.errorBody().toString();
                    mutableLiveData.setValue(respuesta);
                }
            }
            @Override
            public void onFailure(Call<Registro> call, Throwable t) {
                Log.e(TAG, "Borrar llibre incorrecto");
            }
        });
        return mutableLiveData;
    }

    /**
     *
     * @param llibre Libro con el unico atributo de su Id
     * @param token
     * @return Libro encontrado o el titulo con el texto error
     */

    public MutableLiveData<Llibre> buscaLibroId(Llibre llibre, String token, Context context) {
        final MutableLiveData<Llibre> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable

        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, token, context);// Generamos el servicio

        biblioAppCliente.BuscaLibroId(token, llibre.getIdLlibre()).enqueue(new Callback<Llibre>() {
            @Override
            public void onResponse(Call<Llibre> call, Response<Llibre> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Llibre llibre= response.body();
                    mutableLiveData.setValue(llibre);
                }else{
                    Llibre llibre= new Llibre();
                    llibre.setTitulLlibre("Error");
                    mutableLiveData.setValue(llibre);
                }
            }

            @Override
            public void onFailure(Call<Llibre> call, Throwable t) {
                Log.e(TAG, "Buscar  llibre ID  incorrecto");
            }
        });
        return mutableLiveData;
    }

    /**
     *
     * @param llibre Libro con el unico atributo de su titulo
     * @param token
     * @return Libro encontrado o el titulo con el texto error
     */

    public MutableLiveData<Llibre> buscaLibroTitulo(Llibre llibre, String token, Context context) {
        final MutableLiveData<Llibre> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable

        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, token, context);// Generamos el servicio

        biblioAppCliente.BuscaLibroTitulo(token, llibre.getTitulLlibre()).enqueue(new Callback<Llibre>() {
            @Override
            public void onResponse(Call<Llibre> call, Response<Llibre> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Llibre llibre= response.body();
                    mutableLiveData.setValue(llibre);
                }else{
                    Llibre llibre= new Llibre();
                    llibre.setTitulLlibre("Error");
                    mutableLiveData.setValue(llibre);
                }
            }
            @Override
            public void onFailure(Call<Llibre> call, Throwable t) {
                Log.e(TAG, "Buscar  llibre Titol  incorrecto");
            }
        });
        return mutableLiveData;
    }

    /**
     *
     *
     * @return mutableLiveData con la información devuelta por el API
     * Si la conexión resulta correcta, conseguiremos un objeto Collection con los autores, en caso
     * contrario, nos devolvera null
     */
    public MutableLiveData<Collection<Autor>> pideAutores(String token, Context context) {
        final MutableLiveData<Collection<Autor>> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable


        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, token, context);// Generamos el servicio

        biblioAppCliente.ListadoAutores(token).enqueue(new Callback<Collection<Autor>>() {//procesamos en segundo plano el metodo login del servicio
            @Override
            public void onResponse(Call<Collection<Autor>> call, Response<Collection<Autor>> response) {//si el correcto conseguimos un usuario con la auth key
                if (response.isSuccessful() && response.body() != null) {
                    Collection autores;
                    autores = response.body();
                    mutableLiveData.setValue(autores);

                } else {
                    Log.e(TAG, "Error en BiblioAppREpo");
                    mutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<Collection<Autor>> call, Throwable t) {//si falla la conexión
                Log.e(TAG, "Error de conexion  en BiblioAppREpo");
            }
        });

        return mutableLiveData;
    }


    /**
     *
     * @param autor Autor a modificar, con todos los atributos
     * @param token
     * @return Libro modificado
     */

    public MutableLiveData<Autor> modificarAutor(Autor autor, String token, Context context) {
        final MutableLiveData<Autor> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable

        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, token,context);// Generamos el servicio

        biblioAppCliente.ModificaAutores(token, autor.getIdAutor(), autor).enqueue(new Callback<Autor>() {
            @Override
            public void onResponse(Call<Autor> call, Response<Autor> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Autor autor1= response.body();
                    mutableLiveData.setValue(autor1);
                }
            }
            @Override
            public void onFailure(Call<Autor> call, Throwable t) {
                Log.e(TAG, "Modificar autor incorrecto");
            }
        });
        return mutableLiveData;
    }

    /**
     *
     * @param autor Autor a borrar
     * @param token
     * @return Mensaje retornado por API, satisfactorio o no
     */

    public MutableLiveData<String> borraAutor(Autor autor, String token, Context context) {
        final MutableLiveData<String> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable

        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, token,context);// Generamos el servicio

        biblioAppCliente.BorraAutor(token, autor.getIdAutor()).enqueue(new Callback<Registro>() {
            @Override
            public void onResponse(Call<Registro> call, Response<Registro> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Registro r= new Registro();
                    r=response.body();
                    String respuesta= r.getMissatge();
                    mutableLiveData.setValue(respuesta);
                }else{
                    String respuesta= response.errorBody().toString();
                    mutableLiveData.setValue(respuesta);
                }
            }
            @Override
            public void onFailure(Call<Registro> call, Throwable t) {
                Log.e(TAG, "Borrar autor incorrecto");
            }
        });
        return mutableLiveData;
    }

    /**
     *
     * @param autor datos para realizar el registro
     * @return registro objeto con el mensaje de la API
     * Si el registro es correcto nos devuelve un mensaje diciendo que se ha registrado,
     * en caso contrario nos envia un mensaje de error
     */
    public MutableLiveData<Registro> añadeAutor(Autor autor, String token, Context context) {
        final MutableLiveData<Registro> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable


        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, token,context);// Generamos el servicio

        biblioAppCliente.NuevoAutor(autor).enqueue(new Callback<Registro>() {//procesamos en segundo plano el metodo Registro_Activity del servicio
            Registro registro = new Registro();

            @Override
            public void onResponse(Call<Registro> call, Response<Registro> response) { //si es correcto conseguimos una respuesta satisactoria, con informacion en body
                if (response.isSuccessful() && response.body() != null) {
                    //Log.d(TAG, response.body().getMessage());
                    registro = response.body();
                    mutableLiveData.setValue(registro);
                } else {//si la respuesta no es satisfactoria, conseguimos el error del errorBody y generamos un objeto Registro_Activity
                    try {
                        String json = response.errorBody().string();
                        Gson gson = new Gson();
                        registro = gson.fromJson(json, Registro.class);
                        mutableLiveData.setValue(registro);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "Error al crear autor");
                }
            }

            @Override
            public void onFailure(Call<Registro> call, Throwable t) {
                Log.e(TAG, "Añadir Autor incorrecto");
            }
        });
        return mutableLiveData;
    }




    /**
     *
     *
     * @return mutableLiveData con la información devuelta por el API
     * Si la conexión resulta correcta, conseguiremos un objeto Collection con los prestamos, en caso
     * contrario, nos devolvera null
     */
    public MutableLiveData<Collection<Prestec>> pidePrestecs(String token, Context context) {
        final MutableLiveData<Collection<Prestec>> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable


        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, token, context);// Generamos el servicio

        biblioAppCliente.ListadoPrestecs(token).enqueue(new Callback<Collection<Prestec>>() {//procesamos en segundo plano el metodo login del servicio
            @Override
            public void onResponse(Call<Collection<Prestec>> call, Response<Collection<Prestec>> response) {//si el correcto conseguimos un prestamo con la auth key
                if (response.isSuccessful() && response.body() != null) {
                    Collection prestecs;
                    prestecs = response.body();
                    mutableLiveData.setValue(prestecs);

                } else {
                    Log.e(TAG, "Error en BiblioAppREpo");
                    mutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<Collection<Prestec>> call, Throwable t) {//si falla la conexión
                Log.e(TAG, "Error de conexion  en BiblioAppREpo");
            }
        });

        return mutableLiveData;
    }

    /**
     *
     * @param prestec datos para realizar el registro
     * @return registro objeto con el mensaje de la API
     * Si el registro es correcto nos devuelve un mensaje diciendo que se ha registrado,
     * en caso contrario nos envia un mensaje de error
     */
    public MutableLiveData<Registro> añadePrestec(Prestec prestec, String token, Context context) {
        final MutableLiveData<Registro> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable


        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, token, context);// Generamos el servicio

        biblioAppCliente.NouPrestec(prestec).enqueue(new Callback<Registro>() {//procesamos en segundo plano el metodo Registro_Activity del servicio
            Registro registro = new Registro();

            @Override
            public void onResponse(Call<Registro> call, Response<Registro> response) { //si es correcto conseguimos una respuesta satisactoria, con informacion en body
                if (response.isSuccessful() && response.body() != null) {
                    //Log.d(TAG, response.body().getMessage());
                    registro = response.body();
                    mutableLiveData.setValue(registro);
                } else {//si la respuesta no es satisfactoria, conseguimos el error del errorBody y generamos un objeto Registro_Activity
                    try {
                        String json = response.errorBody().string();
                        Gson gson = new Gson();
                        registro = gson.fromJson(json, Registro.class);
                        mutableLiveData.setValue(registro);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "Error al crear prestamo");
                }
            }

            @Override
            public void onFailure(Call<Registro> call, Throwable t) {
                Log.e(TAG, "Añadir prestamo incorrecto");
            }
        });
        return mutableLiveData;
    }

    /**
     *
     * @param prestec Prestamo a modificar, con todos los atributos
     * @param token
     * @return Libro modificado
     */

    public MutableLiveData<Prestec> modificarPrestec(Prestec prestec, String token, Context context) {
        final MutableLiveData<Prestec> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable

        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, token, context);// Generamos el servicio

        biblioAppCliente.ModificaPrestec(token, prestec.getIdPrestec(), prestec).enqueue(new Callback<Prestec>() {
            @Override
            public void onResponse(Call<Prestec> call, Response<Prestec> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Prestec prestec1= response.body();
                    mutableLiveData.setValue(prestec1);
                }
            }
            @Override
            public void onFailure(Call<Prestec> call, Throwable t) {
                Log.e(TAG, "Modificar prestamo incorrecto");
            }
        });
        return mutableLiveData;
    }

    /**
     *
     * @param prestec Prestamo a borrar
     * @param token
     * @return Mensaje retornado por API, satisfactorio o no
     */

    public MutableLiveData<String> borraPrestec(Prestec prestec, String token, Context context) {
        final MutableLiveData<String> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable

        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, token, context);// Generamos el servicio

        biblioAppCliente.BorraPrestec(token, prestec.getIdPrestec()).enqueue(new Callback<Registro>() {
            @Override
            public void onResponse(Call<Registro> call, Response<Registro> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Registro r= new Registro();
                    r=response.body();
                    String respuesta= r.getMissatge();
                    mutableLiveData.setValue(respuesta);
                }else{
                    String respuesta= response.errorBody().toString();
                    mutableLiveData.setValue(respuesta);
                }
            }
            @Override
            public void onFailure(Call<Registro> call, Throwable t) {
                Log.e(TAG, "Borrar prestec incorrecto");
            }
        });
        return mutableLiveData;
    }

    /**
     *
     * @param prestec Prestamo con el unico atributo de su Id
     * @param token
     * @return Libro encontrado o el titulo con el texto error
     */

    public MutableLiveData<Prestec> buscaPrestecId(Prestec prestec, String token, Context context) {
        final MutableLiveData<Prestec> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable

        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, token, context);// Generamos el servicio

        biblioAppCliente.BuscaPrestecId(token, prestec.getIdPrestec()).enqueue(new Callback<Prestec>() {
            @Override
            public void onResponse(Call<Prestec> call, Response<Prestec> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Prestec prestec1= response.body();
                    mutableLiveData.setValue(prestec1);
                }else{
                    Prestec prestec= new Prestec();
                    prestec.setIdPrestec("Error");
                    mutableLiveData.setValue(prestec);
                }
            }

            @Override
            public void onFailure(Call<Prestec> call, Throwable t) {
                Log.e(TAG, "Buscar  prestec per  ID  incorrecto");
            }
        });
        return mutableLiveData;
    }

    /**
     *
     *
     * @return mutableLiveData con la información devuelta por el API
     * Si la conexión resulta correcta, conseguiremos un objeto Collection con los Categorias, en caso
     * contrario, nos devolvera null
     */
    public MutableLiveData<Collection<Categoria>> pideCategorias(String token, Context context) {
        final MutableLiveData<Collection<Categoria>> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable


        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, token, context);// Generamos el servicio

        biblioAppCliente.ListadoCategorias(token).enqueue(new Callback<Collection<Categoria>>() {//procesamos en segundo plano el metodo login del servicio
            @Override
            public void onResponse(Call<Collection<Categoria>> call, Response<Collection<Categoria>> response) {//si el correcto conseguimos un usuario con la auth key
                if (response.isSuccessful() && response.body() != null) {
                    Collection cat;
                    cat = response.body();
                    mutableLiveData.setValue(cat);

                } else {
                    Log.e(TAG, "Error en BiblioAppREpo");
                    mutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<Collection<Categoria>> call, Throwable t) {//si falla la conexión
                Log.e(TAG, "Error de conexion  en BiblioAppREpo");
            }
        });

        return mutableLiveData;
    }


    /**
     *
     * @param cat Categoria a modificar, con todos los atributos
     * @param token
     * @return Libro modificado
     */

    public MutableLiveData<Categoria> modificarCategoria(Categoria cat, String token, Context context) {
        final MutableLiveData<Categoria> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable

        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, token, context);// Generamos el servicio

        biblioAppCliente.ModificaCategorias(token, cat.getIdCategoria(), cat).enqueue(new Callback<Categoria>() {
            @Override
            public void onResponse(Call<Categoria> call, Response<Categoria> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Categoria cat= response.body();
                    mutableLiveData.setValue(cat);
                }
            }
            @Override
            public void onFailure(Call<Categoria> call, Throwable t) {
                Log.e(TAG, "Modificar Categoria incorrecto");
            }
        });
        return mutableLiveData;
    }

    /**
     *
     * @param categoria Categoria a borrar
     * @param token
     * @return Mensaje retornado por API, satisfactorio o no
     */

    public MutableLiveData<String> borraCategoria(Categoria categoria, String token, Context context) {
        final MutableLiveData<String> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable

        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, token, context);// Generamos el servicio

        biblioAppCliente.BorraCategoria(token, categoria.getIdCategoria()).enqueue(new Callback<Registro>() {
            @Override
            public void onResponse(Call<Registro> call, Response<Registro> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Registro r= new Registro();
                    r=response.body();
                    String respuesta= r.getMissatge();
                    mutableLiveData.setValue(respuesta);
                }else{
                    String respuesta= response.errorBody().toString();
                    mutableLiveData.setValue(respuesta);
                }
            }
            @Override
            public void onFailure(Call<Registro> call, Throwable t) {
                Log.e(TAG, "Borrar Categoria incorrecto");
            }
        });
        return mutableLiveData;
    }

    /**
     *
     * @param categoria datos para realizar el registro
     * @return registro objeto con el mensaje de la API
     * Si el registro es correcto nos devuelve un mensaje diciendo que se ha registrado,
     * en caso contrario nos envia un mensaje de error
     */
    public MutableLiveData<Registro> añadeCategoria(Categoria categoria, String token, Context context) {
        final MutableLiveData<Registro> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable


        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, token, context);// Generamos el servicio

        biblioAppCliente.NuevaCategoria(categoria).enqueue(new Callback<Registro>() {//procesamos en segundo plano el metodo Registro_Activity del servicio
            Registro registro = new Registro();

            @Override
            public void onResponse(Call<Registro> call, Response<Registro> response) { //si es correcto conseguimos una respuesta satisactoria, con informacion en body
                if (response.isSuccessful() && response.body() != null) {
                    registro = response.body();
                    mutableLiveData.setValue(registro);
                } else {//si la respuesta no es satisfactoria, conseguimos el error del errorBody y generamos un objeto Registro_Activity
                    try {
                        String json = response.errorBody().string();
                        Gson gson = new Gson();
                        registro = gson.fromJson(json, Registro.class);
                        mutableLiveData.setValue(registro);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "Error al crear Categoria");
                }
            }

            @Override
            public void onFailure(Call<Registro> call, Throwable t) {
                Log.e(TAG, "Añadir Categoria incorrecto");
            }
        });
        return mutableLiveData;
    }


    /**
     *
     * @param usuari Libro con el unico atributo de su Id
     * @param token
     * @return Libro encontrado o el titulo con el texto error
     */

    public MutableLiveData<Usuari> buscaUsuarioId(Usuari usuari, String token, Context context) {
        final MutableLiveData<Usuari> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable

        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, token,context);// Generamos el servicio

        biblioAppCliente.BuscaUsuarioId(token, usuari.getIdUsuari()).enqueue(new Callback<Usuari>() {
            @Override
            public void onResponse(Call<Usuari> call, Response<Usuari> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Usuari usuari1= response.body();
                    mutableLiveData.setValue(usuari1);
                }else{
                    Usuari usuari1= new Usuari();
                    usuari1.setIdUsuari("Error");
                    mutableLiveData.setValue(usuari1);
                }
            }

            @Override
            public void onFailure(Call<Usuari> call, Throwable t) {
                Log.e(TAG, "Buscar  usuario ID  incorrecto");
            }
        });
        return mutableLiveData;
    }

}

