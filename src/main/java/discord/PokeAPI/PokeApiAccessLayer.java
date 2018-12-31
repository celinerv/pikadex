package discord.PokeAPI;

import discord.PokeAPI.database.DBClient;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;

public class PokeApiAccessLayer {
    // Client for accessing discord.PokeAPI
    private PokeApiClient pokeApi;

    // Connect to the local discord.PokeAPI.database
    static {
        DBClient.connect();
    }

    // Class instance constructor
    public PokeApiAccessLayer(){
        this.pokeApi = new PokeApiClient();
    }

    public Pokemon searchPokemon(String species){
        int speciesId = DBClient.getId(species);
        return pokeApi.getPokemon(speciesId);
    }
}
