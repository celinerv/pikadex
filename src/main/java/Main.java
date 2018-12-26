import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.security.auth.login.LoginException;
import java.io.FileReader;
import java.io.IOException;

public class Main extends ListenerAdapter {
    public static void main(String[] args) throws LoginException {

        // Read token from json
        String token;
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("rsrc/secrets.json"));
            JSONObject json = (JSONObject) obj;
            token = (String) json.get("token");
        } catch (IOException | ParseException e){
            e.printStackTrace();
            return;
        }

        // Building the Bot...
        JDABuilder builder = new JDABuilder(AccountType.BOT);

        // Authenticate
        builder.setToken(token);

        // Attach Event Listener
        builder.addEventListener(new EventListener());

        // Build the bot
        builder.buildAsync();
    }
}

