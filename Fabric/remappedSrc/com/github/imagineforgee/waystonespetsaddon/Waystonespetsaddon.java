package com.github.imagineforgee.waystonespetsaddon;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;

public class Waystonespetsaddon implements ModInitializer {
    
    @Override
    public void onInitialize() {
        MidnightConfig.init("waystonespetsaddon", FabricConfig.class);
        FabricConfig.applyToCommon();
        PetTeleportFabric.init();
    }
}
