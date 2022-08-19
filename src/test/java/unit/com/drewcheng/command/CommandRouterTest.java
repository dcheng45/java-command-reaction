package unit.com.drewcheng.command;

import com.drewcheng.command.Command;
import com.drewcheng.command.CommandResponse;
import com.drewcheng.command.CommandRouter;
import com.drewcheng.command.Reaction;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class CommandRouterTest {

    TestCommand testCommand = new TestCommand();
    TestCommandReaction testCommandReaction = new TestCommandReaction();
    FakeCommand fakeCommand = new FakeCommand();
    DuplicateCommand duplicateCommand = new DuplicateCommand();
    DuplicateCommandReaction duplicateCommandReaction = new DuplicateCommandReaction();

    @Test
    void givenExistingCommand_whenRoute_thenReturnReaction() {
        CommandRouter.addRoute(testCommand.getClass().getName(), testCommandReaction);
        Reaction<Command<CommandResponse>, CommandResponse> reaction = CommandRouter.route(testCommand.getClass().getName());

        assertThat(reaction).isNotNull();
        assertThat(reaction.getClass()).isEqualTo(TestCommandReaction.class);
    }

    @Test
    void givenNonExistingCommand_whenRoute_thenThrowNoReactionError() {
        assertThatExceptionOfType(IllegalStateException.class).isThrownBy(() -> {
            CommandRouter.route(fakeCommand.getClass().getName());
        }).withMessage("Reaction not found for command: " + fakeCommand.getClass().getName());
    }

    @Test
    void givenExistingCommand_whenAddRoute_thenThrowAlreadyExistsError() {
        CommandRouter.addRoute(duplicateCommand.getClass().getName(), duplicateCommandReaction);
        assertThatExceptionOfType(IllegalStateException.class).isThrownBy(() -> {
            CommandRouter.addRoute(duplicateCommand.getClass().getName(), duplicateCommandReaction);
        }).withMessage("Reaction already exists for command: " + duplicateCommand.getClass().getName());
    }
}
