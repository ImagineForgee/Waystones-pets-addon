package com.github.imagineforgee.waystonespetsaddon.events;

import com.github.imagineforgee.waystonespetsaddon.Constants;
import com.github.imagineforgee.waystonespetsaddon.api.TickDelayedTaskManager;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID)
public class ForgeTickHandler {
    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            TickDelayedTaskManager.tick();
        }
    }
}
