package com.idat.efinal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.idat.efinal.network.ImagenRequest;
import com.idat.efinal.network.JugadorEntry;

import java.util.List;

public class JugadorCardRecyclerViewAdapter extends RecyclerView.Adapter<JugadorCardViewHolder> {

    private List<JugadorEntry> jugadorList;
    private ImagenRequest imagenRequest;

    JugadorCardRecyclerViewAdapter(List<JugadorEntry> jugadorlist){
        this.jugadorList=jugadorlist;
        imagenRequest=ImagenRequest.getInstance();

    }

    @NonNull
    @Override
    public JugadorCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView= LayoutInflater.from(parent.getContext()).inflate(R.layout.jugador_card, parent,false);

        return new JugadorCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull JugadorCardViewHolder holder, int position) {
        if(jugadorList!=null && position < jugadorList.size()){
            JugadorEntry jugador= jugadorList.get(position);
            holder.jugadorName.setText(jugador.nombre);
            imagenRequest.setImageFromUrl(holder.JugadorImage, jugador.foto);
        }
    }

    @Override
    public int getItemCount() {
        return jugadorList.size();
    }
}
