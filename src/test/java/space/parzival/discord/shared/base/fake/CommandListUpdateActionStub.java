package space.parzival.discord.shared.base.fake;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.exceptions.RateLimitedException;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

@Slf4j
public class CommandListUpdateActionStub implements net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction {

    @Override
    public List<Command> complete(boolean arg0) throws RateLimitedException {
        // not covered by stub
        return null;
    }

    @Override
    public JDA getJDA() {
        // not covered by stub
        return null;
    }

    @Override
    public void queue(Consumer<? super List<Command>> arg0, Consumer<? super Throwable> arg1) {
        // not covered by stub
    }

    @Override
    public CompletableFuture<List<Command>> submit(boolean arg0) {
        // not covered by stub
        return null;
    }

    @Override
    public CommandListUpdateAction addCheck(BooleanSupplier arg0) {
        return this;
    }

    @Override
    public CommandListUpdateAction addCommands(Collection<? extends CommandData> arg0) {

        for (CommandData commandData : arg0) {
            log.info("Simulating command registration for command '{}'", commandData.getName());
        }

        return this;
    }

    @Override
    public CommandListUpdateAction deadline(long arg0) {
        return this;
    }

    @Override
    public CommandListUpdateAction setCheck(BooleanSupplier arg0) {
        return this;
    }

    @Override
    public CommandListUpdateAction timeout(long arg0, TimeUnit arg1) {
        return this;
    }

    
}
