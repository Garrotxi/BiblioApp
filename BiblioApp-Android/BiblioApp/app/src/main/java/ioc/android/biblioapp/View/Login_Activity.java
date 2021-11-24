/**
 * @Autor Saúl López Díez
 * Clase Login_Activity con la Actividad de Login_Activity
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

import java.util.List;

import ioc.android.biblioapp.Model.Clases.Login;
import ioc.android.biblioapp.Model.Clases.Usuari;
import ioc.android.biblioapp.R;
import ioc.android.biblioapp.View.Administrador.Pantalla_Inicio_Administrador;
import ioc.android.biblioapp.View.Usuario.Pantalla_Inicio_Usuario;
import ioc.android.biblioapp.ViewModel.LoginViewModel;

public class Login_Activity extends AppCompatActivity {

    private TextView mUsuario, mContraseña, mLogin_incorrecto;
    private Login login;
    private Usuari usuari;
    private LoginViewModel loginViewModel = new LoginViewModel();//Generamos el loginviewModel
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //incializamos variables
        mUsuario = findViewById(R.id.user_edit_text);
        mContraseña = findViewById(R.id.password_edit_text);
        mLogin_incorrecto = findViewById(R.id.login_incorrecto);

    }

    /**
     * @param view Metodo para gestion el click del boton Login_Activity, en la que comprobamos si es correcto y lanzamos
     *             la activad que corresponda en funcion del rol del usuario
     */
    public void Login(View view) {

        esconderTeclado(this, view);

        if (conectividad(this) && mUsuario.length() != 0 && mContraseña.length() != 0) {//Si la hay conectividad y los campos no estan vacios
            if (login==null){
                login = new Login(mUsuario.getText().toString(), mContraseña.getText().toString());//Instanciamos un login con los datos de los campos
            }else{
                login.setNomUsuari(mUsuario.getText().toString());
                login.setContrasenya(mContraseña.getText().toString());
            }


            loginViewModel.loginViewModel(login, this, loginViewModel).observe(this, new Observer<List<String>>() {
                @Override
                public void onChanged(List<String> strings) {
                    if (strings != null) {
                        loginViewModel.setLoginViewModel(loginViewModel);
                        if (strings.get(0).equalsIgnoreCase("{authority=ROLE_ADMIN}") || (strings.get(1).equalsIgnoreCase("{authority=ROLE_ADMIN}"))) {//conseguimos el rol y lanzamos la actividad que corresponda
                            Intent intent = new Intent(Login_Activity.this, Pantalla_Inicio_Administrador.class);
                            if(strings.get(2)==null){
                                intent.putExtra("token",strings.get(1));
                            }else{
                                intent.putExtra("token",strings.get(2));
                            }


                            getWindow().setExitTransition(new Explode());
                            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(Login_Activity.this).toBundle());
                        } else {
                            Intent intent = new Intent(Login_Activity.this, Pantalla_Inicio_Usuario.class);
                            intent.putExtra("token",strings.get(1));
                            getWindow().setExitTransition(new Explode());
                            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(Login_Activity.this).toBundle());
                        }
                    } else {
                        Log.e("Login", "Error en credenciales");
                        Toast.makeText(getApplicationContext(),"Error en credenciales",Toast.LENGTH_LONG).show();
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
        Intent intent = new Intent(this, Registro_Activity.class);
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