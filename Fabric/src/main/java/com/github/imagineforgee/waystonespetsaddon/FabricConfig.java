package com.github.imagineforgee.waystonespetsaddon;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class FabricConfig {
    public static final CommonConfig COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;

    static {
        Pair<CommonConfig, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
        COMMON = pair.getLeft();
        COMMON_SPEC = pair.getRight();
    }

    public static class CommonConfig {
        public final ForgeConfigSpec.IntValue maxPetsToTeleport;
        public final ForgeConfigSpec.DoubleValue teleportRadius;
        public final ForgeConfigSpec.BooleanValue teleportSittingPets;
        public final ForgeConfigSpec.BooleanValue forceUnsitPets;
        public final ForgeConfigSpec.BooleanValue randomizeTeleportOffset;
        public final ForgeConfigSpec.IntValue teleportDelayTicks;

        public CommonConfig(ForgeConfigSpec.Builder builder) {
            builder.push("Pet Teleport Settings");

            teleportSittingPets = builder
                    .comment("If true, sitting pets will be teleported.")
                    .define("teleportSittingPets", false);

            forceUnsitPets = builder
                    .comment("Force pets to stand up before teleporting, even if they are sitting. Only applies if teleportSittingPets is true.")
                    .define("forceUnsitPets", false);

            teleportRadius = builder
                    .comment("The radius (in blocks) around the player to detect tamed pets.")
                    .defineInRange("teleportRadius", 10.0, 1.0, 100.0);

            maxPetsToTeleport = builder
                    .comment("Maximum number of pets to teleport at once.")
                    .defineInRange("maxPetsToTeleport", 5, 1, 1000);

            randomizeTeleportOffset = builder
                    .comment("Randomize the offset of teleported pets to avoid stacking.")
                    .define("randomizeTeleportOffset", true);

            teleportDelayTicks = builder
                    .comment("Delay (in ticks) before pets are teleported. 0 = instant.")
                    .defineInRange("teleportDelayTicks", 0, 0, 200);

            builder.pop();
        }
    }

    public static void applyToCommon() {
        PetTeleportConfig.ConfigValues values = new PetTeleportConfig.ConfigValues();
        values.maxPetsToTeleport = COMMON.maxPetsToTeleport.get();
        values.teleportRadius = COMMON.teleportRadius.get();
        values.teleportSittingPets = COMMON.teleportSittingPets.get();
        values.forceUnsitPets = COMMON.forceUnsitPets.get();
        values.randomizeTeleportOffset = COMMON.randomizeTeleportOffset.get();
        values.teleportDelayTicks = COMMON.teleportDelayTicks.get();
        PetTeleportConfig.values = values;
    }
}