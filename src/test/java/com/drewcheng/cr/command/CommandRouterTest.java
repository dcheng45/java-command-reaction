package com.drewcheng.cr.command;

import com.drewcheng.cr.TestCommand;
import com.drewcheng.cr.TestCommandReaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CommandRouterTest {

    TestCommandReaction testCommandReaction = new TestCommandReaction();

    @Test
    void givenExistingCommand_whenRoute_thenReturnReaction() {
        CommandRouter router = new CommandRouter();
        router.addRoute(TestCommand.class, testCommandReaction);
        Reaction<Command<CommandResponse>, CommandResponse> reaction = router.route(TestCommand.class);

        assertThat(reaction).isNotNull();
        assertThat(reaction.getClass()).isEqualTo(TestCommandReaction.class);
    }

    @Test
    void givenNonExistingCommand_whenRoute_thenThrowNoReactionError() {
        CommandRouter router = new CommandRouter();
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> router.route(TestCommand.class))
                .withMessage("Reaction not found for command: " + TestCommand.class.getName());
    }

    @Test
    void givenExistingCommand_whenAddRoute_thenThrowAlreadyExistsError() {
        CommandRouter router = new CommandRouter();
        router.addRoute(TestCommand.class, testCommandReaction);
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> router.addRoute(TestCommand.class, testCommandReaction))
                .withMessage("Reaction already exists for command: " + TestCommand.class.getName());
    }
}
