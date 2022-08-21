package com.drewcheng.cr.command;

import com.drewcheng.cr.TestCommand;
import com.drewcheng.cr.TestCommandReaction;
import com.drewcheng.cr.funnel.Now;
import com.drewcheng.cr.funnel.SimpleNow;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CommandTest {

    @Test
    void givenFunnel_whenExecute_thenReturnCommandResponse() {
        TestCommand testCommand = new TestCommand();
        TestCommandReaction testCommandReaction = new TestCommandReaction();
        CommandRouter router = new CommandRouter();
        router.addRoute(TestCommand.class, testCommandReaction);
        Now now = new SimpleNow(router);

        TestCommand.Response response = testCommand.execute(now);

        assertThat(response).isNotNull();
        assertThat(response.success()).isTrue();
    }
}
