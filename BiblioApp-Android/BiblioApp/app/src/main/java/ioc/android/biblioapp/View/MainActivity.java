/**
 * @Autor Saúl López Díez
 * Clase MainActivity con la Actividad de Login
 */

package ioc.android.biblioapp.View;

import static ioc.android.biblioapp.ViewModel.Utilidades.Utilidades.conectividad;
import static ioc.android.biblioapp.ViewModel.Utilidades.Utilidades.esconderTeclado;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import ioc.android.biblioapp.Model.Clases.Login;
import ioc.android.biblioapp.Model.Clases.Usuari;
import ioc.android.biblioapp.R;
import ioc.android.biblioapp.ViewModel.ViewModels.LoginViewModel;

public class MainActivity extends AppCompatActivity {

    private TextView mUsuario, mContraseña, mLogin_incorrecto;
    private Login login;
    private Usuari usuari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //incializamos variables
        mUsuario = findViewById(R.id.user_edit_text);
        mContraseña = findViewById(R.id.password_edit_text);
        mLogin_incorrecto = findViewById(R.id.login_incorrecto);


    }

    /**
     * @param view Metodo para gestion el click del boton Login, en la que comprobamos si es correcto y lanzamos
     *             la activad que corresponda en funcion del rol del usuario
     */
    public void Login(View view) {

        esconderTeclado(this, view);

        if (conectividad(this) && mUsuario.length() != 0 && mContraseña.length() != 0) {//Si la hay conectividad y los campos no estan vacios

            LoginViewModel loginViewModel = new LoginViewModel();//generamos el viewModel de Login
            login = new Login(mUsuario.getText().toString(), mContraseña.getText().toString());//Instanciamos un login con los datos de los campos

            loginViewModel.getLogin(login).observe(this, new Observer<Usuari>() {//Generamos un observer a la espera de la consulta
                @Override
                public void onChanged(Usuari usuari2) {//con la respuesta
                    if (usuari2 != null) {//si no es null
                        usuari = usuari2;
                        String[] rol = usuari.getRoles();//conseguimos el rol y lanzamos la actividad que corresponda
                        if (rol[0].equalsIgnoreCase("ROLE_ADMIN")) {
                            Intent intent = new Intent(MainActivity.this, Pantalla_Inicio_Administrador.class);
                            getWindow().setExitTransition(new Explode());
                            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
                        } else {
                            Intent intent = new Intent(MainActivity.this, Pantalla_Inicio_Usuario.class);
                            getWindow().setExitTransition(new Explode());
                            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
                        }
                    } else {
                        Log.e("Main", "Error en credenciales");
                        mLogin_incorrecto.setVisibility(View.VISIBLE);
                    }
                }
            });
        } else {
            if (mUsuario.length() == 0) {//caso no haya datos en usuario
                Toast.makeText(getApplicationContext(), "Introduzca usuario", Toast.LENGTH_SHORT).show();
            }
            if (mContraseña.length() == 0) {//caso no haya datos en constraseña
                Toast.makeText(getApplicationContext(), "Introduzca contraseña", Toast.LENGTH_SHORT).show();
            } else {//caso no haya conectividad
                Toast.makeText(getApplicationContext(), "Sin conexion a internet", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * @param view Gestion del click en el boton registro, que nos lleva a la actividad para crear un usuario
     */
    public void IrRegistro(View view) {
        esconderTeclado(this, view);
        Intent intent = new Intent(this, Registro.class);
        getWindow().setExitTransition(new Explode());
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

    }

    /**
     * @param view Metodo que gestiona el click en el boton Cancela que deja los campos en blancos y quita
     *             mensaje de error
     */
    public void Borra(View view) {
        mUsuario.setText("");
        mContraseña.setText("");
        mLogin_incorrecto.setVisibility(View.INVISIBLE);
    }


}