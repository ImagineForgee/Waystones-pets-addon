package com.github.imagineforgee.waystonespetsaddon.common.util;

import net.minecraft.nbt.CompoundTag;

import java.util.*;

import net.blay09.mods.balm.api.Balm;
import net.minecraft.server.level.ServerPlayer;

public class PetTracker {

    public static PetData get(ServerPlayer player) {
        CompoundTag tag = Balm.getHooks().getPersistentData(player);
        PetData data = PetData.fromTag(tag);
        return data;
    }

    public static void save(ServerPlayer player, PetData data) {
        CompoundTag tag = Balm.getHooks().getPersistentData(player);
        tag.merge(data.save());
    }

    public static void addPet(ServerPlayer player, UUID petId) {
        PetData data = get(player);
        data.addPet(petId);
        save(player, data);
    }
}

