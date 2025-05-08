package com.github.imagineforgee.waystonespetsaddon.common.client.gui.screen;

import com.github.imagineforgee.waystonespetsaddon.common.client.gui.PetSelectionMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class PetSelectionScreen extends AbstractContainerScreen<PetSelectionMenu> {

    private final Player player;
    private final List<TamableAnimal> pets;

    public PetSelectionScreen(PetSelectionMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
        this.player = inventory.player;
        this.pets = getNearbyPets(player);
    }

    @Override
    protected void init() {
        super.init();
        int buttonId = 0;

        for (TamableAnimal pet : pets) {
            Button petButton = new Button(this.width / 2 - 100, this.height / 2 + buttonId * 25, 200, 20,
                    Component.literal("Pet: " + pet.getName().getString()),
                    button -> selectPet(pet));
            this.addRenderableWidget(petButton);
            buttonId++;
        }
    }

    private void selectPet(TamableAnimal pet) {
        Minecraft.getInstance().player.displayClientMessage(Component.literal("Selected " + pet.getName().getString()), true);
        this.onClose();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    private List<TamableAnimal> getNearbyPets(Player player) {
        return player.level.getEntitiesOfClass(TamableAnimal.class,
                player.getBoundingBox().inflate(10),
                pet -> pet.isTame() && player.getUUID().equals(pet.getOwnerUUID()));
    }

    protected void renderBg(PoseStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
