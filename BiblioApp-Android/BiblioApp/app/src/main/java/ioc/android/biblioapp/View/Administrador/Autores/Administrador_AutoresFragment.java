/**
 * @Autor Saúl López Díez
 * Clase Administrador_AutoresFragment con el fragment para gestion de autores del Administrador
 */


package ioc.android.biblioapp.View.Administrador.Autores;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

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

import ioc.android.biblioapp.Model.Clases.Autor;
import ioc.android.biblioapp.ViewModel.Adapter.AdaptadorListaUsuarios;
import ioc.android.biblioapp.ViewModel.Administrador.Autores.Administrador_AutoresViewModel;
import ioc.android.biblioapp.databinding.FragmentAdministradorAutoresBinding;


public class Administrador_AutoresFragment extends Fragment {

    private Administrador_AutoresViewModel administrador_autoresViewModel;
    private FragmentAdministradorAutoresBinding binding;
    private RecyclerView mRecyclerView;
    private AdaptadorListaUsuarios mAdaptador;
    private String token;
    private LinkedList<String> mListaAutores, mLista;
    private ImageButton mOrdenarAutoresAsc, mOrdenarAutoresDesc;



    public Administrador_AutoresFragment() { }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        administrador_autoresViewModel =
                new ViewModelProvider(this).get(Administrador_AutoresViewModel.class);

        binding = FragmentAdministradorAutoresBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        administrador_autoresViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Bundle b = getActivity().getIntent().getExtras();
        token = b.getString("token");
        mListaAutores = new LinkedList<>();
        mLista = new LinkedList<>();
        //al iniciar el freagment, conseguimos un listado de usuarios
        administrador_autoresViewModel.getListaAutores(administrador_autoresViewModel, getContext(), token).observe(getViewLifecycleOwner(), new Observer<Collection>() {
            @Override
            public void onChanged(Collection autors) {
                Iterator it = autors.iterator();
                while (it.hasNext()) {
                    Autor a = (Autor) it.next();
                    mLista.add(a.getNom());
                    mListaAutores.add(a.getNom());
                }
                mRecyclerView = binding.listViewAutores;
                mAdaptador = new AdaptadorListaUsuarios(getContext(), mListaAutores);
                mRecyclerView.setAdapter(mAdaptador);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });

        //listener del boton ordenar ascendente
        mOrdenarAutoresAsc = binding.botonOrdenarAutoresAsc;
        mOrdenarAutoresAsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListaAutores.clear();
                Collections.sort(mLista);
                mRecyclerView = binding.listViewAutores;
                mAdaptador = new AdaptadorListaUsuarios(getContext(), mLista);
                mRecyclerView.setAdapter(mAdaptador);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });

        //listener del boton ordenar descendente
        mOrdenarAutoresDesc = binding.botonOrdenarAutoresDesc;
        mOrdenarAutoresDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListaAutores.clear();
                Collections.sort(mLista, Collections.reverseOrder());
                mRecyclerView = binding.listViewAutores;
                mAdaptador = new AdaptadorListaUsuarios(getContext(), mLista);
                mRecyclerView.setAdapter(mAdaptador);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });

        mRecyclerView = binding.listViewAutores;
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
}