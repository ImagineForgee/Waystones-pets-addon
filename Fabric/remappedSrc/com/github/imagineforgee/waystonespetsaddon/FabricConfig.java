package com.github.imagineforgee.waystonespetsaddon;

import eu.midnightdust.lib.config.MidnightConfig;

public class FabricConfig extends MidnightConfig {
    @Entry public static int maxPetsToTeleport = 5;
    @Entry(min = 1.0, max = 64.0) public static double teleportRadius = 10.0;
    @Entry public static boolean teleportSittingPets = false;
    @Entry public static boolean forceUnsitPets = true;
    @Entry public static boolean randomizeTeleportOffset = true;
    @Entry public static int teleportDelayTicks = 0;

    public static void applyToCommon() {
        PetTeleportConfig.ConfigValues values = new PetTeleportConfig.ConfigValues();
        values.maxPetsToTeleport = maxPetsToTeleport;
        values.teleportRadius = teleportRadius;
        values.teleportSittingPets = teleportSittingPets;
        values.forceUnsitPets = forceUnsitPets;
        values.randomizeTeleportOffset = randomizeTeleportOffset;
        values.teleportDelayTicks = teleportDelayTicks;
        PetTeleportConfig.values = values;
    }
}
