package unit.com.drewcheng.cr.funnel;

import com.drewcheng.cr.command.CommandRouter;
import com.drewcheng.cr.funnel.Now;
import com.drewcheng.cr.funnel.SimpleNow;
import org.junit.jupiter.api.Test;
import unit.com.drewcheng.cr.TestCommand;
import unit.com.drewcheng.cr.TestCommandReaction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class SimpleNowTest {

    @Test
    void givenCommand_whenExecute_thenReturnCommandResponse() {
        TestCommand testCommand = new TestCommand();
        TestCommandReaction testCommandReactionSpy = spy(new TestCommandReaction());
        CommandRouter router = new CommandRouter();
        router.addRoute(TestCommand.class, testCommandReactionSpy);
        Now now = new SimpleNow(router);
        TestCommand.Response response  = now.execute(testCommand);

        assertThat(response).isNotNull();
        assertThat(response.success()).isTrue();

        verify(testCommandReactionSpy).react(testCommand);
        verifyNoMoreInteractions(testCommandReactionSpy);
    }
}
