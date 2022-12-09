package space.parzival.discord.shared.base.fake;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import space.parzival.discord.shared.base.exceptions.CommandExecutionException;
import space.parzival.discord.shared.base.types.Command;

@Slf4j
public class FakeCommand extends Command {

    public FakeCommand(String name, String description, List<OptionData> options) {
        super(name, description, options);
    }

    @Override
    public void execute(JDA client, SlashCommandInteractionEvent event, InteractionHook hook)
            throws CommandExecutionException {

        hook.sendMessage("done");
        
        log.info("Executing fake command: {}", toString());
    }
    
}
