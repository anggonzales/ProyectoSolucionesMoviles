package com.example.appcliente;

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

import java.util.ArrayList;
import java.util.Vector;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {
    private LayoutInflater inflador;
    ArrayList<eSolicitudAceptada> datos;
    Context micontextsolicitud;

    public Adaptador(Context context, ArrayList<eSolicitudAceptada> datos) {
        this.datos = datos;
        micontextsolicitud = context;
        inflador = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflador.inflate(R.layout.miitem, parent, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int i) {
        holder.titulo.setText("Solicitud Nro : " + i);
        holder.costo.setText("Costo : S/ " + datos.get(i).getCosto().toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(micontextsolicitud, "Registrado", Toast.LENGTH_LONG).show();
            }
        });
        holder.miboton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(micontextsolicitud, UnirseRuta.class);
                intent.putExtra("costo", datos.get(i).getCosto());
                intent.putExtra("id", datos.get(i).getId());
                intent.putExtra("latitud", datos.get(i).getLatitud());
                intent.putExtra("longitud" , datos.get(i).getLongitud());
                intent.putExtra("cliente1", datos.get(i).getNombre_cliente_1());
                intent.putExtra("cliente2", datos.get(i).getNombre_cliente_2());
                intent.putExtra("cliente3", datos.get(i).getNombre_cliente_3());
                intent.putExtra("cliente4", datos.get(i).getNombre_cliente_4());
                intent.putExtra("estado", datos.get(i).getEstado());
                micontextsolicitud.startActivity(intent);
            }
        });
        holder.btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(micontextsolicitud, RutaUsuarios.class);
                intent.putExtra("cliente1", datos.get(i).getNombre_cliente_1());
                intent.putExtra("cliente2", datos.get(i).getNombre_cliente_2());
                intent.putExtra("cliente3", datos.get(i).getNombre_cliente_3());
                intent.putExtra("cliente4", datos.get(i).getNombre_cliente_4());
                micontextsolicitud.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return datos.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titulo, costo;
        public ImageView icon;
        public Button miboton;
        public Button btnUser;
        ViewHolder(View itemView) {
            super(itemView);
            titulo = (TextView)itemView.findViewById(R.id.titulo);
            miboton= itemView.findViewById(R.id.btnver);
            costo = itemView.findViewById(R.id.costoservice);
            btnUser = (Button) itemView.findViewById(R.id.btnUsuarios);
        }
    }
}
