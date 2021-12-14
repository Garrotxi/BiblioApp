/**
 * @Autor Saúl López Díez
 * Clase Usuario_GestionLibrosFragment con el fragment para gestion de libros del Usuario
 */

package ioc.android.biblioapp.View.Usuario;

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
import ioc.android.biblioapp.ViewModel.Adapter.AdaptadorListaLibros;
import ioc.android.biblioapp.ViewModel.Usuario.Usuario_GestionLibrosViewModel;
import ioc.android.biblioapp.databinding.FragmentUsuarioLibrosBinding;

public class Usuario_GestionLibrosFragment extends Fragment {

    private Usuario_GestionLibrosViewModel gestionLibrosViewModel;
    //
    private FragmentUsuarioLibrosBinding binding;
    private RecyclerView mRecyclerView;
    private AdaptadorListaLibros mAdaptador;
    private String token;
    private LinkedList<String> mListaLibros, mLista;
    private ImageButton mOrdenarLibrosAsc, mOrdenarLibrosDesc;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        gestionLibrosViewModel =
                new ViewModelProvider(this).get(Usuario_GestionLibrosViewModel.class);

        binding = FragmentUsuarioLibrosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow2;
        gestionLibrosViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Bundle b = getActivity().getIntent().getExtras();
        token = b.getString("token");
        mListaLibros = new LinkedList<>();
        mLista = new LinkedList<>();
        //Conseguimos un listado de los  libros
        gestionLibrosViewModel.getListaLibros(gestionLibrosViewModel, getContext(), token).observe(getViewLifecycleOwner(), new Observer<Collection>() {
            @Override
            public void onChanged(Collection libros) {
                Iterator it = libros.iterator();
                while (it.hasNext()) {
                    Llibre l = (Llibre) it.next();
                    mLista.add(l.getTitulLlibre());
                    mListaLibros.add("Llibre: " + l.getTitulLlibre());
                }
                // Get a handle to the RecyclerView.
                mRecyclerView = binding.listViewLibros;
                // Create an adapter and supply the data to be displayed.
                mAdaptador = new AdaptadorListaLibros(getContext(), mListaLibros);
                // Connect the adapter with the RecyclerView.
                mRecyclerView.setAdapter(mAdaptador);
                // Give the RecyclerView a default layout manager.
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });

        mOrdenarLibrosAsc = binding.botonOrdenarLibrosAsc;
        //Ordenamos ascendentemente
        mOrdenarLibrosAsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(mLista);
                // Get a handle to the RecyclerView.
                mRecyclerView = binding.listViewLibros;
                // Create an adapter and supply the data to be displayed.
                mAdaptador = new AdaptadorListaLibros(getContext(), mLista);
                // Connect the adapter with the RecyclerView.
                mRecyclerView.setAdapter(mAdaptador);
                // Give the RecyclerView a default layout manager.
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });

        mOrdenarLibrosDesc = binding.botonOrdenarLibrosDesc;
        //ordenamos descendentemente
        mOrdenarLibrosDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(mLista, Collections.reverseOrder());
                // Get a handle to the RecyclerView.
                mRecyclerView = binding.listViewLibros;
                // Create an adapter and supply the data to be displayed.
                mAdaptador = new AdaptadorListaLibros(getContext(), mLista);
                // Connect the adapter with the RecyclerView.
                mRecyclerView.setAdapter(mAdaptador);
                // Give the RecyclerView a default layout manager.
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