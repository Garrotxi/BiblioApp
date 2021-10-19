package ioc.android.biblioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView usuario, contraseña;
    protected String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario= findViewById(R.id.user_edit_text);
        contraseña=findViewById(R.id.password_edit_text);
        user= usuario.getText().toString();

    }

    public void Login(View view) {
        user= usuario.getText().toString();
        usuario.setText(user);

        if(usuario.getText().toString().equalsIgnoreCase("Admin")){
            Toast.makeText(getApplicationContext(), "Bienvenido Admin", Toast.LENGTH_SHORT).show();
        }if(usuario.getText().toString().equalsIgnoreCase("Usuario")) {
            Toast.makeText(getApplicationContext(), "Bienvenido Usuario", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "¿Tú quien eres?", Toast.LENGTH_SHORT).show();
        }
    }
}