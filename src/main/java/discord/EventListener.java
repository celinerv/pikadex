package discord;

import discord.PokeAPI.PokeApiAccessLayer;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/*
 * The discord EventListener listens for and reacts to messages sent to the channel
 */
public class EventListener extends ListenerAdapter {

    private PokeApiAccessLayer pokeApi;

    public EventListener(){
        this.pokeApi = new PokeApiAccessLayer();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event){

        // Log information to the console
        System.out.println("We received a message from " +
                event.getAuthor().getName() + ":\t\t" +
                event.getMessage().getContentDisplay()
        );

        if (event.getAuthor().getName().equals("Pikadex")) return; //don't respond to itself

        // Construct the response...
        String response = null;

        //TODO Revise current behavior to accept supported commands
        //Todo Bot currently sends a picture of the pokemon
        String msg = event.getMessage().getContentRaw();
        Pokemon p = pokeApi.searchPokemon(msg);
        if (p == null) response = "Pokemon not found!";
        else response = p.getSprites().getFrontDefault();

        /*
        switch(msg) {
            case "!ping":
                response = "Pong!";
                break;
            case "bitch":
                response = "That's not nice";
                break;
            default:
                Pokemon p = pokeApi.searchPokemon(msg);
                if (p == null) response = "Pokemon not found!";
                else response = p.getSprites().getFrontDefault();
                break;
        }
        */

        if (response == null) {
            return; // do nothing
        } else {
            if (response.length() > 2000) response = response.substring(0, 2000);
            event.getChannel().sendMessage(response).queue(); // send the message
        }
    }
}
