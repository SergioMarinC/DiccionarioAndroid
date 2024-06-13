package com.example.diccionario.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diccionario.Modelo.Entrada;
import com.example.diccionario.R;
import com.example.diccionario.RecyclerViewInterface;

import java.util.ArrayList;

public class AdaptadorRecyclerView extends RecyclerView.Adapter<AdaptadorRecyclerView.MyViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface; // Interfaz para manejar eventos de clic
    private Context context;
    private ArrayList<Entrada> entradas; // Lista de entradas a mostrar

    // Constructor del adaptador
    public AdaptadorRecyclerView(Context context, ArrayList<Entrada> entradas, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.entradas = entradas;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    // Método llamado cuando se necesita crear una nueva vista para un elemento de la lista
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_recycler_view, parent, false); // Inflar el layout del elemento de la lista
        return new MyViewHolder(view, recyclerViewInterface); // Devolver una instancia de MyViewHolder
    }

    // Método llamado para mostrar datos en una posición específica
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Obtener la entrada en la posición dada
        Entrada entrada = entradas.get(position);
        // Establecer los datos en los elementos de la vista (item)
        holder.txtEspanol.setText(entrada.getEspanol());
        holder.txtIngles.setText(entrada.getIngles());
        // Aquí podrías cargar imágenes u otros datos necesarios
    }

    // Método que devuelve el número total de elementos en la lista
    @Override
    public int getItemCount() {
        return entradas.size();
    }

    // Clase interna para representar cada elemento de la lista
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewEspana, imageViewReinoUnido;
        TextView txtEspanol, txtIngles;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            // Obtener referencias a los elementos de la vista (item)
            imageViewEspana = itemView.findViewById(R.id.imageViewEspana);
            imageViewReinoUnido = itemView.findViewById(R.id.imageViewReinoUnido);
            txtEspanol = itemView.findViewById(R.id.txtEspanol);
            txtIngles = itemView.findViewById(R.id.txtIngles);

            // Añadir un listener al item de la lista
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Verificar si la interfaz está inicializada y no es nula
                    if (recyclerViewInterface != null) {
                        // Obtener la posición del elemento clicado
                        int pos = getAdapterPosition();
                        // Verificar si la posición es válida
                        if (pos != RecyclerView.NO_POSITION) {
                            // Llamar al método onItemClick definido en la interfaz, pasando la posición
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
