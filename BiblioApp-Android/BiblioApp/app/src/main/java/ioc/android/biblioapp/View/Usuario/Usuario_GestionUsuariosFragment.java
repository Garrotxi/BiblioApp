/**
 * @Autor Saúl López Díez
 * Clase Usuario_GestionUsuariosFragment con el fragment para gestion del perfil Usuario
 */

package ioc.android.biblioapp.View.Usuario;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import ioc.android.biblioapp.ViewModel.Usuario.Usuario_GestionUsuariosViewModel;
import ioc.android.biblioapp.databinding.FragmentAdministradorUsuariosBinding;

public class Usuario_GestionUsuariosFragment extends Fragment {

    private Usuario_GestionUsuariosViewModel gestionUsuariosViewModel;
    private FragmentAdministradorUsuariosBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        gestionUsuariosViewModel =
                new ViewModelProvider(this).get(Usuario_GestionUsuariosViewModel.class);
        binding = FragmentAdministradorUsuariosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView textView = binding.textGallery;
        gestionUsuariosViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}