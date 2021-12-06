package ioc.android.biblioapp.View.Administrador;

import static ioc.android.biblioapp.ViewModel.Utilidades.Utilidades.esconderTeclado;

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

import ioc.android.biblioapp.Model.Clases.Usuari;
import ioc.android.biblioapp.ViewModel.Administrador.Administrador_GestionDetalleUsuarioViewModel;
import ioc.android.biblioapp.databinding.FragmentAdministradorDetalleUsuarioBinding;

public class Administrador_DetalleUsuarioFragment extends Fragment {

    private Administrador_GestionDetalleUsuarioViewModel administrador_gestionDetalleUsuarioViewModel;
    private FragmentAdministradorDetalleUsuarioBinding binding;
    private String token;
    private TextView mNomUsuari, mNom, mCognoms, mEmail, mTelefon, mContraseña,mMensajeResultado;
    private Boolean mAdmin;
    private Button mModificar, mBorrar;
    private Usuari usuari = new Usuari();


    public Administrador_DetalleUsuarioFragment() {
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

        administrador_gestionDetalleUsuarioViewModel =
                new ViewModelProvider(this).get(Administrador_GestionDetalleUsuarioViewModel.class);

        binding = FragmentAdministradorDetalleUsuarioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Bundle b = getActivity().getIntent().getExtras();
        token = b.getString("token");


        mNomUsuari = binding.editTextNomUsuari;
        mNom = binding.editTextNom;
        mCognoms = binding.editTextCognoms;
        mEmail = binding.editTextEmail;
        mTelefon = binding.editTextTelefon;
        mContraseña = binding.editTextContraseA;

        mMensajeResultado= binding.textViewMensaje;
        mModificar= binding.botonModificar;
        mBorrar=binding.botonBorrar;

        mAdmin=false;
        binding.esAdmin.setOnClickListener(new View.OnClickListener() {//evento para el checkbox
            @Override
            public void onClick(View view) {
                if(!mAdmin){
                    mAdmin=true;
                }else{
                    mAdmin=false;
                }
                esconderTeclado(getContext(), view);
            }
        });


        administrador_gestionDetalleUsuarioViewModel.conseguirUsuario(administrador_gestionDetalleUsuarioViewModel, getContext(), token).observe(getViewLifecycleOwner(), new Observer<Usuari>() {
            @Override
            public void onChanged(Usuari user) {
                if (user==null){
                    Log.d(getActivity().toString(),"Usuario no existe");
                }else{
                    usuari=user;
                    mNomUsuari.setText(usuari.getNomUsuari());
                    mNom.setText(usuari.getNom());
                    mCognoms.setText(usuari.getCognoms());
                    mEmail.setText(usuari.getEmail());
                    mTelefon.setText(usuari.getTelefon());
                    mContraseña.setText(usuari.getContrasenya());
                }
            }

        });










        return root;
    }
}