package unit.com.drewcheng.command;

import com.drewcheng.command.Command;
import com.drewcheng.command.CommandResponse;

public class FakeCommand implements Command<FakeCommand.Response> {

    record Response(Boolean success) implements CommandResponse {}

}
