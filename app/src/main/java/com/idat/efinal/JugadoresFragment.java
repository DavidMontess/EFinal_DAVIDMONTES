package com.idat.efinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.idat.efinal.network.JugadorEntry;

public class JugadoresFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ){
        View view= inflater.inflate(R.layout.jugadores_futbol,container,false);

        //ejecutar el boton editar perfil
        MaterialButton nextButton= view.findViewById(R.id.perfil_edit);

        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ((Navigationhost) getActivity()).navigateTo(new PerfilFragment(),false);
            }
        });

        /*setUpToolBar(view);

        RecyclerView recyclerView=view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2,GridLayoutManager.VERTICAL, false));
        JugadorCardRecyclerViewAdapter adapter= new JugadorCardRecyclerViewAdapter(
                JugadorEntry.initJugadorEntryList(getResources()));
        recyclerView.setAdapter(adapter);
        int largePadding=getResources().getDimensionPixelSize(R.dimen.jugador_spacing);
        int smallPadding=getResources().getDimensionPixelSize(R.dimen.jugador_spacing_small);
        recyclerView.addItemDecoration(new JugadorItemDecoration(largePadding,smallPadding));*/
        return view;
    }

    private void setUpToolBar(View view){
        Toolbar toolbar=view.findViewById(R.id.app_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if(activity!=null){
            activity.setSupportActionBar(toolbar);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater){
        menuInflater.inflate(R.menu.toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }


}
