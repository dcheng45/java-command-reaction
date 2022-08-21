package unit.com.drewcheng.cr.funnel;

import com.drewcheng.cr.command.CommandRouter;
import com.drewcheng.cr.funnel.Future;
import com.drewcheng.cr.funnel.SimpleFuture;
import org.junit.jupiter.api.Test;
import unit.com.drewcheng.cr.TestCommand;
import unit.com.drewcheng.cr.TestCommandReaction;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class SimpleFutureTest {

    @Test
    void givenCommand_whenExecute_thenRunAsynchronously() {
        TestCommand testCommand = new TestCommand();
        TestCommandReaction testCommandReaction = new TestCommandReaction();
        CommandRouter router = new CommandRouter();
        router.addRoute(TestCommand.class, testCommandReaction);
        Future future = new SimpleFuture(router);

        CompletableFuture<TestCommand.Response> response = future.execute(testCommand);

        assertThat(response.isDone()).isFalse();
        pauseSeconds(1);
        assertThat(response.isDone()).isTrue();
    }

    @Test
    void givenCommand_whenExecute_thenReturnCommandResponse() {
        TestCommand testCommand = new TestCommand();
        TestCommandReaction testCommandReactionSpy = spy(new TestCommandReaction());
        CommandRouter router = new CommandRouter();
        router.addRoute(TestCommand.class, testCommandReactionSpy);
        Future future = new SimpleFuture(router);

        CompletableFuture<TestCommand.Response> response = future.execute(testCommand);

        try {
            assertThat(response.get().success()).isTrue();
        } catch (InterruptedException | ExecutionException e) {
            fail();
        }

        verify(testCommandReactionSpy).react(testCommand);
        verifyNoMoreInteractions(testCommandReactionSpy);
    }

    private void pauseSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
