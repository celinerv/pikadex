import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class EventListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        System.out.println("We received a message from " +
                event.getAuthor().getName() + ":\t" +
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
