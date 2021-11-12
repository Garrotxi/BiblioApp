package ioc.android.biblioapp.Model.Servicio;

import ioc.android.biblioapp.Model.Clases.Login;
import ioc.android.biblioapp.Model.Clases.Usuari;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BiblioAppCliente {

    @POST("/api/auth/login")
    Call<Usuari> Login(
            @Body Login login
    );


}
