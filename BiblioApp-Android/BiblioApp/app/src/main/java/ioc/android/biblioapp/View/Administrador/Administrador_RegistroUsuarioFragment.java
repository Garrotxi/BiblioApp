/**
 * @Autor Saúl López Díez
 * Clase Administrador_RegistroUsuarioFragment con el fragment para crear un nuevo usuario del Administrador
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

import java.util.ArrayList;
import java.util.Collection;

import ioc.android.biblioapp.Model.Clases.Usuari;
import ioc.android.biblioapp.ViewModel.Administrador.Administrador_RegistroUsuarioViewModel;
import ioc.android.biblioapp.databinding.FragmentAdministradorRegistroUsuarioBinding;


public class Administrador_RegistroUsuarioFragment extends Fragment {

    private FragmentAdministradorRegistroUsuarioBinding binding;
    private TextView mNomUsuari, mNom, mCognoms, mEmail, mTelefon, mContraseña,mMensajeResultado;
    private Boolean mAdmin;
    private Button mRegistro;
    private Usuari usuari = new Usuari();
    private Administrador_RegistroUsuarioViewModel administrador_registroUsuarioViewModel=new Administrador_RegistroUsuarioViewModel();



    public Administrador_RegistroUsuarioFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAdministradorRegistroUsuarioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mNomUsuari = binding.editTextNomUsuari;
        mNom = binding.editTextNom;
        mCognoms = binding.editTextCognoms;
        mEmail = binding.editTextEmail;
        mTelefon = binding.editTextTelefon;
        mContraseña = binding.editTextContraseA;
        mMensajeResultado= binding.textViewMensaje;
        mRegistro= binding.botonRegistro;

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


        mRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuari.setNomUsuari(mNomUsuari.getText().toString());
                usuari.setNom(mNom.getText().toString());
                usuari.setCognoms(mCognoms.getText().toString());
                usuari.setEmail(mEmail.getText().toString());
                usuari.setTelefon(mTelefon.getText().toString());
                usuari.setContrasenya(mContraseña.getText().toString());

                if (mAdmin){
                    Collection c= new ArrayList();
                    c.add("admin");
                    usuari.setRols(c);
                }

                esconderTeclado(getContext(), view);

                if (conectividad(getContext()) && mNomUsuari.length() != 0 && mNom.length() != 0 && mCognoms.length() != 0 && mEmail.length() != 0 && mTelefon.length() != 0 && mContraseña.length() != 0) {
                    administrador_registroUsuarioViewModel.registroViewModel(usuari, getContext(), administrador_registroUsuarioViewModel).observe(getViewLifecycleOwner(), new Observer<String>() {//Gestionamos en viewModel
                        @Override
                        public void onChanged(String s) {
                            if (s!=null){
                                esconderTeclado(getContext(), view);
                                mMensajeResultado.setVisibility(View.VISIBLE);
                                mMensajeResultado.setText(s);
                                Log.d("Registro", s);
                            }else{
                                mMensajeResultado.setVisibility(View.VISIBLE);
                                mMensajeResultado.setText(s);
                                Log.d("Registro", "Error");
                            }
                        }
                    });
                }
                else {
                    if (mContraseña.length() == 0) {//caso no haya datos en usuario
                        Toast.makeText(getContext(), "Falten dades: Contrasenya", Toast.LENGTH_SHORT).show();
                        mContraseña.requestFocus();
                    }
                    if (mTelefon.length() == 0) {//caso no haya datos en usuario
                        Toast.makeText(getContext(), "Falten dades: Telefon", Toast.LENGTH_SHORT).show();
                        mTelefon.requestFocus();
                    }
                    if (mEmail.length() == 0) {//caso no haya datos en usuario
                        Toast.makeText(getContext(), "Falten dades: Email", Toast.LENGTH_SHORT).show();
                        mEmail.requestFocus();
                    }
                    if (mCognoms.length() == 0) {//caso no haya datos en usuario
                        Toast.makeText(getContext(), "Falten dades: Cognoms", Toast.LENGTH_SHORT).show();
                        mCognoms.requestFocus();
                    }
                    if (mNom.length() == 0) {//caso no haya datos en constraseña
                        Toast.makeText(getContext(), "Falten dades: nom ", Toast.LENGTH_SHORT).show();
                        mNom.requestFocus();
                    }
                    if (mNomUsuari.length() == 0) {//caso no haya datos en nombre de usuario
                        Toast.makeText(getContext(), "Falten dades: nom d'usuari", Toast.LENGTH_SHORT).show();
                        mNomUsuari.requestFocus();
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