package com.oscar.tecmi.actividad4oscarv3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.oscar.tecmi.actividad4oscarv3.models.pokemon;

import java.util.ArrayList;

public class listAdapter extends RecyclerView.Adapter<listAdapter.ViewHolder> {

    private ArrayList<pokemon> dataset;
    private Context context;
    private pokemon p;

    public listAdapter (Context context){
        this.context = context;
        dataset = new ArrayList<>();
    }

    //no sabes que onda con el NonNull

    @NonNull
    @Override
    public listAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull listAdapter.ViewHolder holder, int position) {
        p = dataset.get(position);
        holder.nameTextView.setText(p.getPkName());
        Glide.with(context).load("https://pokeapi.co/media/sprites/pokemon/"+p.getPkNumber()+".png")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.pkImageView);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void addPokemonList(ArrayList<pokemon>pokemonList){
        dataset.addAll(pokemonList);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView pkImageView;
        private TextView nameTextView;
        private CardView cards;

        public ViewHolder (View itemView){
            super(itemView);

            pkImageView = (ImageView) itemView.findViewById(R.id.PkImageView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            cards = (CardView) itemView.findViewById(R.id.cards);
        }

    }
}
