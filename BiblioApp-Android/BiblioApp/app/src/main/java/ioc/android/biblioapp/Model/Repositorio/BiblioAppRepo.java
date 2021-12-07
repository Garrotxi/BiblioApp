/**
 * @Autor Saúl López Díez
 * Clase BiblioAppRepo con el Repositorio de metodos para conseguir la información y enviarsela a la capa ViewModel
 */
package ioc.android.biblioapp.Model.Repositorio;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Collection;

import ioc.android.biblioapp.Model.Clases.Llibre;
import ioc.android.biblioapp.Model.Clases.Login;
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
    public MutableLiveData<Usuari> pideLogin(Login login) {
        final MutableLiveData<Usuari> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable


        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, "login");// Generamos el servicio

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
    public MutableLiveData<Registro> pideRegistro(Usuari usuari) {
        final MutableLiveData<Registro> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable


        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, "registro");// Generamos el servicio

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
    public MutableLiveData<Collection<Usuari>> pideUsuarios(String token) {
        final MutableLiveData<Collection<Usuari>> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable


        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, token);// Generamos el servicio

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
    public MutableLiveData<Collection<Llibre>> pideLibros(String token) {
        final MutableLiveData<Collection<Llibre>> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable


        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, token);// Generamos el servicio

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
    public MutableLiveData<Registro> añadeLibro(Llibre libro, String token) {
        final MutableLiveData<Registro> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable


        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, token);// Generamos el servicio

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

    public MutableLiveData<Usuari> modificarUsuario(Usuari usuari, String token) {
        final MutableLiveData<Usuari> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable


        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, token);// Generamos el servicio

        biblioAppCliente.ModificaUsuarios(token, usuari.getIdUsuari(), usuari).enqueue(new Callback<Usuari>() {
            @Override
            public void onResponse(Call<Usuari> call, Response<Usuari> response) {
                if (response.isSuccessful() && response.body() != null) {
                    //String respuesta= response.body();
                    Usuari usuari1= response.body();
                    mutableLiveData.setValue(usuari1);
                }else{
                   // String respuesta= response.errorBody().toString();
                    //mutableLiveData.setValue(respuesta);

                }
            }

            @Override
            public void onFailure(Call<Usuari> call, Throwable t) {
                Log.e(TAG, "Modificar usuario incorrecto");

            }

        });
        return mutableLiveData;
    }

    public MutableLiveData<String> borraUsuario(Usuari usuari, String token) {
        final MutableLiveData<String> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable


        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, token);// Generamos el servicio

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

    public MutableLiveData<Llibre> modificarLlibre(Llibre llibre, String token) {
        final MutableLiveData<Llibre> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable


        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, token);// Generamos el servicio

        biblioAppCliente.ModificaLibros(token, llibre.getIdLlibre(), llibre).enqueue(new Callback<Llibre>() {
            @Override
            public void onResponse(Call<Llibre> call, Response<Llibre> response) {
                if (response.isSuccessful() && response.body() != null) {
                    //String respuesta= response.body();
                    Llibre llibre1= response.body();
                    mutableLiveData.setValue(llibre1);
                }else{
                    // String respuesta= response.errorBody().toString();
                    //mutableLiveData.setValue(respuesta);

                }
            }

            @Override
            public void onFailure(Call<Llibre> call, Throwable t) {
                Log.e(TAG, "Modificar libro incorrecto");

            }

        });
        return mutableLiveData;
    }

    public MutableLiveData<String> borraLibro(Llibre llibre, String token) {
        final MutableLiveData<String> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable


        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, token);// Generamos el servicio

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

    public MutableLiveData<Llibre> buscaLibroId(Llibre llibre, String token) {
        final MutableLiveData<Llibre> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable


        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, token);// Generamos el servicio

        biblioAppCliente.BuscaLibroId(token, llibre.getIdLlibre()).enqueue(new Callback<Llibre>() {
            @Override
            public void onResponse(Call<Llibre> call, Response<Llibre> response) {
                if (response.isSuccessful() && response.body() != null) {
                    /*Registro r= new Registro();
                    r=response.body();
                    String respuesta= r.getMissatge();*/
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

    public MutableLiveData<Llibre> buscaLibroTitulo(Llibre llibre, String token) {
        final MutableLiveData<Llibre> mutableLiveData = new MutableLiveData<>();//para capturar informacion variable


        BiblioAppCliente biblioAppCliente =
                ServiceGenerator.createService(BiblioAppCliente.class, token);// Generamos el servicio

        biblioAppCliente.BuscaLibroTitulo(token, llibre.getTitulLlibre()).enqueue(new Callback<Llibre>() {
            @Override
            public void onResponse(Call<Llibre> call, Response<Llibre> response) {
                if (response.isSuccessful() && response.body() != null) {
                    /*Registro r= new Registro();
                    r=response.body();
                    String respuesta= r.getMissatge();*/
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


}

