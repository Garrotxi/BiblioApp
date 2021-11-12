package ioc.android.biblioapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;

import ioc.android.biblioapp.R;

public class Pantalla_Inicio_Administrador extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicio_administrador);
    }

    public void Logout(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        getWindow().setExitTransition(new Explode());
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}