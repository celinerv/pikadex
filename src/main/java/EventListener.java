import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

// The EventListener listens for and reacts to messages sent to the channel.
public class EventListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event){

        // Log information to the console
        System.out.println("We received a message from " +
                event.getAuthor().getName() + ":\t\t" +
                event.getMessage().getContentDisplay()
        );

        // Construct the response
        String response = null;

        //TODO parse message for tokens based on supported commands instead of using a switch statement
        switch(event.getMessage().getContentRaw()){
            case "!ping":
                response = "Pong!";
                break;
            case "bitch":
                response = "That's not nice";
                break;
        }

        if (response == null) {
            return; // do nothing
        } else {
            event.getChannel().sendMessage(response).queue(); // send the message
        }
    }
}
