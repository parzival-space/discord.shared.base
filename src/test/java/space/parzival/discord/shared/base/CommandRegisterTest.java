package space.parzival.discord.shared.base;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.internal.entities.GuildImpl;
import net.dv8tion.jda.internal.requests.Route.CompiledRoute;
import space.parzival.discord.shared.base.fake.CommandListUpdateActionStub;
import space.parzival.discord.shared.base.fake.FakeCommand;
import space.parzival.discord.shared.base.types.Command;
import space.parzival.discord.shared.base.utils.Commands;

@Slf4j
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CommandRegisterTest {

	@Mock
	JDA client;

	@Mock
	GuildImpl guildImpl;

	@Mock
	CompiledRoute compiledRoute;

	@Test
	void commandRegisterTest() {
		prepareClientMock();
		log.info("Using fake token: {}", client.getToken());

		// register commands
		List<Command> commands = createFakeCommands(10);
		assertDoesNotThrow(() -> Commands.registerCommands(commands, client));
	}

	// prepare mockito
	private void prepareClientMock() {
		when(client.getToken()).thenReturn("XxxxXxX0XXX0XXXxXxX0XXX0.X00X00.xX0X0x0XXxXxXXXXXxXXXXx00xx"); // took me way to long to create this...

		// allow command tests
		when(client.updateCommands()).thenReturn(new CommandListUpdateActionStub());
	}

	// create some fake commands
	private List<Command> createFakeCommands(int amount) {
		List<Command> commands = new ArrayList<>();

		for (int i = 0; i < amount; i++) {
			commands.add(new FakeCommand("fake" +i, "Command #" + i, null));
		}

		return commands;
	}

}
