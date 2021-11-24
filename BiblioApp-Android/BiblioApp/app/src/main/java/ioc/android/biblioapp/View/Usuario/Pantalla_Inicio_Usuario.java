/**
 * @Autor Saúl López Díez
 * Clase Pantalla_Inicio_Usuario con la Activity de incio del Usuario, que acoge los diferentes fragments
 */
package ioc.android.biblioapp.View.Usuario;

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
import ioc.android.biblioapp.ViewModel.Usuario.PantallaInicioUsuarioViewModel;
import ioc.android.biblioapp.databinding.ActivityPantallaInicioUsuarioBinding;

public class Pantalla_Inicio_Usuario extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityPantallaInicioUsuarioBinding binding;
    private PantallaInicioUsuarioViewModel pantallaInicioUsuarioViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPantallaInicioUsuarioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarUsuarioDrawer.toolbarUsuario);
        binding.appBarUsuarioDrawer.fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Pantalla_Inicio_Usuario.this, Login_Activity.class);
                getWindow().setExitTransition(new Explode());
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(Pantalla_Inicio_Usuario.this).toBundle());
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView2;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_User_home, R.id.nav_User_usuarios, R.id.nav_User_libros)
                .setOpenableLayout(drawer)
                .build();


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_usuario_drawer);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        pantallaInicioUsuarioViewModel= new ViewModelProvider(this).get(PantallaInicioUsuarioViewModel.class);
        String token= getIntent().getExtras().getString("token");
        pantallaInicioUsuarioViewModel.setToken(token);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.p_usuario_drawer, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_usuario_drawer);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}