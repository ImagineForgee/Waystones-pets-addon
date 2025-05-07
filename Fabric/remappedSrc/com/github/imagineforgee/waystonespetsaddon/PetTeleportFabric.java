package com.github.imagineforgee.waystonespetsaddon;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.waystones.api.TeleportDestination;
import net.blay09.mods.waystones.api.WaystoneTeleportEvent;

public class PetTeleportFabric {
    public static void init() {
        Balm.getEvents().onEvent(WaystoneTeleportEvent.Pre.class, event -> {
            if (!(event.getContext().getEntity() instanceof net.minecraft.server.network.ServerPlayerEntity player)) return;

            var destination = event.getContext().getDestination();
            PetTeleportHandler.handle(player, destination.getLocation(), destination.getLevel());
        });
    }
}
