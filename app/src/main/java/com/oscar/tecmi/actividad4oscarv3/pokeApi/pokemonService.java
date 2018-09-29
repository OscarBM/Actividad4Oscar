package com.oscar.tecmi.actividad4oscarv3.pokeApi;

import com.oscar.tecmi.actividad4oscarv3.models.pokemonAnswer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface pokemonService {
    @GET("pokemon")
    Call<pokemonAnswer> getPokemonList(@Query("limit")int limit, @Query("offset")int offset);
    //Call<pokemonAnswer> getPokemonList(@Query("limit")int limit, @Query("offset")int offset);
}
