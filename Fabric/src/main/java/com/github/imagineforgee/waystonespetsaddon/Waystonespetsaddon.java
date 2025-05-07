package com.github.imagineforgee.waystonespetsaddon;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.waystones.api.WaystoneTeleportEvent;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class Waystonespetsaddon implements ModInitializer {
    
    @Override
    public void onInitialize() {
        ModLoadingContext.registerConfig(Constants.MOD_ID, ModConfig.Type.COMMON, FabricConfig.COMMON_SPEC);
        FabricConfig.applyToCommon();
        PetTeleportAPI.HANDLER = new FabricPetTeleportHandler();
        Balm.getEvents().onEvent(WaystoneTeleportEvent.Pre.class, event -> {
            Entity entity = event.getContext().getEntity();
            if (!(entity instanceof ServerPlayerEntity player)) return;

            Vec3d location = event.getContext().getDestination().getLocation();
            Vec3d targetVec = new Vec3d(location.getX(), location.getY(), location.getZ());

            ServerWorld level = player.getServer().getWorld(entity.getWorld().getRegistryKey());
            if (level != null) {
                PetTeleportAPI.HANDLER.handleTeleport(player, targetVec, level);
            }
        });
    }
}
