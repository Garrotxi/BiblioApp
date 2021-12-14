/**
 * @Autor Saúl López Díez
 * Clase Usuario_GestionUsuariosFragment con el fragment para gestion del perfil Usuario
 */

package ioc.android.biblioapp.View.Usuario;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ioc.android.biblioapp.Model.Clases.Usuari;
import ioc.android.biblioapp.ViewModel.Usuario.Usuario_GestionUsuariosViewModel;
import ioc.android.biblioapp.databinding.FragmentUsuarioUsuariosBinding;

public class Usuario_GestionUsuariosFragment extends Fragment {

    private Usuario_GestionUsuariosViewModel usuario_gestionUsuariosViewModel;
    private FragmentUsuarioUsuariosBinding binding;
    private String token;
    private TextView mNomUsuari, mNom, mCognoms, mEmail, mTelefon, mContraseña, mMensajeResultado;

    private Button mModificar;
    private Usuari usuari = new Usuari();


    public Usuario_GestionUsuariosFragment() {
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
        usuario_gestionUsuariosViewModel =
                new ViewModelProvider(this).get(Usuario_GestionUsuariosViewModel.class);

        binding = FragmentUsuarioUsuariosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Bundle b = getActivity().getIntent().getExtras();
        token = b.getString("token");
        mNomUsuari = binding.editTextNomUsuari;
        mNom = binding.editTextNom;
        mCognoms = binding.editTextCognoms;
        mEmail = binding.editTextEmail;
        mTelefon = binding.editTextTelefon;
        mContraseña = binding.editTextContraseA;
        mMensajeResultado = binding.textViewMensaje;
        mModificar = binding.botonModificar;


        //Conseguimos informacion del usuarioz
/*        usuario_gestionUsuariosViewModel.conseguirUsuario(usuario_gestionUsuariosViewModel, getContext(), token, usuari).observe(getViewLifecycleOwner(), new Observer<Usuari>() {
            @Override
            public void onChanged(Usuari user) {
                if (user == null) {
                    Log.d(getActivity().toString(), "Usuario no existe");
                } else {
                    usuari = user;
                    mNomUsuari.setText(usuari.getNomUsuari());
                    mNom.setText(usuari.getNom());
                    mCognoms.setText(usuari.getCognoms());
                    mEmail.setText(usuari.getEmail());
                    mTelefon.setText(usuari.getTelefon());
                    mContraseña.setText(usuari.getContrasenya());
                    usuari.setIdUsuari(user.getIdUsuari());
                }
            }

        });*/


/*

        //Modificamos al usuario conforme la informacion de los campos
        mModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                usuari.setNomUsuari(mNomUsuari.getText().toString());
                usuari.setNom(mNom.getText().toString());
                usuari.setCognoms(mCognoms.getText().toString());
                usuari.setEmail(mEmail.getText().toString());
                usuari.setTelefon(mTelefon.getText().toString());
                usuari.setContrasenya(mContraseña.getText().toString());

                usuario_gestionUsuariosViewModel.modificarUsuario(usuario_gestionUsuariosViewModel, getContext(), token, usuari).observe(getViewLifecycleOwner(), new Observer<Usuari>() {

                    @Override
                    public void onChanged(Usuari s) {
                        mMensajeResultado.setVisibility(View.VISIBLE);
                        mMensajeResultado.setText("Usuario modificado correctamente");
                        Log.d("Modificar usuario", "Satisfactorio");
                    }
                });
            }
        });
*/


        return root;
    }
}