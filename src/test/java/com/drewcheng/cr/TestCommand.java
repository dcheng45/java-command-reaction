package com.drewcheng.cr;

import com.drewcheng.cr.command.Command;
import com.drewcheng.cr.command.CommandResponse;

public class TestCommand implements Command<TestCommand.Response> {

    public record Response(Boolean success) implements CommandResponse {}

}
