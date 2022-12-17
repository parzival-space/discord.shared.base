package space.parzival.discord.shared.base.utils;

import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import space.parzival.discord.shared.base.types.Command;

@Slf4j
public class Commands {
    
    private Commands() {}

    /**
     * Registers multiple commands.
     * @param commands 
     * @param client
     */
    public static void registerCommands(List<? extends Command> commands, JDA client) {
        List<SlashCommandData> commandInfos = commands.stream()
            .map(Command::toSlashCommandData)
            .collect(Collectors.toList());
        
        if (commandInfos != null) {
            client.updateCommands().addCommands(commandInfos).queue();
            commands.forEach(c -> c.registerCommandListener(client));

            log.info("Registered {} commands.", commandInfos.size());
        } else {
            log.warn("Failed to load command! Please look into this error.");
        }
    }
}
