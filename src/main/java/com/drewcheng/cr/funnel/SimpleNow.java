package com.drewcheng.cr.funnel;

import com.drewcheng.cr.command.Command;
import com.drewcheng.cr.command.CommandResponse;
import com.drewcheng.cr.command.CommandRouter;
import com.drewcheng.cr.command.Reaction;

public class SimpleNow implements Now {

    private final CommandRouter router;

    public SimpleNow(CommandRouter router) {
        this.router = router;
    }

    @Override
    public <R extends CommandResponse, C extends Command<R>> R execute(C command) {
        Reaction<Command<CommandResponse>, CommandResponse> reaction = router.route(command.getClass());
        return (R) reaction.react((Command<CommandResponse>) command);
    }
}
