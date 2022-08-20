package com.drewcheng.cr.funnel;

import com.drewcheng.cr.command.Command;
import com.drewcheng.cr.command.CommandResponse;

import java.util.concurrent.CompletableFuture;

public interface Future {

    <R extends CommandResponse, C extends Command<R>> CompletableFuture<R> execute(C command);

}
