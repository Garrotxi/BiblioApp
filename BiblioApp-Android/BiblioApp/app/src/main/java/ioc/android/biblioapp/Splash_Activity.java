package ioc.android.biblioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Splash_Activity extends AppCompatActivity {

    private static final int TIEMPO_CARGA_SPLASH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_Splash);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Intent intent = new Intent(this, MainActivity.class);
        // Dormimos el thread el tiempo definido para que se vea la pantalla de carga
        try {
            Thread.sleep(TIEMPO_CARGA_SPLASH);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startActivity(intent);
        finish();

    }


}