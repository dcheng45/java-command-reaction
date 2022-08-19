package com.drewcheng.cr.funnel;

import com.drewcheng.cr.command.Command;
import com.drewcheng.cr.command.CommandResponse;
import com.drewcheng.cr.command.CommandRouter;
import com.drewcheng.cr.command.Reaction;

public class NowFunnel implements Now {
    @Override
    public <R extends CommandResponse, C extends Command<R>> R execute(C command) {
        String commandName = command.getClass().getName();
        Reaction<Command<CommandResponse>, CommandResponse> reaction = CommandRouter.route(commandName);
        return (R) reaction.react((Command<CommandResponse>) command);
    }
}
