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

    private  final RecyclerViewInterface recyclerViewInterface;
    private Context context;
    private ArrayList<Entrada> entradas;

    public AdaptadorRecyclerView(Context context, ArrayList<Entrada> entradas, RecyclerViewInterface recyclerViewInterface){

        this.context = context;
        this.entradas = entradas;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_recycler_view, parent, false);
        return new AdaptadorRecyclerView.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.txtEspanol.setText(entradas.get(position).getEspanol());
        holder.txtIngles.setText(entradas.get(position).getIngles());
    }

    @Override
    public int getItemCount() {
        return entradas.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{

        ImageView imageViewEspana, imageViewReinoUnido;
        TextView txtEspanol, txtIngles;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            imageViewEspana = itemView.findViewById(R.id.imageViewEspana);
            imageViewReinoUnido = itemView.findViewById(R.id.imageViewReinoUnido);
            txtEspanol = itemView.findViewById(R.id.txtEspanol);
            txtIngles= itemView.findViewById(R.id.txtIngles);

            //Añadir listener al item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null){

                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });

        }
    }
}
