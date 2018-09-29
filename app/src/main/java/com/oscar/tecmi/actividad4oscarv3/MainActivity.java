package com.oscar.tecmi.actividad4oscarv3;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.oscar.tecmi.actividad4oscarv3.models.pokemon;
import com.oscar.tecmi.actividad4oscarv3.models.pokemonAnswer;
import com.oscar.tecmi.actividad4oscarv3.pokeApi.pokemonService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private static final String TAG = "POKEDEX";

    private RecyclerView recyclerView;
    private listAdapter listAdapter;

    private int offset;
    private boolean ableToLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        listAdapter = new listAdapter(this);

        recyclerView.setAdapter(listAdapter);
        recyclerView.setHasFixedSize(true);

        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(dy < 0){
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if(ableToLoad){
                        if((visibleItemCount + pastVisibleItems) >= totalItemCount){
                            Log.i(TAG, "Llegamos al final");
                            ableToLoad = false;
                            offset += 20;
                            getData(offset);
                        }
                    }
                }
            }
        });



        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ableToLoad = true;
        offset = 0;
        getData(offset);

    }

    private void getData(int offset){
        pokemonService service = retrofit.create(pokemonService.class);
        Call<pokemonAnswer>pokemonAnswerCall = service.getPokemonList(20, offset);



        pokemonAnswerCall.enqueue(new Callback<pokemonAnswer>() {
            @Override
            public void onResponse(Call<pokemonAnswer> call, Response<pokemonAnswer> response) {
                ableToLoad = true;
                if(response.isSuccessful()){
                    pokemonAnswer pokemonAnswer = response.body();
                    ArrayList<pokemon> pokemonList = pokemonAnswer.getResults();
                    listAdapter.addPokemonList(pokemonList);

                    for(int i = 0; i < pokemonList.size(); i++){
                        pokemon p = pokemonList.get(i);
                        Log.i(TAG, "Pokemon: " + p.getPkName());
                    }
                }
                else {
                    Log.e(TAG, "No response "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<pokemonAnswer> call, Throwable t) {
                ableToLoad = true;
                Log.e(TAG, "On failure " + t.getMessage());
            }
        });
    }
}
