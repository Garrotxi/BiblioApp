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

import ioc.android.biblioapp.Model.Clases.Llibre;
import ioc.android.biblioapp.ViewModel.Administrador.Administrador_GestionDetalleLibroViewModel;
import ioc.android.biblioapp.databinding.FragmentAdministradorDetalleLibroBinding;


public class Administrador_Detalle_LibroFragment extends Fragment {

    private Administrador_GestionDetalleLibroViewModel administrador_gestionDetalleLibroViewModel;
    private FragmentAdministradorDetalleLibroBinding binding;
    private String token;
    private TextView mTitul, mData, mCopies, mIsbn, mDescripcio, mMensajeResultado;

    private Button mModificar, mBorrar;
    private Llibre llibre = new Llibre();

    public Administrador_Detalle_LibroFragment() {
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
        administrador_gestionDetalleLibroViewModel =
                new ViewModelProvider(this).get(Administrador_GestionDetalleLibroViewModel.class);

        binding = FragmentAdministradorDetalleLibroBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Bundle b = getActivity().getIntent().getExtras();
        token = b.getString("token");
        mTitul = binding.editTextTitul;
        mData = binding.editTextData;
        mCopies = binding.editTextCopies;
        mIsbn = binding.editTextIsbn;
        mDescripcio = binding.editTextDescripcio;
        mMensajeResultado = binding.textViewMensaje;
        mModificar = binding.botonModificar;
        mBorrar = binding.botonBorrar;

        //conseguimos los datos del libro que queremos ver en detalle
        administrador_gestionDetalleLibroViewModel.conseguirLibro(administrador_gestionDetalleLibroViewModel, getContext(), token).observe(getViewLifecycleOwner(), new Observer<Llibre>() {
            @Override
            public void onChanged(Llibre book) {
                if (book == null) {
                    Log.d(getActivity().toString(), "Libro no existe");
                } else {
                    llibre = book;
                    mTitul.setText(book.getTitulLlibre());
                    mData.setText(book.getDataPublicacio());
                    mCopies.setText(book.getCopiesDisponibles());
                    mIsbn.setText(book.getIsbn());
                    mDescripcio.setText(book.getDescripcio());
                    llibre.setIdLlibre(book.getIdLlibre());
                }
            }
        });

        mModificar.setOnClickListener(new View.OnClickListener() {//modificamos el libro conforme la informacion en los campos
            @Override
            public void onClick(View view) {

                llibre.setTitulLlibre(mTitul.getText().toString());
                llibre.setDataPublicacio(mData.getText().toString());
                llibre.setCopiesDisponibles(mCopies.getText().toString());
                llibre.setIsbn(mIsbn.getText().toString());
                llibre.setDescripcio(mDescripcio.getText().toString());

                if (administrador_gestionDetalleLibroViewModel != null) {
                    administrador_gestionDetalleLibroViewModel = new Administrador_GestionDetalleLibroViewModel();
                }

                administrador_gestionDetalleLibroViewModel.modificarLibro(administrador_gestionDetalleLibroViewModel, getContext(), token, llibre).observe(getViewLifecycleOwner(), new Observer<Llibre>() {

                    @Override
                    public void onChanged(Llibre s) {
                        mMensajeResultado.setVisibility(View.VISIBLE);
                        mMensajeResultado.setText("Libro modificado correctamente");

                        Log.d("Modificar libro", "Satisfactorio");

                    }
                });

            }
        });

        mBorrar.setOnClickListener(new View.OnClickListener() {//borramos el libro
            @Override
            public void onClick(View view) {
                if (administrador_gestionDetalleLibroViewModel != null) {
                    administrador_gestionDetalleLibroViewModel = new Administrador_GestionDetalleLibroViewModel();
                }
                administrador_gestionDetalleLibroViewModel.borrarLibro(administrador_gestionDetalleLibroViewModel, getContext(), token, llibre).observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        mMensajeResultado.setVisibility(View.VISIBLE);
                        mMensajeResultado.setText(s);
                        Log.d("Modificar libro", "Satisfactorio");
                    }
                });
            }
        });

        return root;
    }
}