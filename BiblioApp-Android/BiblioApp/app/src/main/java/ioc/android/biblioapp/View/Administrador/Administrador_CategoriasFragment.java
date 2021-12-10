/**
 * @Autor Saúl López Díez
 * Clase Administrador_CategoriasFragment con el fragment para gestion de categorias del Administrador
 */


package ioc.android.biblioapp.View.Administrador;

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

import ioc.android.biblioapp.Model.Clases.Categoria;
import ioc.android.biblioapp.ViewModel.Adapter.AdaptadorListaUsuarios;
import ioc.android.biblioapp.ViewModel.Administrador.Administrador_CategoriasViewModel;
import ioc.android.biblioapp.databinding.FragmentAdministradorCategoriasBinding;


public class Administrador_CategoriasFragment extends Fragment {

    private Administrador_CategoriasViewModel administrador_categoriasViewModel;
    private FragmentAdministradorCategoriasBinding binding;
    private RecyclerView mRecyclerView;
    private AdaptadorListaUsuarios mAdaptador;
    private String token;
    private LinkedList<String> mListaCategorias, mLista;
    private ImageButton mOrdenarCategoriasAsc, mOrdenarCategoriasDesc;



    public Administrador_CategoriasFragment() { }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        administrador_categoriasViewModel =
                new ViewModelProvider(this).get(Administrador_CategoriasViewModel.class);

        binding = FragmentAdministradorCategoriasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        administrador_categoriasViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Bundle b = getActivity().getIntent().getExtras();
        token = b.getString("token");
        mListaCategorias = new LinkedList<>();
        mLista = new LinkedList<>();
        //al iniciar el freagment, conseguimos un listado de Categorias
        administrador_categoriasViewModel.getListaCategorias(administrador_categoriasViewModel, getContext(), token).observe(getViewLifecycleOwner(), new Observer<Collection>() {
            @Override
            public void onChanged(Collection cat) {
                Iterator it = cat.iterator();
                while (it.hasNext()) {
                    Categoria a = (Categoria) it.next();
                    mLista.add(a.getNom());
                    mListaCategorias.add(a.getNom());
                }
                mRecyclerView = binding.listViewCategorias;
                mAdaptador = new AdaptadorListaUsuarios(getContext(), mListaCategorias);
                mRecyclerView.setAdapter(mAdaptador);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });

        //listener del boton ordenar ascendente
        mOrdenarCategoriasAsc = binding.botonOrdenarCategoriasAsc;
        mOrdenarCategoriasAsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListaCategorias.clear();
                Collections.sort(mLista);
                mRecyclerView = binding.listViewCategorias;
                mAdaptador = new AdaptadorListaUsuarios(getContext(), mLista);
                mRecyclerView.setAdapter(mAdaptador);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });

        //listener del boton ordenar descendente
        mOrdenarCategoriasDesc = binding.botonOrdenarCategoriasDesc;
        mOrdenarCategoriasDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListaCategorias.clear();
                Collections.sort(mLista, Collections.reverseOrder());
                mRecyclerView = binding.listViewCategorias;
                mAdaptador = new AdaptadorListaUsuarios(getContext(), mLista);
                mRecyclerView.setAdapter(mAdaptador);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });

        mRecyclerView = binding.listViewCategorias;
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