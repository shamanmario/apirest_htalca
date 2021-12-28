package com.example.apirest_htalca;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder>{
    List<Publicacion> posteos;

    public Adaptador(List<Publicacion> posteos){
        this.posteos = posteos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.publicacion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.foto.setImageResource(R.drawable.ic_launcher_foreground);
        //holder.foto.setImageURI();
        holder.titulo.setText(posteos.get(position).getTitle());
        holder.id.setText("Publicación N°"+posteos.get(position).getId());
        holder.posteo = posteos.get(position);
    }

    @Override
    public int getItemCount() {
        return posteos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView id;
        private TextView titulo;
        private ImageView foto;
        private Publicacion posteo;

        public ViewHolder(View itemView){
            super(itemView);

            foto = itemView.findViewById(R.id.fotito);
            id = itemView.findViewById(R.id.textoID);
            titulo = itemView.findViewById(R.id.textoTitulo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), posteo.getBody(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
