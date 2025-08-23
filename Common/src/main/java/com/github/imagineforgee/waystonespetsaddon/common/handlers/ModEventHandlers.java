package com.github.imagineforgee.waystonespetsaddon.common.handlers;

import com.github.imagineforgee.waystonespetsaddon.common.PetTeleportHandler;
import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.waystones.api.WaystoneTeleportEvent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class ModEventHandlers {
    private static Screen lastScreen = null;

    public static void initialize() {
        Balm.getEvents().onEvent(WaystoneTeleportEvent.Pre.class, event ->{
            Entity entity = event.getContext().getEntity();
            if (!(entity instanceof ServerPlayer player)) return;

            Vec3 location = event.getContext().getDestination().getLocation();
            Vec3 targetVec = new Vec3(location.x, location.y, location.z);

            ServerLevel level = player.getServer().getLevel(entity.getLevel().dimension());
            if (level != null) {
                PetTeleportHandler.handleTeleport(player, targetVec, level);
            }
        });
    }
}

