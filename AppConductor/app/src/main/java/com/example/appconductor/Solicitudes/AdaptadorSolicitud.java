package com.example.appconductor.Solicitudes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.appconductor.R;

import java.util.ArrayList;

public class AdaptadorSolicitud extends RecyclerView.Adapter<AdaptadorSolicitud.ViewHolder> {
    private LayoutInflater inflador;
    ArrayList<eSolicitud> datos;
    Context micontextsolicitud;

    public AdaptadorSolicitud(Context context, ArrayList<eSolicitud> datos) {
        this.datos = datos;
        micontextsolicitud = context;
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
        holder.titulo.setText("Solicitud Nro : " + i);
        //holder.subtitulo.setText(datos.get(i).getLatitud());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(micontextsolicitud, "Registrado", Toast.LENGTH_LONG).show();
            }
        });
        holder.btnDestino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(micontextsolicitud, SolicitudMapa.class);
                intent.putExtra("cliente", datos.get(i).getNombre_cliente());
                intent.putExtra("latitud", datos.get(i).getLatitud());
                intent.putExtra("longitud" , datos.get(i).getLongitud());
                intent.putExtra("id", datos.get(i).getId());
                micontextsolicitud.startActivity(intent);
            }
        });
        holder.btnUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                micontextsolicitud.startActivity(new Intent(micontextsolicitud, DetalleSolicitud.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titulo, subtitulo;
        public ImageView icon;
        public Button btnUsuarios;
        public Button btnDestino;

        ViewHolder(View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.id_Destino);
            subtitulo = (TextView) itemView.findViewById(R.id.id_nropasajero);
            btnDestino = (Button) itemView.findViewById(R.id.btnVer);
            btnUsuarios = (Button) itemView.findViewById(R.id.btnUsuarios);
        }
    }
}
