package com.example.appconductor.Solicitudes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.appconductor.R;

import java.util.Vector;

public class AdaptadorSolicitud extends RecyclerView.Adapter<AdaptadorSolicitud.ViewHolder> {
    private LayoutInflater inflador;
    private Vector<String> lista;

    Context micontext;
    public AdaptadorSolicitud(Context context, Vector<String> lista) {
        this.lista = lista;
        micontext = context;
        inflador = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflador.inflate(R.layout.activity_solicitud, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int i) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                micontext.startActivity(new Intent(micontext , SolicitudMapa.class));
            }
        });
        holder.titulo.setText(lista.get(i));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titulo, subtitulo;
        public ImageView icon;
        ViewHolder(View itemView) {
            super(itemView);
            titulo = (TextView)itemView.findViewById(R.id.id_Destino);
            subtitulo = (TextView)itemView.findViewById(R.id.id_nropasajero);
        }
    }
}
