package com.idat.efinal;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;

public class JugadorCardViewHolder extends RecyclerView.ViewHolder {
    public NetworkImageView JugadorImage;
    public TextView jugadorName;

    public JugadorCardViewHolder(@NonNull View ItemView){
        super(ItemView);
        jugadorName=ItemView.findViewById(R.id.jugador_name);
        JugadorImage=ItemView.findViewById(R.id.jugador_image);
    }
}
