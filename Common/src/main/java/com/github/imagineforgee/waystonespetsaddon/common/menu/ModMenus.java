package com.github.imagineforgee.waystonespetsaddon.common.menu;

import com.github.imagineforgee.waystonespetsaddon.common.client.gui.PetSelectionMenu;
import net.blay09.mods.balm.api.DeferredObject;
import net.blay09.mods.balm.api.menu.BalmMenus;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;
import org.jetbrains.annotations.NotNull;

public class ModMenus {
    public static DeferredObject<MenuType<PetSelectionMenu>> petSelection;

    public static void initialize(BalmMenus menus) {
        petSelection = menus.registerMenu(id("pet_selection"), PetSelectionMenu::createSelection);
    }

    private static @NotNull ResourceLocation id(String name) {
        return new ResourceLocation("waystonespetsaddon", name);
    }
}
