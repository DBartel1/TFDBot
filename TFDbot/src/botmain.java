import Events.Commands;
import Events.profileSlashCommand;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.JDA;


//Class to initialize the discord bot and commands for the bot.
public class botmain {

        public static void main(String[] args) {
                //Insert Discord API token below
                JDABuilder jdaBuilder = JDABuilder.createDefault("");
                    jdaBuilder.setActivity(Activity.playing("The First Descendant"));
                    jdaBuilder.addEventListeners(new Commands());
                    jdaBuilder.addEventListeners(new profileSlashCommand());

                JDA jda = jdaBuilder.build();




        }
}
