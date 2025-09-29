package com.github.imagineforgee.waystonespetsaddon.common.handlers;

import com.github.imagineforgee.waystonespetsaddon.common.PetTeleportHandler;
import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.api.event.BalmEvents;
import net.blay09.mods.waystones.api.WaystoneTeleportEvent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class ModEventHandlers {
    public static void initialize(BalmEvents events) {
        events.onEvent(WaystoneTeleportEvent.Pre.class, event ->{
            Entity entity = event.getContext().getEntity();
            if (!(entity instanceof ServerPlayer player)) return;

            Vec3 location = event.getContext().getDestination().getLocation();
            Vec3 targetVec = new Vec3(location.x, location.y, location.z);

            ServerLevel level = player.getServer().getLevel(entity.level().dimension());
            if (level != null) {
                PetTeleportHandler.handleTeleport(player, targetVec, level);
            }
        });
    }
}

