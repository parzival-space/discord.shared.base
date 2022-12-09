package space.parzival.discord.shared.base.types;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import space.parzival.discord.shared.base.exceptions.CommandExecutionException;

@Slf4j
@ToString
public abstract class Command {

    /**
     * The name of this command.
     */
    @Getter
    protected @Nonnull String name = "";

    /**
     * A description of this command.
     */
    @Getter
    protected @Nonnull String description = "";

    /**
     * A list of all arguments for this Command.
     */
    @Getter
    protected @Nonnull List<OptionData> options = new ArrayList<>();

    /**
     * Wether or not the class should automatically set the "is thinking state".
     * By default, the type will automatically accept and incoming commands.
     */
    protected boolean autoDefer = true;

    /**
     * The base class of a command.
     * @param name The name of the command.
     * @param description The description of the command.
     * @param options Options for the command.
     */
    protected Command(@Nonnull String name, @Nonnull String description, @Nullable List<OptionData> options) {
        this.name = name;
        this.description = description;

        if (options != null) {
            this.options = options;
        }
    }

    /**
     * Registers the command to the specified {@see JDA}-Bot instance.
     * @param client The Client-Instance.
     */
    public void registerCommandListener(JDA client) {
        // listen for execution event
        client.addEventListener(new ListenerAdapter() {
            @Override
            public void onSlashCommandInteraction(@Nonnull SlashCommandInteractionEvent event) {
                // check if the executed command is handled by this class
                if (!event.getName().equals(name)) return;

                // set the response to: 'thinking...'
                event.deferReply().queue();
                InteractionHook hook = event.getHook();

                // execute the command isolated
                try {
                    execute(client, event, hook);
                } catch (Exception err) {
                    hook.sendMessage("Sorry, it looks like something went wrong. Please contact the bot author.").queue();
                    log.error("Failed to execute command '{}'", name);
                    err.printStackTrace();
                }
            }
        });
        
        log.debug("Registered new command '{}' with {} options.", this.name, this.options.size());
    }
    
    /**
     * Executes the command.
     * @param client The Client-Instance.
     * @param event The triggering event.
     * @param hook The message hook. Use this instead of directly replying with .reply()!
     */
    public abstract void execute(JDA client, SlashCommandInteractionEvent event, InteractionHook hook) throws CommandExecutionException;


    public @Nonnull SlashCommandData toSlashCommandData() {
        return Commands.slash(this.name, this.description)
            .addOptions(this.options);
    }
}
