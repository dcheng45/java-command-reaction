package unit.com.drewcheng.cr.command;

import com.drewcheng.cr.command.CommandRouter;
import com.drewcheng.cr.funnel.Now;
import com.drewcheng.cr.funnel.NowFunnel;
import org.junit.jupiter.api.Test;
import unit.com.drewcheng.cr.TestCommand;
import unit.com.drewcheng.cr.TestCommandReaction;

import static org.assertj.core.api.Assertions.assertThat;

public class CommandTest {

    @Test
    void givenFunnel_whenExecute_thenReturnCommandResponse() {
        TestCommand testCommand = new TestCommand();
        TestCommandReaction testCommandReaction = new TestCommandReaction();
        CommandRouter router = new CommandRouter();
        router.addRoute(TestCommand.class, testCommandReaction);
        Now now = new NowFunnel(router);

        TestCommand.Response response = testCommand.execute(now);

        assertThat(response).isNotNull();
        assertThat(response.success()).isTrue();
    }
}
