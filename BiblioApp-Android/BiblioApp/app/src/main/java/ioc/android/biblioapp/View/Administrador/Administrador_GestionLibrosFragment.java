/**
 * @Autor Saúl López Díez
 * Clase Administrador_GestionLibrosFragment con el fragment para gestion de libros del Administrador
 */

package ioc.android.biblioapp.View.Administrador;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import ioc.android.biblioapp.Model.Clases.Llibre;
import ioc.android.biblioapp.ViewModel.Administrador.Administrador_GestionLibrosViewModel;
import ioc.android.biblioapp.ViewModel.Adapter.AdaptadorListaLibros;
import ioc.android.biblioapp.databinding.FragmentAdministradorLibrosBinding;

public class Administrador_GestionLibrosFragment extends Fragment {

    private Administrador_GestionLibrosViewModel gestionLibrosViewModel;
    private FragmentAdministradorLibrosBinding binding;
    private RecyclerView mRecyclerView;
    private AdaptadorListaLibros mAdaptador;
    private String token;
    private LinkedList<String> mListaLibros, mLista;
    private Button mOrdenarLibrosAsc, mOrdenarLibrosDesc;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        gestionLibrosViewModel =
                new ViewModelProvider(this).get(Administrador_GestionLibrosViewModel.class);

        binding = FragmentAdministradorLibrosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        final TextView textView = binding.textSlideshow;
        gestionLibrosViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        Bundle b = getActivity().getIntent().getExtras();
        token = b.getString("token"); //conseguimos el token
        mListaLibros = new LinkedList<>();
        mLista = new LinkedList<>();
        //al iniciar el freagment, conseguimos un listado de libros
        gestionLibrosViewModel.getListaLibros(gestionLibrosViewModel, getContext(), token).observe(getViewLifecycleOwner(), new Observer<Collection>() {
            @Override
            public void onChanged(Collection libros) {

                Iterator it = libros.iterator();
                while (it.hasNext()) {
                    Llibre l = (Llibre) it.next();
                    mLista.add( l.getTitulLlibre());
                    mListaLibros.add(l.getTitulLlibre());
                }
                //gestionamos y actualizamos la vista cone el adapter del recycler view
                mRecyclerView = binding.listViewLibros;
                mAdaptador = new AdaptadorListaLibros(getContext(), mListaLibros);
                mRecyclerView.setAdapter(mAdaptador);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });

        //listener del boton ordenar ascendente
        mOrdenarLibrosAsc = binding.botonOrdenarLibrosAsc;
        mOrdenarLibrosAsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(mLista);
                mRecyclerView = binding.listViewLibros;
                mAdaptador = new AdaptadorListaLibros(getContext(), mLista);
                mRecyclerView.setAdapter(mAdaptador);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });

        //listener del boton ordenar desscendente

        mOrdenarLibrosDesc = binding.botonOrdenarLibrosDesc;
        mOrdenarLibrosDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(mLista, Collections.reverseOrder());
                mRecyclerView = binding.listViewLibros;
                mAdaptador = new AdaptadorListaLibros(getContext(), mLista);
                mRecyclerView.setAdapter(mAdaptador);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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