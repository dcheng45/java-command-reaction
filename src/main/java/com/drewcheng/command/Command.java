package com.drewcheng.command;

public interface Command<R extends CommandResponse> {

    default R execute(Now now) {
        return now.execute(this);
    }

}
