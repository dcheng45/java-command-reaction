package com.drewcheng.cr.funnel;

import com.drewcheng.cr.command.Command;
import com.drewcheng.cr.command.CommandResponse;

public interface Now {

    <R extends CommandResponse, C extends Command<R>> R execute(C command);

}
