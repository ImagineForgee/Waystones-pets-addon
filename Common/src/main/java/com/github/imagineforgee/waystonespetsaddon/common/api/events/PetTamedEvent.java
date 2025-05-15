package com.github.imagineforgee.waystonespetsaddon.common.api.events;

import net.blay09.mods.balm.api.event.BalmEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.TamableAnimal;

public class PetTamedEvent extends BalmEvent {
    private final ServerPlayer player;
    private final TamableAnimal pet;

    public PetTamedEvent(ServerPlayer player, TamableAnimal pet) {
        this.player = player;
        this.pet = pet;
    }

    public ServerPlayer getPlayer() {
        return player;
    }

    public TamableAnimal getPet() {
        return pet;
    }
}
