package com.drewcheng.cr.funnel;

import com.drewcheng.cr.command.Command;
import com.drewcheng.cr.command.CommandResponse;
import com.drewcheng.cr.command.CommandRouter;
import com.drewcheng.cr.command.Reaction;

import java.util.concurrent.CompletableFuture;

public class SimpleFuture implements Future {

    private final CommandRouter router;

    public SimpleFuture(CommandRouter router) {
        this.router = router;
    }

    @Override
    public <R extends CommandResponse, C extends Command<R>> CompletableFuture<R> execute(C command) {
        return CompletableFuture.supplyAsync(() -> {
            Reaction<Command<CommandResponse>, CommandResponse> reaction = router.route(command.getClass());
            return (R) reaction.react((Command<CommandResponse>) command);
        });
    }
}
