package com.drewcheng.cr.command;

import java.util.HashMap;
import java.util.Map;

public class CommandRouter {
    private final Map<String, Reaction<Command<CommandResponse>, CommandResponse>> routes = new HashMap<>();

    public <CR extends CommandResponse, C extends Command<CR>> void addRoute(Class<C> commandClass, Reaction<C, CR> reaction) {
        String commandName = commandClass.getName();
        if (!routes.containsKey(commandName)) {
            routes.put(commandName, (Reaction<Command<CommandResponse>, CommandResponse>) reaction);
        } else {
            throw new IllegalStateException("Reaction already exists for command: " + commandName);
        }
    }

    public <CR extends CommandResponse, C extends Command<CR>> Reaction<Command<CommandResponse>, CommandResponse> route(Class<C> commandClass) {
        String commandName = commandClass.getName();
        Reaction<Command<CommandResponse>, CommandResponse> reaction = routes.get(commandName);
        if (reaction == null) {
            throw new IllegalStateException("Reaction not found for command: " + commandName);
        }
        return reaction;
    }
}
