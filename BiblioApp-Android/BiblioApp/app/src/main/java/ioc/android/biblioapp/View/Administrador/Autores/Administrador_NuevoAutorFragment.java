/**
 * @Autor Saúl López Díez
 * Clase Administrador_NuevoLibroFragment con el fragment para crear un nuevo libro del Administrador
 */

package ioc.android.biblioapp.View.Administrador.Autores;

import static ioc.android.biblioapp.ViewModel.Utilidades.Utilidades.conectividad;
import static ioc.android.biblioapp.ViewModel.Utilidades.Utilidades.esconderTeclado;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import ioc.android.biblioapp.Model.Clases.Autor;
import ioc.android.biblioapp.ViewModel.Administrador.Autores.Administrador_NuevoAutorViewModel;
import ioc.android.biblioapp.databinding.FragmentAdministradorNuevoAutorBinding;


public class Administrador_NuevoAutorFragment extends Fragment {


    private FragmentAdministradorNuevoAutorBinding binding;
    private TextView mNom, mMensajeResultado;
    private Button mAñadir;
    private String token;
    private Autor autor = new Autor();
    private Administrador_NuevoAutorViewModel administrador_nuevoAutorViewModel = new Administrador_NuevoAutorViewModel();

    public Administrador_NuevoAutorFragment() { }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAdministradorNuevoAutorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mNom = binding.editTextNom;
        mMensajeResultado = binding.textViewMensaje;
        mAñadir=binding.botonRegistro;

        mAñadir.setOnClickListener(new View.OnClickListener() {//Añadir nuevo autor
            @Override
            public void onClick(View view) {
                autor.setNom(mNom.getText().toString());

                esconderTeclado(getContext(), view);
                Bundle b = getActivity().getIntent().getExtras();
                token = b.getString("token"); //conseguimos el token
                if (conectividad(getContext()) && mNom.length() != 0  ) {
                    if (administrador_nuevoAutorViewModel!=null){
                        administrador_nuevoAutorViewModel=new Administrador_NuevoAutorViewModel();
                    }
                    administrador_nuevoAutorViewModel.nuevoAutor(autor, getContext(), administrador_nuevoAutorViewModel,token).observe(getViewLifecycleOwner(), new Observer<String>() {//Gestionamos en viewModel
                        @Override
                        public void onChanged(String s) {
                            if (s != null) {
                                esconderTeclado(getContext(), view);
                                mMensajeResultado.setVisibility(View.VISIBLE);
                                mMensajeResultado.setText(s);
                                Log.d("Registro", s);
                            } else {
                                mMensajeResultado.setVisibility(View.VISIBLE);
                                mMensajeResultado.setText(s);
                                Log.d("Registro", "Error");
                            }
                        }
                    });
                } else {
                    if (mNom.length() == 0) {//caso no haya datos en usuario
                        Toast.makeText(getContext(), "Falten dades: nom", Toast.LENGTH_SHORT).show();
                        Log.d("View", "Titulo vacio");
                        mNom.requestFocus();

                    }

                    if (conectividad(getContext()) == false) {//caso no haya conectividad
                        Toast.makeText(getContext(), "Sense conexio a internet", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return root;
    }
}