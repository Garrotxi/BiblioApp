/**
 * @Autor Saúl López Díez
 * Clase Administrador_GestionUsuariosFragment con el fragment para gestion de usuarios del Administrador
 */

package ioc.android.biblioapp.View.Administrador;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import ioc.android.biblioapp.Model.Clases.Usuari;
import ioc.android.biblioapp.ViewModel.Adapter.AdaptadorListaUsuarios;
import ioc.android.biblioapp.ViewModel.Administrador.Administrador_GestionUsuariosViewModel;
import ioc.android.biblioapp.databinding.FragmentAdministradorUsuariosBinding;

public class Administrador_GestionUsuariosFragment extends Fragment {

    private Administrador_GestionUsuariosViewModel gestionUsuariosViewModel;
    private FragmentAdministradorUsuariosBinding binding;
    private RecyclerView mRecyclerView;
    private AdaptadorListaUsuarios mAdaptador;
    private String token;
    private LinkedList<String> mListaUsuarios, mLista;
    private ImageButton mOrdenarUsuariosAsc, mOrdenarUsuariosDesc;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        gestionUsuariosViewModel =
                new ViewModelProvider(this).get(Administrador_GestionUsuariosViewModel.class);

        binding = FragmentAdministradorUsuariosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        gestionUsuariosViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Bundle b = getActivity().getIntent().getExtras();
        token = b.getString("token");
        mListaUsuarios = new LinkedList<>();
        mLista = new LinkedList<>();
        //al iniciar el freagment, conseguimos un listado de usuarios
        gestionUsuariosViewModel.getListaUsuarios(gestionUsuariosViewModel, getContext(), token).observe(getViewLifecycleOwner(), new Observer<Collection>() {
            @Override
            public void onChanged(Collection usuari) {
                Iterator it = usuari.iterator();
                while (it.hasNext()) {
                    Usuari u = (Usuari) it.next();
                    mLista.add(u.getNomUsuari());
                    mListaUsuarios.add(u.getNomUsuari());
                }
                mRecyclerView = binding.listViewUsuaris;
                mAdaptador = new AdaptadorListaUsuarios(getContext(), mListaUsuarios);
                mRecyclerView.setAdapter(mAdaptador);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });

        //listener del boton ordenar ascendente
        mOrdenarUsuariosAsc = binding.botonOrdenarUsuariosAsc;
        mOrdenarUsuariosAsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListaUsuarios.clear();
                Collections.sort(mLista);
                mRecyclerView = binding.listViewUsuaris;
                mAdaptador = new AdaptadorListaUsuarios(getContext(), mLista);
                mRecyclerView.setAdapter(mAdaptador);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });

        //listener del boton ordenar descendente
        mOrdenarUsuariosDesc = binding.botonOrdenarUsuariosDesc;
        mOrdenarUsuariosDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListaUsuarios.clear();
                Collections.sort(mLista, Collections.reverseOrder());
                mRecyclerView = binding.listViewUsuaris;
                mAdaptador = new AdaptadorListaUsuarios(getContext(), mLista);
                mRecyclerView.setAdapter(mAdaptador);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });

        mRecyclerView = binding.listViewUsuaris;
        mRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setActivated(true);
                int f = view.getId();
                String nombre = mAdaptador.toString();
                Log.d(getActivity().toString(), nombre);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}