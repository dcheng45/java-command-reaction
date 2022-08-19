package unit.com.drewcheng.cr.funnel;

import com.drewcheng.cr.command.CommandRouter;
import com.drewcheng.cr.funnel.Now;
import com.drewcheng.cr.funnel.NowFunnel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import unit.com.drewcheng.cr.TestCommand;
import unit.com.drewcheng.cr.TestCommandReaction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NowFunnelTest {

    @BeforeAll
    void beforeAll() {
        CommandRouter.clearRoutes();
    }

    @Test
    void givenCommand_whenExecute_thenReturnCommandResponse() {
        TestCommand testCommand = new TestCommand();
        TestCommandReaction testCommandReaction = new TestCommandReaction();
        TestCommandReaction testCommandReactionSpy = spy(testCommandReaction);
        CommandRouter.addRoute(TestCommand.class.getName(), testCommandReactionSpy);
        Now now = new NowFunnel();
        TestCommand.Response response  = now.execute(testCommand);

        assertThat(response).isNotNull();
        assertThat(response.success()).isTrue();

        verify(testCommandReactionSpy).react(testCommand);
        verifyNoMoreInteractions(testCommandReactionSpy);
    }
}
