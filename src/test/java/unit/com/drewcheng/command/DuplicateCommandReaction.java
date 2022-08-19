package unit.com.drewcheng.command;

import com.drewcheng.command.Reaction;

public class DuplicateCommandReaction implements Reaction<DuplicateCommand, DuplicateCommand.Response> {
    @Override
    public DuplicateCommand.Response react(DuplicateCommand command) {
        return new DuplicateCommand.Response(true);
    }
}
