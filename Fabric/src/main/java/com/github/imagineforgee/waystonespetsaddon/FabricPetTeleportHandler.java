package com.github.imagineforgee.waystonespetsaddon;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.server.world.ServerWorld;

public class FabricPetTeleportHandler implements PetTeleportHandler {
    @Override
    public void handleTeleport(Object playerObj, Object targetVecObj, Object levelObj) {
        if (!(playerObj instanceof ServerPlayerEntity player)) return;
        if (!(targetVecObj instanceof Vec3d targetVec)) return;
        if (!(levelObj instanceof ServerWorld targetLevel)) return;

        BlockPos targetPos = new BlockPos(targetVec);

        var pets = player.getWorld().getEntitiesByClass(TameableEntity.class,
                player.getBoundingBox().expand(PetTeleportConfig.values.teleportRadius),
                pet -> pet.isTamed()
                        && player.getUuid().equals(pet.getOwnerUuid())
                        && (PetTeleportConfig.values.teleportSittingPets || !pet.isSitting()));
        int count = 0;

        for (TameableEntity pet : pets) {
            if (!(pet.getWorld() instanceof ServerWorld petLevel)) continue;
            if (!petLevel.equals(targetLevel)) continue;
            if (count >= PetTeleportConfig.values.maxPetsToTeleport) break;

            if (pet.isSitting() && PetTeleportConfig.values.forceUnsitPets) {
                pet.setSitting(false);
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
                DelayedTaskHandler.schedule(delay, () -> {
                    pet.teleport(finalX, finalY, finalZ);
                });
            } else {
                pet.teleport(finalX, finalY, finalZ);
            }

            count++;
        }
    }
}
