package com.github.imagineforgee.waystonespetsaddon.common.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;

import java.util.*;

public class PetData {
    private static final String KEY = "WaystonesPets";
    private final Set<UUID> petUUIDs = new HashSet<>();

    public void addPet(UUID petId) {
        petUUIDs.add(petId);
    }

    public Set<UUID> getPets() {
        return petUUIDs;
    }

    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        ListTag petList = new ListTag();
        for (UUID uuid : petUUIDs) {
            petList.add(StringTag.valueOf(uuid.toString()));
        }
        tag.put(KEY, petList);
        return tag;
    }

    public void load(CompoundTag tag) {
        petUUIDs.clear();
        if (tag.contains(KEY)) {
            ListTag petList = tag.getList(KEY, Tag.TAG_STRING);
            for (Tag petTag : petList) {
                petUUIDs.add(UUID.fromString(petTag.getAsString()));
            }
        }
    }

    public static PetData fromTag(CompoundTag tag) {
        PetData data = new PetData();
        data.load(tag);
        return data;
    }
}
