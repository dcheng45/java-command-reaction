package unit.com.drewcheng.cr.command;

import com.drewcheng.cr.command.Command;
import com.drewcheng.cr.command.CommandResponse;
import com.drewcheng.cr.command.CommandRouter;
import com.drewcheng.cr.command.Reaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import unit.com.drewcheng.cr.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CommandRouterTest {

    TestCommand testCommand = new TestCommand();
    TestCommandReaction testCommandReaction = new TestCommandReaction();

    @AfterEach
    void afterEach() {
        CommandRouter.clearRoutes();
    }

    @Test
    void givenExistingCommand_whenRoute_thenReturnReaction() {
        CommandRouter.addRoute(TestCommand.class.getName(), testCommandReaction);
        Reaction<Command<CommandResponse>, CommandResponse> reaction = CommandRouter.route(TestCommand.class.getName());

        assertThat(reaction).isNotNull();
        assertThat(reaction.getClass()).isEqualTo(TestCommandReaction.class);
    }

    @Test
    void givenNonExistingCommand_whenRoute_thenThrowNoReactionError() {
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> CommandRouter.route(TestCommand.class.getName()))
                .withMessage("Reaction not found for command: " + TestCommand.class.getName());
    }

    @Test
    void givenExistingCommand_whenAddRoute_thenThrowAlreadyExistsError() {
        CommandRouter.addRoute(TestCommand.class.getName(), testCommandReaction);
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> CommandRouter.addRoute(TestCommand.class.getName(), testCommandReaction))
                .withMessage("Reaction already exists for command: " + TestCommand.class.getName());
    }
}
