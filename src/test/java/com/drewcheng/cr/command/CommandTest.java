package com.drewcheng.cr.command;

import com.drewcheng.cr.TestCommand;
import com.drewcheng.cr.TestCommandReaction;
import com.drewcheng.cr.funnel.Future;
import com.drewcheng.cr.funnel.Now;
import com.drewcheng.cr.funnel.SimpleFuture;
import com.drewcheng.cr.funnel.SimpleNow;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class CommandTest {

    @Test
    void givenNowFunnel_whenExecute_thenReturnCommandResponse() {
        TestCommand testCommand = new TestCommand();
        TestCommandReaction testCommandReaction = new TestCommandReaction();
        CommandRouter router = new CommandRouter();
        router.addRoute(TestCommand.class, testCommandReaction);
        Now now = new SimpleNow(router);

        TestCommand.Response response = testCommand.execute(now);

        assertThat(response).isNotNull();
        assertThat(response.success()).isTrue();
    }

    @Test
    void givenFutureFunnel_whenSchedule_thenReturnCompletableFutureCommandResponse() {
        TestCommand testCommand = new TestCommand();
        TestCommandReaction testCommandReaction = new TestCommandReaction();
        CommandRouter router = new CommandRouter();
        router.addRoute(TestCommand.class, testCommandReaction);
        Future future = new SimpleFuture(router);

        CompletableFuture<TestCommand.Response> response = testCommand.schedule(future);

        try {
            assertThat(response.get().success()).isTrue();
        } catch (InterruptedException | ExecutionException e) {
            fail();
        }
    }
}
