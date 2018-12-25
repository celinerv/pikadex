import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {
    public static void main(String[] args) throws LoginException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        String token = "NTI3MjE0NjU4MTg1MDY4NTY0.DwQyoA.4_Igf5RMYdCaVF_dhHrzJFHqTac";
        builder.setToken(token);
        builder.addEventListener(new Main());
        builder.buildAsync();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        System.out.println("We received a message from" +
                event.getAuthor().getName() + ":" +
                event.getMessage().getContentDisplay()
        );
        String response = "";


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

        event.getChannel().sendMessage(response).queue();

        /*

        if(event.getMessage().getContentRaw().equals("!ping")) {
            event.getChannel().sendMessage("Pong!").queue();
         }
        if(event.getMessage().getContentRaw().equals("bitch")) {
            event.getChannel().sendMessage("That's not nice").queue();
        }
        */
        }
    }

