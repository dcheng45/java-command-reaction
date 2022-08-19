package unit.com.drewcheng.cr;

import com.drewcheng.cr.command.Reaction;

public class DuplicateCommandReaction implements Reaction<DuplicateCommand, DuplicateCommand.Response> {
    @Override
    public DuplicateCommand.Response react(DuplicateCommand command) {
        return new DuplicateCommand.Response(true);
    }
}
