/**
 * @Autor Saúl López Díez
 * Clase Administrador_Detalle_PrestecFragment con el fragment con el detalle de un prestamo y llamadas a metodos para modificar y borrar del Administrador
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

import ioc.android.biblioapp.Model.Clases.Prestec;
import ioc.android.biblioapp.ViewModel.Administrador.Administrador_DetallePrestecViewModel;
import ioc.android.biblioapp.databinding.FragmentAdministradorDetallePrestecBinding;


public class Administrador_Detalle_PrestecFragment extends Fragment {

    private Administrador_DetallePrestecViewModel administrador_detallePrestecViewModel;
    private FragmentAdministradorDetallePrestecBinding binding;
    private String token;
    private TextView mIdPrestec, mIdUsuari, mIdLlibre, mDataPrestec, mDataDevolucioPrevista, mDataDevolucio, mMensajeResultado;

    private Button mModificar, mBorrar;
    private Prestec prestec = new Prestec();

    public Administrador_Detalle_PrestecFragment() {
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
        administrador_detallePrestecViewModel =
                new ViewModelProvider(this).get(Administrador_DetallePrestecViewModel.class);

        binding = FragmentAdministradorDetallePrestecBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Bundle b = getActivity().getIntent().getExtras();
        token = b.getString("token");
        mIdPrestec = binding.editTextIdPrestec;
        mIdUsuari = binding.editTextIdUsuari;
        mIdLlibre = binding.editTextIdLlibre;
        mDataPrestec = binding.editTextDataPrestec;
        mDataDevolucioPrevista = binding.editTextDataDevolucioPrevista;
        mDataDevolucio = binding.editTextDataDevolucio;
        mMensajeResultado = binding.textViewMensaje;
        mModificar = binding.botonModificar;
        mBorrar = binding.botonBorrar;

        //conseguimos los datos del prestamo que queremos ver en detalle
        administrador_detallePrestecViewModel.conseguirPrestec(administrador_detallePrestecViewModel, getContext(), token).observe(getViewLifecycleOwner(), new Observer<Prestec>() {
            @Override
            public void onChanged(Prestec loan) {
                if (loan == null) {
                    Log.d(getActivity().toString(), "Presctec no existe");
                } else {
                    prestec = loan;
                    mIdPrestec.setText(loan.getIdPrestec());
                    mIdLlibre.setText(loan.getIdLlibre());
                    mIdUsuari.setText(loan.getIdUsuari());
                    mDataPrestec.setText(loan.getDataPrestec());
                    mDataDevolucioPrevista.setText(loan.getDataDevolucioPrevista());
                    mDataDevolucio.setText(loan.getDataDevolucio());

                    prestec.setIdPrestec(loan.getIdPrestec());
                }
            }
        });

        mModificar.setOnClickListener(new View.OnClickListener() {//modificamos el prestamo conforme la informacion en los campos
            @Override
            public void onClick(View view) {

                prestec.setIdPrestec(mIdPrestec.getText().toString());
                prestec.setIdLlibre(mIdLlibre.getText().toString());
                prestec.setIdUsuari(mIdUsuari.getText().toString());
                prestec.setDataPrestec(mDataPrestec.getText().toString());
                prestec.setDataDevolucioPrevista(mDataDevolucioPrevista.getText().toString());
                prestec.setDataDevolucio(mDataDevolucio.getText().toString());

                if (administrador_detallePrestecViewModel != null) {
                    administrador_detallePrestecViewModel = new Administrador_DetallePrestecViewModel();
                }

                administrador_detallePrestecViewModel.modificarPrestec(administrador_detallePrestecViewModel, getContext(), token, prestec).observe(getViewLifecycleOwner(), new Observer<Prestec>() {

                    @Override
                    public void onChanged(Prestec s) {
                        mMensajeResultado.setVisibility(View.VISIBLE);
                        mMensajeResultado.setText("Prestec modificado correctamente");

                        Log.d("Modificar prestec", "Satisfactorio");

                    }
                });

            }
        });

        mBorrar.setOnClickListener(new View.OnClickListener() {//borramos el prestamo
            @Override
            public void onClick(View view) {
                if (administrador_detallePrestecViewModel != null) {
                    administrador_detallePrestecViewModel = new Administrador_DetallePrestecViewModel();
                }
                administrador_detallePrestecViewModel.borrarPrestec(administrador_detallePrestecViewModel, getContext(), token, prestec).observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        mMensajeResultado.setVisibility(View.VISIBLE);
                        mMensajeResultado.setText(s);
                        Log.d("Modificar prestec", "Satisfactorio");
                    }
                });
            }
        });

        return root;
    }
}