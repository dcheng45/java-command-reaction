package com.drewcheng.cr.command;

import com.drewcheng.cr.TestCommand;
import com.google.common.reflect.TypeToken;
import org.junit.jupiter.api.Test;
import com.drewcheng.cr.TestCommandReaction;

import static org.assertj.core.api.Assertions.assertThat;

public class ReactionTest {

    @Test
    void givenCommand_whenReact_thenReturnCommandResponse() {
        TestCommand testCommand = new TestCommand();
        TestCommandReaction testCommandReaction = new TestCommandReaction();

        CommandResponse response = testCommandReaction.react(testCommand);

        assertThat(response).isNotNull();
        assertThat(response.getClass()).isEqualTo(TestCommand.Response.class);
    }

    @Test
    void whenCommandType_thenReturnReactionCommand() {
        TestCommandReaction testCommandReaction = new TestCommandReaction();

        assertThat(testCommandReaction.commandType()).isEqualTo(TypeToken.of(TestCommand.class));
    }
}
