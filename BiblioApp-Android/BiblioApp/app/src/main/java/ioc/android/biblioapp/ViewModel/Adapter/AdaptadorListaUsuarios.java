/**
 * @Autor Saúl López Díez
 * Clase AdaptadorListaUsuarios con el adaptador de la lista de usuarios
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
import ioc.android.biblioapp.ViewModel.Administrador.Administrador_AutoresViewModel;
import ioc.android.biblioapp.ViewModel.Administrador.Administrador_GestionUsuariosViewModel;

public class AdaptadorListaUsuarios extends
        RecyclerView.Adapter<AdaptadorListaUsuarios.UsuariosViewHolder> {

    private final LinkedList<String> mListaUsuarios;
    private LayoutInflater mInflater;
    private int posicionClick, posicionAnterior=-1;
    String elementAnterior=null;

    public AdaptadorListaUsuarios(Context context,
                                  LinkedList<String> mListaUsuarios) {
        mInflater = LayoutInflater.from(context);
        this.mListaUsuarios = mListaUsuarios;
    }

    @NonNull
    @Override
    public AdaptadorListaUsuarios.UsuariosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.lista_usarios,
                parent, false);
        return new UsuariosViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorListaUsuarios.UsuariosViewHolder holder, int position) {
        String mCurrent = mListaUsuarios.get(position);
        holder.usuarioItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mListaUsuarios.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public String getItemIdNombre () {
        long i= getItemId(posicionClick);
        String element = mListaUsuarios.get((int) i);
        return element;
    }

    class UsuariosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView usuarioItemView;
        final AdaptadorListaUsuarios mAdaptador;

        public UsuariosViewHolder(View itemView, AdaptadorListaUsuarios adapter) {
            super(itemView);
            usuarioItemView = itemView.findViewById(R.id.usuario);
            this.mAdaptador = adapter;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

            int mPosition = getLayoutPosition();
            if(posicionAnterior==-1){
                posicionAnterior=getLayoutPosition();
                elementAnterior= mListaUsuarios.get(mPosition);
            }else{
                mListaUsuarios.set(posicionAnterior, elementAnterior);
            }
            String element = mListaUsuarios.get(mPosition);
            Administrador_GestionUsuariosViewModel.setNombreUsuarioDetalle(element);
            Administrador_AutoresViewModel.setNombreAutorDetalle(element);
            elementAnterior=mListaUsuarios.get(mPosition);
            mListaUsuarios.set(mPosition,"SELECCIONADO: "+element);
            view.requestFocus();
            posicionAnterior=mPosition;
            mAdaptador.notifyDataSetChanged();
        }
    }


}
