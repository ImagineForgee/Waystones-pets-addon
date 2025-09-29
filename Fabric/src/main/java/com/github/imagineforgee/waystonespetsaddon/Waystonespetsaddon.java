package com.github.imagineforgee.waystonespetsaddon;

import com.github.imagineforgee.waystonespetsaddon.common.Common;
import com.github.imagineforgee.waystonespetsaddon.common.Constants;
import com.github.imagineforgee.waystonespetsaddon.common.api.PlatformAbstractions;
import com.github.imagineforgee.waystonespetsaddon.events.FabricTickHandler;
import net.blay09.mods.balm.api.Balm;
import net.fabricmc.api.ModInitializer;

import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import net.minecraftforge.fml.config.ModConfig;

public class Waystonespetsaddon implements ModInitializer {
    
    @Override
    public void onInitialize() {
        ForgeConfigRegistry.INSTANCE.register(Constants.MOD_ID, ModConfig.Type.COMMON, FabricConfig.COMMON_SPEC);
        Balm.initialize(Constants.MOD_ID, Common::initialize);
        FabricConfig.applyToCommon();
        PlatformAbstractions.delayedTaskFactory = DelayedTaskImpl::new;
        FabricTickHandler.register();
    }
}
