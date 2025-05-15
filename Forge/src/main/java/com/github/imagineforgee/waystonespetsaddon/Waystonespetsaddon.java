package com.github.imagineforgee.waystonespetsaddon;


import com.github.imagineforgee.waystonespetsaddon.common.Common;
import com.github.imagineforgee.waystonespetsaddon.common.Constants;
import com.github.imagineforgee.waystonespetsaddon.common.api.PlatformAbstractions;
import net.blay09.mods.balm.api.Balm;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod(Constants.MOD_ID)
public class Waystonespetsaddon {
    
    public Waystonespetsaddon() {
        Balm.initialize(Constants.MOD_ID, Common::initialize);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ForgeConfig.SPEC);
        Mod.EventBusSubscriber.Bus.MOD.bus().get().addListener((ModConfigEvent e) -> {
            if (e.getConfig().getSpec() == ForgeConfig.SPEC) {
                ForgeConfig.CONFIG.applyToCommon();
            }
        });
        PlatformAbstractions.delayedTaskFactory = DelayedTaskImpl::new;
    }

}