/**
 * @Autor Saúl López Díez
 * Clase AdaptadorListaLibros con el adaptador de la lista de libros
 */

package ioc.android.biblioapp.ViewModel.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

import ioc.android.biblioapp.R;
import ioc.android.biblioapp.ViewModel.Administrador.Administrador_GestionLibrosViewModel;

public class AdaptadorListaLibros extends
        RecyclerView.Adapter<AdaptadorListaLibros.LibrosViewHolder>{

    private final LinkedList<String> mListaLibros;
    private LayoutInflater mInflater;
    private int posicionClick, posicionAnterior=-1;
    String elementAnterior=null;


    public AdaptadorListaLibros(Context context,
                                LinkedList<String> mListaLibros) {
        mInflater = LayoutInflater.from(context);
        this.mListaLibros = mListaLibros;
    }

    @NonNull
    @Override
    public AdaptadorListaLibros.LibrosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.lista_libros,
                parent, false);
        return new LibrosViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorListaLibros.LibrosViewHolder holder, int position) {

        String mCurrent = mListaLibros.get(position);
        holder.libroItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mListaLibros.size();
    }

    class LibrosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public final TextView libroItemView;
        final AdaptadorListaLibros mAdaptador;

        public LibrosViewHolder(View itemView, AdaptadorListaLibros adapter) {
            super(itemView);
            libroItemView = itemView.findViewById(R.id.libro);
            this.mAdaptador = adapter;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {

            int mPosition = getLayoutPosition();
            if(posicionAnterior==-1){
                posicionAnterior=getLayoutPosition();
                elementAnterior= mListaLibros.get(mPosition);
            }else{
                mListaLibros.set(posicionAnterior, elementAnterior);
            }
            String element = mListaLibros.get(mPosition);
            Administrador_GestionLibrosViewModel.setTituloLibroDetalle(element);
            elementAnterior=mListaLibros.get(mPosition);
            mListaLibros.set(mPosition,"SELECCIONADO: "+element);
            view.requestFocus();
            posicionAnterior=mPosition;

            mAdaptador.notifyDataSetChanged();
        }
    }

}
