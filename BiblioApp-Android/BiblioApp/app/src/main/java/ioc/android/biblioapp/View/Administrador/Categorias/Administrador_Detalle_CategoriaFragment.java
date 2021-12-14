/**
 * @Autor Saúl López Díez
 * Clase Administrador_Detalle_CategoriaFragment con el fragment con el detalle de una categoria y llamadas a metodos para modificar y borrar del Administrador
 */
package ioc.android.biblioapp.View.Administrador.Categorias;

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

import ioc.android.biblioapp.Model.Clases.Categoria;
import ioc.android.biblioapp.ViewModel.Administrador.Categorias.Administrador_DetalleCategoriaViewModel;
import ioc.android.biblioapp.databinding.FragmentAdministradorDetalleCategoriaBinding;


public class Administrador_Detalle_CategoriaFragment extends Fragment {

    private Administrador_DetalleCategoriaViewModel administrador_detalleCategoriaViewModel;
    private FragmentAdministradorDetalleCategoriaBinding binding;
    private String token;
    private TextView mNom, mMensajeResultado;

    private Button mModificar, mBorrar;
    private Categoria categoria = new Categoria();

    public Administrador_Detalle_CategoriaFragment() {
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
        administrador_detalleCategoriaViewModel =
                new ViewModelProvider(this).get(Administrador_DetalleCategoriaViewModel.class);

        binding = FragmentAdministradorDetalleCategoriaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Bundle b = getActivity().getIntent().getExtras();
        token = b.getString("token");
        mNom = binding.editTextNom;
        mMensajeResultado = binding.textViewMensaje;
        mModificar = binding.botonModificar;
        mBorrar = binding.botonBorrar;
        //conseguimos los datos de la Categoria que queremos ver en detalle
        administrador_detalleCategoriaViewModel.conseguirCategoria(administrador_detalleCategoriaViewModel, getContext(), token).observe(getViewLifecycleOwner(), new Observer<Categoria>() {
            @Override
            public void onChanged(Categoria categoria1) {
                if (categoria1 == null) {
                    Log.d(getActivity().toString(), "Categoria no existe");
                } else {
                    categoria = categoria1;
                    mNom.setText(categoria1.getNom());
                    categoria.setIdCategoria(categoria1.getIdCategoria());
                }
            }
        });

        mModificar.setOnClickListener(new View.OnClickListener() {//modificamos la Categoria conforme la informacion en los campos
            @Override
            public void onClick(View view) {

                categoria.setNom(mNom.getText().toString());

                if (administrador_detalleCategoriaViewModel != null) {
                    administrador_detalleCategoriaViewModel = new Administrador_DetalleCategoriaViewModel();
                }

                administrador_detalleCategoriaViewModel.modificarCategoria(administrador_detalleCategoriaViewModel, getContext(), token, categoria).observe(getViewLifecycleOwner(), new Observer<Categoria>() {

                    @Override
                    public void onChanged(Categoria s) {
                        mMensajeResultado.setVisibility(View.VISIBLE);
                        mMensajeResultado.setText("Categoria modificado correctamente");

                        Log.d("Modificar Categoria", "Satisfactorio");

                    }
                });

            }
        });

        mBorrar.setOnClickListener(new View.OnClickListener() {//borramos la Categoria
            @Override
            public void onClick(View view) {
                if (administrador_detalleCategoriaViewModel != null) {
                    administrador_detalleCategoriaViewModel = new Administrador_DetalleCategoriaViewModel();
                }
                administrador_detalleCategoriaViewModel.borrarCategoria(administrador_detalleCategoriaViewModel, getContext(), token, categoria).observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        mMensajeResultado.setVisibility(View.VISIBLE);
                        mMensajeResultado.setText(s);
                        Log.d("Modificar Categoria", "Satisfactorio");
                    }
                });
            }
        });

        return root;
    }
}