package unit.com.drewcheng.command;

import com.drewcheng.command.Reaction;

public class TestCommandReaction implements Reaction<TestCommand, TestCommand.Response> {
    @Override
    public TestCommand.Response react(TestCommand command) {
        return new TestCommand.Response(true);
    }

}
