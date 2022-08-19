package unit.com.drewcheng.cr.command;

import com.drewcheng.cr.command.CommandRouter;
import com.drewcheng.cr.funnel.Now;
import com.drewcheng.cr.funnel.NowFunnel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unit.com.drewcheng.cr.TestCommand;
import unit.com.drewcheng.cr.TestCommandReaction;

import static org.assertj.core.api.Assertions.assertThat;

public class CommandTest {

    @BeforeEach
    void beforeEach() {
        CommandRouter.clearRoutes();
    }

    @Test
    void givenFunnel_whenExecute_thenReturnCommandResponse() {
        TestCommand testCommand = new TestCommand();
        TestCommandReaction testCommandReaction = new TestCommandReaction();
        CommandRouter.addRoute(TestCommand.class.getName(), testCommandReaction);
        Now now = new NowFunnel();

        TestCommand.Response response = testCommand.execute(now);

        assertThat(response).isNotNull();
        assertThat(response.success()).isTrue();
    }
}
