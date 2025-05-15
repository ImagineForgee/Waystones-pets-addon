package com.github.imagineforgee.waystonespetsaddon.common.client;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ClientPetData {
    private static Set<UUID> petUUIDs = Set.of();

    public static void setPets(Set<UUID> pets) {
        petUUIDs = pets;
    }

    public static boolean isPet(UUID uuid) {
        return petUUIDs.contains(uuid);
    }

    public static Set<UUID> getPets() {
        return petUUIDs;
    }
}
