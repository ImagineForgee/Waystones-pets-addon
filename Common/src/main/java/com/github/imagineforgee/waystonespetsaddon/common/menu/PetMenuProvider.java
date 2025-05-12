package com.github.imagineforgee.waystonespetsaddon.common.menu;

import com.github.imagineforgee.waystonespetsaddon.common.client.gui.PetSelectionMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.jetbrains.annotations.Nullable;

public class PetMenuProvider implements MenuProvider {

    @Override
    public Component getDisplayName() {
        return Component.translatable("gui.waystonespetsaddon.pet_selection");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player player) {
        return PetSelectionMenu.createSelection(windowId);
    }
}
