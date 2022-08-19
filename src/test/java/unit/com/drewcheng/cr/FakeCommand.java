package unit.com.drewcheng.cr;

import com.drewcheng.cr.command.Command;
import com.drewcheng.cr.command.CommandResponse;

public class FakeCommand implements Command<FakeCommand.Response> {

    record Response(Boolean success) implements CommandResponse {}

}
