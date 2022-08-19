package com.drewcheng.command;

public interface Now {

    <R extends CommandResponse, C extends Command<R>> R execute(C command);

}
