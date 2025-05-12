package com.github.imagineforgee.waystonespetsaddon.common.menu;

import com.github.imagineforgee.waystonespetsaddon.common.client.gui.PetSelectionMenu;
import net.blay09.mods.balm.api.DeferredObject;
import net.blay09.mods.balm.api.menu.BalmMenus;
import net.blay09.mods.waystones.api.IWaystone;
import net.blay09.mods.waystones.core.Waystone;
import net.blay09.mods.waystones.menu.WaystoneSettingsMenu;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;
import org.jetbrains.annotations.NotNull;

public class ModMenus {
    public static DeferredObject<MenuType<PetSelectionMenu>> petSelection;

    public static void initialize(BalmMenus menus) {
        petSelection = menus.registerMenu(id("pet_selection"), (windowId, inv, data) -> new PetSelectionMenu(petSelection.get(), windowId));
    }

    private static @NotNull ResourceLocation id(String name) {
        return new ResourceLocation("waystonespetsaddon", name);
    }
}
