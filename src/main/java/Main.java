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

    final static String secretsPath = "resources/secrets.json";

    public static void main(String[] args) throws LoginException {

        // Read the auth token for JDA
        JSONParser jsonParser = new JSONParser();
        String token;
        try {
            JSONObject obj = (JSONObject)jsonParser.parse(new FileReader(secretsPath));
            token = (String) obj.get("token");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return;
        }

        JDABuilder builder = new JDABuilder(AccountType.BOT);

        // Authenticate with JDA
        builder.setToken(token);

        // Handle events
        builder.addEventListener(new Main());

        // Launch bot
        builder.buildAsync();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        System.out.println("We received a message from" +
                event.getAuthor().getName() + ":" +
                event.getMessage().getContentDisplay()
        );

        String response = null;
        switch(event.getMessage().getContentRaw()){
            case "!ping":
                response = "Pong!";
                break;
            case "bitch":
                response = "That's not nice";
                break;
            default:
                //do nothing
                break;
        }

        if (response == null) return; //do nothing
        else event.getChannel().sendMessage(response).queue();
        }
    }

