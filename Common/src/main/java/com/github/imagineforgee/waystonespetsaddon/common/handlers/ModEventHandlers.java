package com.github.imagineforgee.waystonespetsaddon.common.handlers;

import com.github.imagineforgee.waystonespetsaddon.common.PetTeleportHandler;
import com.github.imagineforgee.waystonespetsaddon.common.api.events.PetTamedEvent;
import com.github.imagineforgee.waystonespetsaddon.common.network.ModNetworking;
import com.github.imagineforgee.waystonespetsaddon.common.util.PetData;
import com.github.imagineforgee.waystonespetsaddon.common.util.PetTracker;
import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.api.event.PlayerLoginEvent;
import net.blay09.mods.waystones.api.WaystoneTeleportEvent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.phys.Vec3;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

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

        Balm.getEvents().onEvent(PetTamedEvent.class, event -> {
            ServerPlayer player = event.getPlayer();
            UUID petId = event.getPet().getUUID();
            PetTracker.addPet(player, petId);
            ModNetworking.sendPetDataTo(player, PetTracker.get(player).getPets());
        });

        Balm.getEvents().onEvent(PlayerLoginEvent.class, player -> {
            ServerPlayer serverPlayer = player.getPlayer();
            Set<UUID> pets = PetTracker.get(serverPlayer).getPets();
            ModNetworking.sendPetDataTo(serverPlayer, pets);
        });

    }
}

