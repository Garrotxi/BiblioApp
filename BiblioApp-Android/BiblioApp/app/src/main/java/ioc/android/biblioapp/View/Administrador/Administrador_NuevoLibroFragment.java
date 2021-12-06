package ioc.android.biblioapp.View.Administrador;

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

import ioc.android.biblioapp.Model.Clases.Llibre;
import ioc.android.biblioapp.ViewModel.Administrador.Administrador_NuevoLibroViewModel;
import ioc.android.biblioapp.databinding.FragmentAdministradorNuevoLibroBinding;


public class Administrador_NuevoLibroFragment extends Fragment {


    private FragmentAdministradorNuevoLibroBinding binding;
    private TextView mTitulo, mFecha, mCopias, mMensajeResultado;
    private Button mAñadir;
    private String token;
    private Llibre libro = new Llibre();
    private Administrador_NuevoLibroViewModel administrador_nuevoLibroViewModel = new Administrador_NuevoLibroViewModel();

    public Administrador_NuevoLibroFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAdministradorNuevoLibroBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        mTitulo = binding.editTextTitulo;
        mFecha = binding.editTextFecha;
        mCopias = binding.editTextCopias;
        mMensajeResultado = binding.textViewMensaje;
        mAñadir=binding.botonRegistro;

        mAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                libro.setTitulLlibre(mTitulo.getText().toString());
                libro.setDataPublicacio(mFecha.getText().toString());
                libro.setCopiesDisponibles(mCopias.getText().toString());

                esconderTeclado(getContext(), view);
                Bundle b = getActivity().getIntent().getExtras();
                token = b.getString("token"); //conseguimos el token
                if (conectividad(getContext()) && mTitulo.length() != 0 && mCopias.length() != 0 && mFecha.length() != 0 ) {
                    administrador_nuevoLibroViewModel.nuevoLibro(libro, getContext(), administrador_nuevoLibroViewModel,token).observe(getViewLifecycleOwner(), new Observer<String>() {//Gestionamos en viewModel
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
                    if (mTitulo.length() == 0) {//caso no haya datos en usuario
                        Toast.makeText(getContext(), "Falten dades: Contrasenya", Toast.LENGTH_SHORT).show();
                        Log.d("View", "Titulo vacio");
                        mTitulo.requestFocus();
                    }
                    if (mFecha.length() == 0) {//caso no haya datos en usuario
                        Toast.makeText(getContext(), "Falten dades: Telefon", Toast.LENGTH_SHORT).show();
                        mFecha.requestFocus();
                    }
                    if (mCopias.length() == 0) {//caso no haya datos en usuario
                        Toast.makeText(getContext(), "Falten dades: Email", Toast.LENGTH_SHORT).show();
                        mCopias.requestFocus();
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