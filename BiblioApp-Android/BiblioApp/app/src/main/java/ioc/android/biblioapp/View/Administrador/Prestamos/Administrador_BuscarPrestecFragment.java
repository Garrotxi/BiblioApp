/**
 * @Autor Saúl López Díez
 * Clase Administrador_BuscarPrestecFragment con el fragment para buscar prestamos del Administrador
 */

package ioc.android.biblioapp.View.Administrador.Prestamos;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import ioc.android.biblioapp.Model.Clases.Prestec;
import ioc.android.biblioapp.R;
import ioc.android.biblioapp.ViewModel.Administrador.Prestamos.Administrador_BuscarPrestecViewModel;
import ioc.android.biblioapp.ViewModel.Utilidades.Utilidades;
import ioc.android.biblioapp.databinding.FragmentAdministradorBuscarPrestecBinding;

public class Administrador_BuscarPrestecFragment extends Fragment {

    private Administrador_BuscarPrestecViewModel administrador_buscarPrestecViewModel;
    private FragmentAdministradorBuscarPrestecBinding binding;
    private String token, criterio;
    private TextView mIdPrestec, mIdUsuari, mIdLlibre, mDataPrestec, mDataDevolucioPrevista, mDataDevolucio, mMensajeResultado;
    private Spinner mSpinner;
    private Button mBuscar;
    private Prestec prestec = new Prestec();


    public Administrador_BuscarPrestecFragment() {
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
        administrador_buscarPrestecViewModel =
                new ViewModelProvider(this).get(Administrador_BuscarPrestecViewModel.class);

        binding = FragmentAdministradorBuscarPrestecBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Bundle b = getActivity().getIntent().getExtras();
        token = b.getString("token");

        mIdPrestec = binding.editTextIdPrestec;
        mIdUsuari = binding.editTextIdUsuari;
        mIdLlibre = binding.editTextIdLlibre;
        mDataPrestec = binding.editTextDataPrestec;
        mDataDevolucioPrevista = binding.editTextDataDevolucioPrevista;
        mDataDevolucio = binding.editTextDataDevolucio;
        mSpinner = binding.spinnerBusqueda;
        mMensajeResultado = binding.textViewMensaje;
        mBuscar = binding.botonBuscar;
        criterio = null;

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.prestec, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {//Spinner con el criterio por el que se realizara la busqueda
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                criterio = adapterView.getItemAtPosition(i).toString();
                Log.d("Seleccion Spiner:", criterio);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mBuscar.setOnClickListener(new View.OnClickListener() {//Accion boton buscar
            @Override
            public void onClick(View view) {
                mIdPrestec.setText("");
                mIdLlibre.setText("");
                mIdUsuari.setText("");
                mDataPrestec.setText("");
                mDataDevolucioPrevista.setText("");
                mDataDevolucio.setText("");

                Utilidades.esconderTeclado(getContext(), view);
                switch (criterio) {
                    case ("Id")://busqueda por Id
                        prestec.setIdPrestec(binding.editTextBusqueda.getText().toString());
                        if (administrador_buscarPrestecViewModel != null) {
                            administrador_buscarPrestecViewModel = new Administrador_BuscarPrestecViewModel();
                        }
                        administrador_buscarPrestecViewModel.buscarPrestecId(administrador_buscarPrestecViewModel, getContext(), token, prestec).observe(getViewLifecycleOwner(), new Observer<Prestec>() {

                            @Override
                            public void onChanged(Prestec prestec1) {
                                if (mMensajeResultado.VISIBLE==0){
                                    mMensajeResultado.setVisibility(View.INVISIBLE);
                                }
                                prestec = prestec1;
                                mIdPrestec.setText(prestec1.getIdPrestec());
                                mIdLlibre.setText(prestec1.getIdLlibre());
                                mIdUsuari.setText(prestec1.getIdUsuari());
                                mDataPrestec.setText(prestec1.getDataPrestec());
                                mDataDevolucioPrevista.setText(prestec1.getDataDevolucioPrevista());
                                mDataDevolucio.setText(prestec1.getDataDevolucio());
                                Log.d("Buscar prestec id", "Satisfactorio");

                                if (prestec1.getIdPrestec().equalsIgnoreCase("Error")) {
                                    mMensajeResultado.setVisibility(View.VISIBLE);
                                    mMensajeResultado.setText("Prestec no trobat");
                                }
                            }
                        });

                        break;


                    default:
                        break;
                }


            }

        });


        return root;
    }
}