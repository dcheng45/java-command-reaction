package com.drewcheng.cr.command;

import com.drewcheng.cr.funnel.Now;

public interface Command<R extends CommandResponse> {

    default R execute(Now now) {
        return now.execute(this);
    }

}
