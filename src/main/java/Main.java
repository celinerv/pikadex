// DISCORD JDA
import discord.EventListener;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.AccountType;

// GOOGLE JSON PARSER
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.security.auth.login.LoginException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws LoginException {

        // Read token from json
        String discordToken;
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("rsrc/secrets.json"));
            JSONObject json = (JSONObject) obj;

            // Typecast the result to a String object
            discordToken = (String) json.get("token");

        } catch (IOException | ParseException e){
            // Print out exception to error console
            e.printStackTrace();
            return;
        }

        JDABuilder builder = new JDABuilder(AccountType.BOT);
        // Building the bot...

        // Authenticate
        builder.setToken(discordToken);

        // Attach an discord.EventListener object
        EventListener el = new EventListener();
        builder.addEventListener(el);

        // Build the bot
        builder.buildAsync();
    }
}

