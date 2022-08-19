package unit.com.drewcheng.command;

import com.drewcheng.command.Command;
import com.drewcheng.command.CommandResponse;

public class TestCommand implements Command<TestCommand.Response> {

    record Response(Boolean success) implements CommandResponse {}

}
