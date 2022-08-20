package com.drewcheng.cr.command;

import com.drewcheng.cr.funnel.Future;
import com.drewcheng.cr.funnel.Now;

import java.util.concurrent.CompletableFuture;

public interface Command<R extends CommandResponse> {

    default R execute(Now now) {
        return now.execute(this);
    }

    default CompletableFuture<R> schedule(Future future) {
        return future.execute(this);
    }

}
