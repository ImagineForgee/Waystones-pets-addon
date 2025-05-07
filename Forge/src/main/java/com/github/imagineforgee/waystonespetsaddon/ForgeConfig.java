package com.github.imagineforgee.waystonespetsaddon;

import com.github.imagineforgee.waystonespetsaddon.PetTeleportConfig;
import net.minecraftforge.common.ForgeConfigSpec;

public class ForgeConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final Config CONFIG = new Config();
    public static final ForgeConfigSpec SPEC;

    static {
        CONFIG.setup();
        SPEC = BUILDER.build();
    }

    public static class Config {
        public ForgeConfigSpec.IntValue maxPetsToTeleport;
        public ForgeConfigSpec.DoubleValue teleportRadius;
        public ForgeConfigSpec.BooleanValue teleportSittingPets;
        public ForgeConfigSpec.BooleanValue forceUnsitPets;
        public ForgeConfigSpec.BooleanValue randomizeTeleportOffset;
        public ForgeConfigSpec.IntValue teleportDelayTicks;

        public void setup() {
            teleportSittingPets = BUILDER
                    .comment("If true, sitting pets will be teleported.")
                    .define("teleportSittingPets", false);

            teleportRadius = BUILDER
                    .comment("The radius (in blocks) around the player to detect tamed pets.")
                    .defineInRange("teleportRadius", 10.0, 1.0, 100.0);

            maxPetsToTeleport = BUILDER
                    .comment("Maximum number of pets to teleport at once.")
                    .defineInRange("maxPetsToTeleport", 5, 1, 1000);

            randomizeTeleportOffset = BUILDER
                    .comment("Randomize the offset of teleported pets to avoid stacking.")
                    .define("randomizeTeleportOffset", true);

            teleportDelayTicks = BUILDER
                    .comment("Delay (in ticks) before pets are teleported. 0 = instant.")
                    .defineInRange("teleportDelayTicks", 0, 0, 100);

            forceUnsitPets = BUILDER
                    .comment("Force pets to stand before teleporting, even if they are sitting. Note only works if teleportSittingPets is set to true.")
                    .define("forceUnsitPets", false);
        }

        public void applyToCommon() {
            PetTeleportConfig.values = new PetTeleportConfig.ConfigValues();
            PetTeleportConfig.values.maxPetsToTeleport = maxPetsToTeleport.get();
            PetTeleportConfig.values.teleportRadius = teleportRadius.get();
            PetTeleportConfig.values.teleportSittingPets = teleportSittingPets.get();
            PetTeleportConfig.values.forceUnsitPets = forceUnsitPets.get();
            PetTeleportConfig.values.randomizeTeleportOffset = randomizeTeleportOffset.get();
            PetTeleportConfig.values.teleportDelayTicks = teleportDelayTicks.get();
        }
    }
}