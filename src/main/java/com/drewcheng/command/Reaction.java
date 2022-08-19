package com.drewcheng.command;

import com.google.common.reflect.TypeToken;

public interface Reaction<C extends Command<R>, R extends CommandResponse> {

    R react(C command);

    default TypeToken<C> commandType() {
        return new TypeToken<C>(getClass()) {};
    }

}
