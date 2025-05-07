package com.github.imagineforgee.waystonespetsaddon;

public class PetTeleportConfig {
    public static ConfigValues values = new ConfigValues();

    public static class ConfigValues {
        public int maxPetsToTeleport = 5;
        public double teleportRadius = 10.0;
        public boolean teleportSittingPets = false;
        public boolean forceUnsitPets = true;
        public boolean randomizeTeleportOffset = true;
        public int teleportDelayTicks = 0;
    }
}
