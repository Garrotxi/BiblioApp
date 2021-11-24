/**
 * @Autor Saúl López Díez
 * Clase Administrador_HomeFragment con el fragment de incio del Administrador
 */

package ioc.android.biblioapp.View.Administrador;

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

import ioc.android.biblioapp.ViewModel.Administrador.Administrador_HomeViewModel;
import ioc.android.biblioapp.databinding.FragmentAdministradorHomeBinding;

public class Administrador_HomeFragment extends Fragment {

    private Administrador_HomeViewModel homeViewModel;
    private FragmentAdministradorHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(Administrador_HomeViewModel.class);

        binding = FragmentAdministradorHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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