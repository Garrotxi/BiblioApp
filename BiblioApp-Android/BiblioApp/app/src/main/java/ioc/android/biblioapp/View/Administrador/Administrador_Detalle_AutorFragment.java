/**
 * @Autor Saúl López Díez
 * Clase Administrador_Detalle_LibroFragment con el fragment con el detalle de un libro y llamadas a metodos para modificar y borrar del Administrador
 */
package ioc.android.biblioapp.View.Administrador;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import ioc.android.biblioapp.Model.Clases.Autor;
import ioc.android.biblioapp.ViewModel.Administrador.Administrador_DetalleAutorViewModel;
import ioc.android.biblioapp.databinding.FragmentAdministradorDetalleAutorBinding;


public class Administrador_Detalle_AutorFragment extends Fragment {

    private Administrador_DetalleAutorViewModel administrador_detalleAutorViewModel;
    private FragmentAdministradorDetalleAutorBinding binding;
    private String token;
    private TextView mNom, mMensajeResultado;

    private Button mModificar, mBorrar;
    private Autor autor = new Autor();

    public Administrador_Detalle_AutorFragment() {
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
        administrador_detalleAutorViewModel =
                new ViewModelProvider(this).get(Administrador_DetalleAutorViewModel.class);

        binding = FragmentAdministradorDetalleAutorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Bundle b = getActivity().getIntent().getExtras();
        token = b.getString("token");
        mNom = binding.editTextNom;
        mMensajeResultado = binding.textViewMensaje;
        mModificar = binding.botonModificar;
        mBorrar = binding.botonBorrar;
        //conseguimos los datos del autor que queremos ver en detalle
        administrador_detalleAutorViewModel.conseguirAutor(administrador_detalleAutorViewModel, getContext(), token).observe(getViewLifecycleOwner(), new Observer<Autor>() {
            @Override
            public void onChanged(Autor author) {
                if (author == null) {
                    Log.d(getActivity().toString(), "Autor no existe");
                } else {
                    autor = author;
                    mNom.setText(author.getNom());
                    autor.setIdAutor(author.getIdAutor());
                }
            }
        });

        mModificar.setOnClickListener(new View.OnClickListener() {//modificamos el autor conforme la informacion en los campos
            @Override
            public void onClick(View view) {

                autor.setNom(mNom.getText().toString());

                if (administrador_detalleAutorViewModel != null) {
                    administrador_detalleAutorViewModel = new Administrador_DetalleAutorViewModel();
                }

                administrador_detalleAutorViewModel.modificarAutor(administrador_detalleAutorViewModel, getContext(), token, autor).observe(getViewLifecycleOwner(), new Observer<Autor>() {

                    @Override
                    public void onChanged(Autor s) {
                        mMensajeResultado.setVisibility(View.VISIBLE);
                        mMensajeResultado.setText("Autor modificado correctamente");

                        Log.d("Modificar autor", "Satisfactorio");

                    }
                });

            }
        });

        mBorrar.setOnClickListener(new View.OnClickListener() {//borramos el autor
            @Override
            public void onClick(View view) {
                if (administrador_detalleAutorViewModel != null) {
                    administrador_detalleAutorViewModel = new Administrador_DetalleAutorViewModel();
                }
                administrador_detalleAutorViewModel.borrarAutor(administrador_detalleAutorViewModel, getContext(), token, autor).observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        mMensajeResultado.setVisibility(View.VISIBLE);
                        mMensajeResultado.setText(s);
                        Log.d("Modificar autor", "Satisfactorio");
                    }
                });
            }
        });

        return root;
    }
}