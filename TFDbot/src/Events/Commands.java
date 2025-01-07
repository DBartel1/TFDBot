package Events;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

public class Commands extends ListenerAdapter {
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        Guild guild = event.getJDA().getGuildById("1170563430202232872");
        if (guild != null) {
            guild.upsertCommand("profile", "Gives basic character information").addOptions(
                    new OptionData(OptionType.STRING, "playername", "enter player name", true)
            ).queue();
        }

    }
}