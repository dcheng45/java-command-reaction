package com.drewcheng.command;

public class NowFunnel implements Now {
    @Override
    public <R extends CommandResponse, C extends Command<R>> R execute(C command) {
        String commandName = command.getClass().getName();
        Reaction<Command<CommandResponse>, CommandResponse> reaction = CommandRouter.route(commandName);
        return (R) reaction.react((Command<CommandResponse>) command);
    }
}
