import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.AccountType;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.security.auth.login.LoginException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    final static String secretsPath = "resources/secrets.json";

    public static void main(String[] args) throws LoginException {

        // Read the auth token for JDA
        JSONParser jsonParser = new JSONParser();
        String token;
        try {
            JSONObject obj = (JSONObject) jsonParser.parse(new FileReader(secretsPath));
            token = (String) obj.get("token");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return;
        }

        JDABuilder builder = new JDABuilder(AccountType.BOT);

        // Authenticate with JDA
        builder.setToken(token);

        // Handle events
        builder.addEventListener(new EventListener());

        // Launch bot
        builder.buildAsync();
    }
}

