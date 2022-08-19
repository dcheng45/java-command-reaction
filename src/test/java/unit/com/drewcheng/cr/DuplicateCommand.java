package unit.com.drewcheng.cr;

import com.drewcheng.cr.command.Command;
import com.drewcheng.cr.command.CommandResponse;

public class DuplicateCommand implements Command<DuplicateCommand.Response> {

    record Response(Boolean success) implements CommandResponse {}

}
