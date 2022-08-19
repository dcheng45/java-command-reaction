package unit.com.drewcheng.command;

import com.drewcheng.command.Command;
import com.drewcheng.command.CommandResponse;

public class DuplicateCommand implements Command<DuplicateCommand.Response> {

    record Response(Boolean success) implements CommandResponse {}

}