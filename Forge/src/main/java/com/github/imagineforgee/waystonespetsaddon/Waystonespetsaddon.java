package com.github.imagineforgee.waystonespetsaddon;


import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.waystones.api.WaystoneTeleportEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod(Constants.MOD_ID)
public class Waystonespetsaddon {
    
    public Waystonespetsaddon() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ForgeConfig.SPEC);
        Mod.EventBusSubscriber.Bus.MOD.bus().get().addListener((ModConfigEvent e) -> {
            if (e.getConfig().getSpec() == ForgeConfig.SPEC) {
                ForgeConfig.CONFIG.applyToCommon();
            }
        });
        PetTeleportAPI.HANDLER = new ForgePetTeleportHandler();

        Balm.getEvents().onEvent(WaystoneTeleportEvent.Pre.class, event ->{
            Entity entity = event.getContext().getEntity();
            if (!(entity instanceof ServerPlayer player)) return;

            Vec3 location = event.getContext().getDestination().getLocation();
            Vec3 targetVec = new Vec3(location.x, location.y, location.z);

            ServerLevel level = player.getServer().getLevel(entity.getLevel().dimension());
            if (level != null) {
                PetTeleportAPI.HANDLER.handleTeleport(player, targetVec, level);
            }
        });
    }

}