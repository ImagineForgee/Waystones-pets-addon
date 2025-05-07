package com.github.imagineforgee.waystonespetsaddon;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.phys.Vec3;

public class ForgePetTeleportHandler implements PetTeleportHandler {
    @Override
    public void handleTeleport(Object playerObj, Object targetVecObj, Object levelObj) {
        if (!(playerObj instanceof ServerPlayer player)) return;
        if (!(targetVecObj instanceof Vec3 targetVec)) return;
        if (!(levelObj instanceof ServerLevel targetLevel)) return;

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
                DelayedTaskHandler.schedule(delay, () -> {
                    finalPet.teleportTo(finalX, finalY, finalZ);
                });
            } else {
                pet.teleportTo(finalX, finalY, finalZ);
            }

            count++;
        }
    }
}
