/**
 * @Autor Saúl López Díez
 * Clase Splash_Activity que genera una portada en la aplicacion
 */
package ioc.android.biblioapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import ioc.android.biblioapp.R;

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