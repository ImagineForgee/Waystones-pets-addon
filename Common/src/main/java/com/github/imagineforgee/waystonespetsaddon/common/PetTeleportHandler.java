package com.github.imagineforgee.waystonespetsaddon.common;

import com.github.imagineforgee.waystonespetsaddon.common.api.PlatformAbstractions;
import com.github.imagineforgee.waystonespetsaddon.common.api.TickDelayedTaskManager;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.phys.Vec3;

public class PetTeleportHandler {
    public static void handleTeleport(ServerPlayer player, Vec3 targetVec, ServerLevel targetLevel) {
        BlockPos targetPos = new BlockPos(targetVec);

        var pets = player.level.getEntitiesOfClass(TamableAnimal.class,
                player.getBoundingBox().inflate(PetTeleportConfig.values.teleportRadius),
                pet -> pet.isTame()
                        && player.getUUID().equals(pet.getOwnerUUID())
                        && (PetTeleportConfig.values.teleportSittingPets || !pet.isInSittingPose()));

        int count = 0;

        for (TamableAnimal pet : pets) {
            if (!(pet.level instanceof ServerLevel petLevel)) continue;
            if (count >= PetTeleportConfig.values.maxPetsToTeleport) break;

            if (pet.isInSittingPose() && PetTeleportConfig.values.forceUnsitPets) {
                pet.setInSittingPose(false);
            }

            if (!petLevel.equals(targetLevel)) {
                pet = (TamableAnimal) pet.changeDimension(targetLevel);
                if (pet == null) continue;
            }

            double offsetX = 0;
            double offsetZ = 0;

            if (PetTeleportConfig.values.randomizeTeleportOffset) {
                offsetX = (player.getRandom().nextDouble() - 0.5) * 2;
                offsetZ = (player.getRandom().nextDouble() - 0.5) * 2;
            }

            double finalX = targetPos.getX() + 0.5 + offsetX;
            double finalY = targetPos.getY();
            double finalZ = targetPos.getZ() + 0.5 + offsetZ;

            if (PetTeleportConfig.values.teleportDelayTicks > 0) {
                int delay = PetTeleportConfig.values.teleportDelayTicks;
                TamableAnimal finalPet = pet;

                TickDelayedTaskManager.schedule(PlatformAbstractions.createDelayedTask(delay, () -> {
                    finalPet.teleportTo(finalX, finalY, finalZ);
                }));
            } else {
                pet.teleportTo(finalX, finalY, finalZ);
            }

            count++;
        }
    }
}
