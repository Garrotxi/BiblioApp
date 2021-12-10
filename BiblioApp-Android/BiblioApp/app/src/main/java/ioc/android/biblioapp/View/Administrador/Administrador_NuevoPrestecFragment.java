/**
 * @Autor Saúl López Díez
 * Clase Administrador_NuevoLibroFragment con el fragment para crear un nuevo prestamo del Administrador
 */

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

import ioc.android.biblioapp.Model.Clases.Prestec;
import ioc.android.biblioapp.ViewModel.Administrador.Administrador_NuevoPrestecViewModel;
import ioc.android.biblioapp.databinding.FragmentAdministradorNuevoPrestecBinding;


public class Administrador_NuevoPrestecFragment extends Fragment {


    private FragmentAdministradorNuevoPrestecBinding binding;
    private TextView mIdUsuario, mIdLibro, mDataPrestec,mDataDevolucioPrevista, mMensajeResultado;
    private Button mAñadir;
    private String token;
    private Prestec prestec = new Prestec();
    private Administrador_NuevoPrestecViewModel administrador_nuevoPrestecViewModel = new Administrador_NuevoPrestecViewModel();

    public Administrador_NuevoPrestecFragment() { }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAdministradorNuevoPrestecBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mIdUsuario = binding.editTextIdUsuari;
        mIdLibro = binding.editTextIdLlibre;
        mDataPrestec = binding.editTextDataPrestec;
        mDataDevolucioPrevista = binding.editTextDataDevolucioPrevista;
        mMensajeResultado = binding.textViewMensaje;
        mAñadir=binding.botonRegistro;

        mAñadir.setOnClickListener(new View.OnClickListener() {//Añadir nuevo prestamo
            @Override
            public void onClick(View view) {
                prestec.setIdUsuari(mIdUsuario.getText().toString());
                prestec.setIdLlibre(mIdLibro.getText().toString());
                prestec.setDataPrestec(mDataPrestec.getText().toString());
                prestec.setDataDevolucioPrevista(mDataDevolucioPrevista.getText().toString());

                esconderTeclado(getContext(), view);
                Bundle b = getActivity().getIntent().getExtras();
                token = b.getString("token"); //conseguimos el token
                if (conectividad(getContext()) && mIdUsuario.length() != 0 && mIdLibro.length() != 0 && mDataPrestec.length() != 0 ) {
                    administrador_nuevoPrestecViewModel.nuevoPrestec(prestec, getContext(), administrador_nuevoPrestecViewModel,token).observe(getViewLifecycleOwner(), new Observer<String>() {//Gestionamos en viewModel
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
                    if (mIdLibro.length() == 0) {//caso no haya datos en usuario
                        Toast.makeText(getContext(), "Falten dades: id llibre", Toast.LENGTH_SHORT).show();
                        Log.d("View", "Titulo vacio");
                        mIdLibro.requestFocus();
                    }
                    if (mIdUsuario.length() == 0) {//caso no haya datos en usuario
                        Toast.makeText(getContext(), "Falten dades: Id usuari", Toast.LENGTH_SHORT).show();
                        mIdUsuario.requestFocus();
                    }
                    if (mDataPrestec.length() == 0) {//caso no haya datos en usuario
                        Toast.makeText(getContext(), "Falten dades: Data prestec", Toast.LENGTH_SHORT).show();
                        mDataPrestec.requestFocus();
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