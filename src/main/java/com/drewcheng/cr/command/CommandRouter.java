package com.drewcheng.cr.command;

import java.util.HashMap;
import java.util.Map;

public class CommandRouter {
    private static final Map<String, Reaction<Command<CommandResponse>, CommandResponse>> routes = new HashMap<>();

    public static <CR extends CommandResponse, C extends Command<CR>> void addRoute(String commandName, Reaction<C, CR> reaction) {
        if (!routes.containsKey(commandName)) {
            routes.put(commandName, (Reaction<Command<CommandResponse>, CommandResponse>) reaction);
        } else {
            throw new IllegalStateException("Reaction already exists for command: " + commandName);
        }
    }

    public static Reaction<Command<CommandResponse>, CommandResponse> route(String commandName) {
        Reaction<Command<CommandResponse>, CommandResponse> reaction = routes.get(commandName);
        if (reaction == null) {
            throw new IllegalStateException("Reaction not found for command: " + commandName);
        }
        return reaction;
    }

    public static void clearRoutes() {
        routes.clear();
    }
}
