/**
 * @Autor Saúl López Díez
 * Clase Pantalla_Inicio_Administrador con la Activity de incio del Administrador, que acoge los diferentes fragments
 */
package ioc.android.biblioapp.View.Administrador;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import ioc.android.biblioapp.R;
import ioc.android.biblioapp.View.Login_Activity;
import ioc.android.biblioapp.ViewModel.Administrador.PantallaInicioAdministradorViewModel;
import ioc.android.biblioapp.databinding.ActivityPantallaInicioAdministradorBinding;

public class Pantalla_Inicio_Administrador extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityPantallaInicioAdministradorBinding binding;
    private PantallaInicioAdministradorViewModel pantallaInicioAdministradorViewModel;
    public String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPantallaInicioAdministradorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarAdministradorDrawer.toolbar);
        binding.appBarAdministradorDrawer.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Pantalla_Inicio_Administrador.this, Login_Activity.class);
                getWindow().setExitTransition(new Explode());
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(Pantalla_Inicio_Administrador.this).toBundle());
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_Admin_home, R.id.nav_Admin_usuarios, R.id.nav_Admin_libros)
                .setOpenableLayout(drawer)
                .build();


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_administrador_drawer);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        pantallaInicioAdministradorViewModel= new ViewModelProvider(this).get(PantallaInicioAdministradorViewModel.class);
        token= getIntent().getExtras().getString("token");
        pantallaInicioAdministradorViewModel.setToken(token);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.p_administrador_drawer, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_administrador_drawer);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void nuevoUsuario(View view) {

    }

    public void nuevoLibro(View view) {
    }
}