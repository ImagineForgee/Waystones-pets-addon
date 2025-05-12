package com.github.imagineforgee.waystonespetsaddon.common.client;

import com.github.imagineforgee.waystonespetsaddon.common.client.gui.PetSelectionMenu;
import com.github.imagineforgee.waystonespetsaddon.common.client.gui.screen.PetSelectionScreen;
import com.github.imagineforgee.waystonespetsaddon.common.menu.ModMenus;
import net.blay09.mods.balm.api.DeferredObject;
import net.blay09.mods.balm.api.client.screen.BalmScreens;
import net.minecraft.world.inventory.MenuType;

public class ModScreens {
    public static void initialize(BalmScreens screens) {
        screens.registerScreen(ModMenus.petSelection::get, PetSelectionScreen::new);
    }
}
