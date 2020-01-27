package com.example.appconductor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

public class AdaptadorSolicitud  extends RecyclerView.Adapter<AdaptadorSolicitud.ViewHolder> {
    private LayoutInflater inflador;
    private Vector<String> lista;
    Context micontext;

    String[] items;
    public AdaptadorSolicitud(String[] items) {this.items = items; }

    public AdaptadorSolicitud(Context context, Vector<String> lista) {
        this.lista = lista;
        micontext=context;
        inflador = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflador.inflate(R.layout.mi_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int i) {

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titulo, subtitutlo;
        public ImageView icon;
        public Button miboton;
        ViewHolder(View itemView) {
            super(itemView);
            titulo = (TextView)itemView.findViewById(R.id.id_11);
            subtitutlo = (TextView)itemView.findViewById(R.id.id_21);
            icon = (ImageView)itemView.findViewById(R.id.idimagen1);
        }
    }
}
