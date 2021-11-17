/**
 * @Autor Saúl López Díez
 * Clase Registro con la Actividad de registro de usuarios
 */

package ioc.android.biblioapp.View;

import static ioc.android.biblioapp.ViewModel.Utilidades.Utilidades.conectividad;
import static ioc.android.biblioapp.ViewModel.Utilidades.Utilidades.esconderTeclado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import ioc.android.biblioapp.Model.Clases.Usuari;
import ioc.android.biblioapp.R;
import ioc.android.biblioapp.ViewModel.ViewModels.RegistroViewModel;

public class Registro extends AppCompatActivity {
    Usuari usuari = new Usuari();
    private TextView mNomUsuari, mNom, mCognoms, mEmail, mTelefon, mContraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        mNomUsuari = findViewById(R.id.editText_NomUsuari);
        mNom = findViewById(R.id.editText_Nom);
        mCognoms = findViewById(R.id.editText_Cognoms);
        mEmail = findViewById(R.id.editText_Email);
        mTelefon = findViewById(R.id.editText_Telefon);
        mContraseña = findViewById(R.id.editText_Contraseña);
    }

    /**
     * @param view Metodo que gestiona el clik en el boton Registro, que gestiona la creacion de un Usuari
     *             con los datos introducidos
     */
    public void Registro(View view) {

        usuari.setusername(mNomUsuari.getText().toString());
        usuari.setNom(mNom.getText().toString());
        usuari.setCognoms(mCognoms.getText().toString());
        usuari.setEmail(mEmail.getText().toString());
        usuari.setTelefon(mTelefon.getText().toString());
        usuari.setpassword(mContraseña.getText().toString());

        esconderTeclado(this, view);
        if (conectividad(this) && mNomUsuari.length() != 0 && mNom.length() != 0 && mCognoms.length() != 0 && mEmail.length() != 0 && mTelefon.length() != 0 && mContraseña.length() != 0) {
            RegistroViewModel registroViewModel = new RegistroViewModel();
            registroViewModel.registro(usuari).observe(this, new Observer<ioc.android.biblioapp.Model.Clases.Registro>() {
                @Override
                public void onChanged(ioc.android.biblioapp.Model.Clases.Registro mensaje) {
                    if (mensaje != null) {
                        Log.e("Registro", mensaje.getMessage());
                        Toast.makeText(getApplicationContext(), mensaje.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Log.e("Registro", "Error en registro");
                    }
                }
            });
        } else {
            if (mContraseña.length() == 0) {//caso no haya datos en usuario
                Toast.makeText(getApplicationContext(), "Falten dades: Contrasenya", Toast.LENGTH_SHORT).show();
                mContraseña.requestFocus();
            }
            if (mTelefon.length() == 0) {//caso no haya datos en usuario
                Toast.makeText(getApplicationContext(), "Falten dades: Telefon", Toast.LENGTH_SHORT).show();
                mTelefon.requestFocus();
            }
            if (mEmail.length() == 0) {//caso no haya datos en usuario
                Toast.makeText(getApplicationContext(), "Falten dades: Email", Toast.LENGTH_SHORT).show();
                mEmail.requestFocus();
            }
            if (mCognoms.length() == 0) {//caso no haya datos en usuario
                Toast.makeText(getApplicationContext(), "Falten dades: Cognoms", Toast.LENGTH_SHORT).show();
                mCognoms.requestFocus();
            }
            if (mNom.length() == 0) {//caso no haya datos en constraseña
                Toast.makeText(getApplicationContext(), "Falten dades: nom ", Toast.LENGTH_SHORT).show();
                mNom.requestFocus();
            }
            if (mNomUsuari.length() == 0) {//caso no haya datos en nombre de usuario
                Toast.makeText(getApplicationContext(), "Falten dades: nom d'usuari", Toast.LENGTH_SHORT).show();
                mNomUsuari.requestFocus();
            }
            if (conectividad(this) == false) {//caso no haya conectividad
                Toast.makeText(getApplicationContext(), "Sense conexio a internet", Toast.LENGTH_SHORT).show();
            }
        }

    }
}