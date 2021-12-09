/**
 * @Autor Saúl López Díez
 * Clase Administrador_BuscarLibroFragment con el fragment para buscar libros del Administrador
 */

package ioc.android.biblioapp.View.Administrador;

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

import ioc.android.biblioapp.Model.Clases.Llibre;
import ioc.android.biblioapp.R;
import ioc.android.biblioapp.ViewModel.Administrador.Administrador_GestionBuscarLibroViewModel;
import ioc.android.biblioapp.ViewModel.Utilidades.Utilidades;
import ioc.android.biblioapp.databinding.FragmentAdministradorBuscarLibroBinding;

public class Administrador_BuscarLibroFragment extends Fragment {

    private Administrador_GestionBuscarLibroViewModel administrador_gestionBuscarLibroViewModel;
    private FragmentAdministradorBuscarLibroBinding binding;
    private String token, criterio;
    private TextView mTitul, mData, mCopies, mIsbn, mDescripcio, mMensajeResultado;
    private Spinner mSpinner;
    private Button mBuscar;
    private Llibre llibre = new Llibre();


    public Administrador_BuscarLibroFragment() {
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
        administrador_gestionBuscarLibroViewModel =
                new ViewModelProvider(this).get(Administrador_GestionBuscarLibroViewModel.class);

        binding = FragmentAdministradorBuscarLibroBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Bundle b = getActivity().getIntent().getExtras();
        token = b.getString("token");

        mTitul = binding.editTextTitul;
        mData = binding.editTextData;
        mCopies = binding.editTextCopies;
        mIsbn = binding.editTextIsbn;
        mDescripcio = binding.editTextDescripcio;
        mSpinner = binding.spinnerBusqueda;
        mMensajeResultado = binding.textViewMensaje;
        mBuscar = binding.botonBuscar;
        criterio = null;

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.busqueda, android.R.layout.simple_spinner_item);

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
                mTitul.setText("");
                mData.setText("");
                mCopies.setText("");
                mIsbn.setText("");
                mDescripcio.setText("");

                Utilidades.esconderTeclado(getContext(), view);
                switch (criterio) {
                    case ("Id")://busqueda por Id
                        llibre.setIdLlibre(binding.editTextBusqueda.getText().toString());
                        if (administrador_gestionBuscarLibroViewModel != null) {
                            administrador_gestionBuscarLibroViewModel = new Administrador_GestionBuscarLibroViewModel();
                        }
                        administrador_gestionBuscarLibroViewModel.buscarLibroId(administrador_gestionBuscarLibroViewModel, getContext(), token, llibre).observe(getViewLifecycleOwner(), new Observer<Llibre>() {

                            @Override
                            public void onChanged(Llibre llibre1) {
                                llibre = llibre1;
                                mTitul.setText(llibre1.getTitulLlibre());
                                mData.setText(llibre1.getDataPublicacio());
                                mCopies.setText(llibre1.getCopiesDisponibles());
                                mIsbn.setText(llibre1.getIsbn());
                                mDescripcio.setText(llibre1.getDescripcio());
                                Log.d("Buscar libro id", "Satisfactorio");

                                if (llibre.getTitulLlibre().equalsIgnoreCase("Error")) {
                                    mMensajeResultado.setVisibility(View.VISIBLE);
                                    mMensajeResultado.setText("Llibre no trobat");
                                }
                            }
                        });

                        break;
                    case ("Titul")://busqueda por titulo
                        llibre.setTitulLlibre(binding.editTextBusqueda.getText().toString());
                        if (administrador_gestionBuscarLibroViewModel != null) {
                            administrador_gestionBuscarLibroViewModel = new Administrador_GestionBuscarLibroViewModel();
                        }
                        administrador_gestionBuscarLibroViewModel.buscarLibroTitulo(administrador_gestionBuscarLibroViewModel, getContext(), token, llibre).observe(getViewLifecycleOwner(), new Observer<Llibre>() {

                            @Override
                            public void onChanged(Llibre llibre1) {
                                llibre = llibre1;
                                mTitul.setText(llibre1.getTitulLlibre());
                                mData.setText(llibre1.getDataPublicacio());
                                mCopies.setText(llibre1.getCopiesDisponibles());
                                mIsbn.setText(llibre1.getIsbn());
                                mDescripcio.setText(llibre1.getDescripcio());
                                Log.d("Buscar libro titulo", "Satisfactorio");
                                if (llibre.getTitulLlibre().equalsIgnoreCase("Error")) {
                                    mMensajeResultado.setVisibility(View.VISIBLE);
                                    mMensajeResultado.setText("Llibre no trobat");
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