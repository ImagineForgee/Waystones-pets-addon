package com.github.imagineforgee.waystonespetsaddon.common.client.gui;

import com.github.imagineforgee.waystonespetsaddon.common.menu.ModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PetSelectionMenu extends AbstractContainerMenu {

    public PetSelectionMenu(MenuType<PetSelectionMenu> type, int windowId) {
        super(type, windowId);
    }

    @Override
    public @NotNull ItemStack quickMoveStack(Player player, int i) {
        return ItemStack.EMPTY;
    }

    public static PetSelectionMenu createSelection(int syncId) {
        return new PetSelectionMenu(ModMenus.petSelection.get(), syncId);
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}
