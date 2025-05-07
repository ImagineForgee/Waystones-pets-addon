package com.github.imagineforgee.waystonespetsaddon;

import com.github.imagineforgee.waystonespetsaddon.api.PlatformAbstractions;
import com.github.imagineforgee.waystonespetsaddon.events.FabricTickHandler;
import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.waystones.api.WaystoneTeleportEvent;
import net.fabricmc.api.ModInitializer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class Waystonespetsaddon implements ModInitializer {
    
    @Override
    public void onInitialize() {
        ModLoadingContext.registerConfig(Constants.MOD_ID, ModConfig.Type.COMMON, FabricConfig.COMMON_SPEC);
        FabricConfig.applyToCommon();
        PlatformAbstractions.delayedTaskFactory = DelayedTaskImpl::new;
        FabricTickHandler.register();
        Balm.getEvents().onEvent(WaystoneTeleportEvent.Pre.class, event ->{
            Entity entity = event.getContext().getEntity();
            if (!(entity instanceof ServerPlayer player)) return;

            Vec3 location = event.getContext().getDestination().getLocation();
            Vec3 targetVec = new Vec3(location.x, location.y, location.z);

            ServerLevel level = player.getServer().getLevel(entity.getLevel().dimension());
            if (level != null) {
                PetTeleportHandler.handleTeleport(player, targetVec, level);
            }
        });
    }
}
