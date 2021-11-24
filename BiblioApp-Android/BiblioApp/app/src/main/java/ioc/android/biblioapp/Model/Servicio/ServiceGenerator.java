/**
 * @Autor Saúl López Díez
 * Clase ServiceGenerator con la construccion del cliente. Se utiliza Retrofit, para realizar conectividad HTTP, con conversor GSON,
 * interceptor logging para ver los mensajes en la comunicacion HTTP
 */

package ioc.android.biblioapp.Model.Servicio;

import android.text.TextUtils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Clase ServiceGenerator
 */
public class ServiceGenerator {


    private static final String BASE_URL = "http://10.0.2.2:9090";//direccion IP y puerto del ordenador donde esta API escuchando

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();// cliente http

    private static Retrofit.Builder builder =//paramentros del retrofit
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();//construccion del retrofit

    private static HttpLoggingInterceptor logging =//implementacion del interceptor en las comunicaciones http
            new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);

    /**
     * @param BiblioAppCliente interfaz con los endpoint del API
     * @param <S>              clase generica
     * @return createService Servicio BiblioAppCliente sin token autentificacion
     */
    public static <S> S createService(Class<S> BiblioAppCliente) {

        return createService(BiblioAppCliente, null);
    }

    /**
     * @param BiblioAppCliente interfaz con los endpoint del API
     * @param authToken        token autentificacion
     * @param <S>              clase generica
     * @return retrofit con interceptor para añadir token
     */
    public static <S> S createService(
            Class<S> BiblioAppCliente, final String authToken) {

        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor(authToken);

            if (!httpClient.interceptors().contains(logging)) {
                httpClient.addInterceptor(logging);
            }

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);

                builder.client(httpClient.build());
                retrofit = builder.build();
            }
        }
        return retrofit.create(BiblioAppCliente);
    }


}
