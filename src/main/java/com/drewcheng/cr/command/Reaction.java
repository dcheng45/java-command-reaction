package com.drewcheng.cr.command;

import com.drewcheng.cr.command.Command;
import com.drewcheng.cr.command.CommandResponse;
import com.google.common.reflect.TypeToken;

public interface Reaction<C extends Command<R>, R extends CommandResponse> {

    R react(C command);

    default TypeToken<C> commandType() {
        return new TypeToken<C>(getClass()) {};
    }

}
