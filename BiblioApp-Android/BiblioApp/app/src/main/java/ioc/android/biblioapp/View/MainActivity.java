package ioc.android.biblioapp.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import ioc.android.biblioapp.Model.Clases.Login;
import ioc.android.biblioapp.Model.Clases.Usuari;
import ioc.android.biblioapp.R;
import ioc.android.biblioapp.ViewModel.ViewModels.LoginViewModel;

public class MainActivity extends AppCompatActivity {

    private TextView usuario, contraseña,login_incorrecto;
    private Login login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //incializamos variables
        usuario = findViewById(R.id.user_edit_text);
        contraseña = findViewById(R.id.password_edit_text);
        login_incorrecto = findViewById(R.id.login_incorrecto);


    }

    public void Login(View view) {

        LoginViewModel loginViewModel = new LoginViewModel();
        login = new Login(usuario.getText().toString(), contraseña.getText().toString());

        loginViewModel.getLogin(login).observe(this, new Observer<Usuari>() {
            @Override
            public void onChanged(Usuari usuari) {
                if (usuari != null) {
                    Log.e("Main", usuari.getAccessToken());
                    Usuari usuari2 = new Usuari(loginViewModel.getLogin(login).getValue());

                }else{
                    Log.e("Main", "Error en credenciales");
                }

            }
        });


    }
}