/**
 * @Autor Saúl López Díez
 * Clase Administrador_PrestecFragment con el fragment para gestion de prestamos del Administrador
 */

package ioc.android.biblioapp.View.Administrador;

import android.os.Bundle;
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

import ioc.android.biblioapp.Model.Clases.Llibre;
import ioc.android.biblioapp.Model.Clases.Prestec;
import ioc.android.biblioapp.ViewModel.Adapter.AdaptadorListaLibros;
import ioc.android.biblioapp.ViewModel.Administrador.Administrador_GestionLibrosViewModel;
import ioc.android.biblioapp.ViewModel.Administrador.Administrador_PrestecViewModel;
import ioc.android.biblioapp.databinding.FragmentAdministradorLibrosBinding;
import ioc.android.biblioapp.databinding.FragmentAdministradorPrestecBinding;

public class Administrador_PrestecFragment extends Fragment {

    private Administrador_PrestecViewModel administrador_prestecViewModel;
    private FragmentAdministradorPrestecBinding binding;
    private RecyclerView mRecyclerView;
    private AdaptadorListaLibros mAdaptador;
    private String token;
    private LinkedList<String> mListaPrestecs, mLista;
    private ImageButton mOrdenarPrestecsAsc, mOrdenarPrestecsDesc;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        administrador_prestecViewModel =
                new ViewModelProvider(this).get(Administrador_PrestecViewModel.class);

        binding = FragmentAdministradorPrestecBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        administrador_prestecViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        Bundle b = getActivity().getIntent().getExtras();
        token = b.getString("token"); //conseguimos el token
        mListaPrestecs = new LinkedList<>();
        mLista = new LinkedList<>();
        //al iniciar el fragment, conseguimos un listado de libros
        administrador_prestecViewModel.getListaPrestecs(administrador_prestecViewModel, getContext(), token).observe(getViewLifecycleOwner(), new Observer<Collection>() {
            @Override
            public void onChanged(Collection prestecs) {

                Iterator it = prestecs.iterator();
                while (it.hasNext()) {
                    Prestec l = (Prestec) it.next();
                    mLista.add(l.getIdPrestec());
                    mListaPrestecs.add(l.getIdPrestec());
                }
                //gestionamos y actualizamos la vista cone el adapter del recycler view
                mRecyclerView = binding.listViewLibros;
                mAdaptador = new AdaptadorListaLibros(getContext(), mListaPrestecs);
                mRecyclerView.setAdapter(mAdaptador);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });

        //listener del boton ordenar ascendente
        mOrdenarPrestecsAsc = binding.botonOrdenarPrestamosAsc;
        mOrdenarPrestecsAsc.setOnClickListener(new View.OnClickListener() {
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

        mOrdenarPrestecsDesc = binding.botonOrdenarPrestamosDesc;
        mOrdenarPrestecsDesc.setOnClickListener(new View.OnClickListener() {
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