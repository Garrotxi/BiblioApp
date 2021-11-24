/**
 * @Autor Saúl López Díez
 * Clase AuthenticationInterceptor con el interceptor para añadir la accessToken al servicio
 */
package ioc.android.biblioapp.Model.Servicio;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Clase AuthenticationInterceptor
 */
public class AuthenticationInterceptor implements Interceptor {
    private String authToken;

    public AuthenticationInterceptor(String token) {
        this.authToken = token;
    }

    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder()
                .header("Authorization", "Bearer "+authToken);

        Request request = builder.build();
        return chain.proceed(request);
    }
}
