package com.github.imagineforgee.waystonespetsaddon.events;

import com.github.imagineforgee.waystonespetsaddon.api.TickDelayedTaskManager;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class FabricTickHandler {
    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            TickDelayedTaskManager.tick();
        });
    }
}
