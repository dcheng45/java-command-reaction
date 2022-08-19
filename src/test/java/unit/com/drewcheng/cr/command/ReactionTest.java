package unit.com.drewcheng.cr.command;

import com.drewcheng.cr.command.CommandResponse;
import org.junit.jupiter.api.Test;
import unit.com.drewcheng.cr.TestCommand;
import unit.com.drewcheng.cr.TestCommandReaction;

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
}
