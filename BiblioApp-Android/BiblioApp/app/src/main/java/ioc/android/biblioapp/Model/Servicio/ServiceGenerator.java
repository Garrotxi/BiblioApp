package ioc.android.biblioapp.Model.Servicio;

import android.text.TextUtils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static final String BASE_URL = "http://172.27.0.1:9090";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    private static HttpLoggingInterceptor logging =
            new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);


    public static <S> S createService(Class<S> BiblioAppCliente) {
        return createService(BiblioAppCliente, null);
    }

    public static <S> S createService(
            Class<S> BiblioAppCliente, final String authToken) {

        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor(authToken);

            if (!httpClient.interceptors().contains(logging)) {
                httpClient.addInterceptor(logging);

                builder.client(httpClient.build());
                retrofit = builder.build();
            }
        }
        return retrofit.create(BiblioAppCliente);
    }
}
