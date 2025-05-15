package com.github.imagineforgee.waystonespetsaddon.common.client.gui.screen;

import com.github.imagineforgee.waystonespetsaddon.common.Constants;
import com.github.imagineforgee.waystonespetsaddon.common.PetTeleportConfig;
import com.github.imagineforgee.waystonespetsaddon.common.client.ClientPetData;
import com.github.imagineforgee.waystonespetsaddon.common.util.PetData;
import com.github.imagineforgee.waystonespetsaddon.common.util.PetTracker;
import com.mojang.blaze3d.vertex.PoseStack;
import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.waystones.client.gui.widget.ITooltipProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class PetSelectionScreen extends Screen {

    private final Player player;

    public PetSelectionScreen() {
        super(Component.literal("pets"));
        Constants.LOG.info("PetSelectionScreen opened!");
        this.player = Minecraft.getInstance().player;
    }

    @Override
    protected void init() {
        super.init();
        int buttonId = 0;

        Set<UUID> trackedPets = ClientPetData.getPets();
        if (trackedPets == null || trackedPets.isEmpty()) {
            this.addRenderableWidget(new Button(this.width / 2 - 100, this.height / 2, 200, 20,
                    Component.literal("No pets found."), btn -> {}));
            return;
        }

        Constants.LOG.info(trackedPets.toString());

        List<TamableAnimal> pets = player.level.getEntitiesOfClass(TamableAnimal.class,
                player.getBoundingBox().inflate(PetTeleportConfig.values.teleportRadius),
                pet -> pet.isTame()
                        && trackedPets.contains(pet.getUUID())
                        && player.getUUID().equals(pet.getOwnerUUID())
                        && (PetTeleportConfig.values.teleportSittingPets || !pet.isInSittingPose()));

        for (TamableAnimal pet : pets) {
            Button petButton = new Button(this.width / 2 - 100, this.height / 2 + buttonId * 25, 200, 20,
                    Component.literal("Pet: " + pet.getName().getString()),
                    button -> selectPet(pet));
            this.addRenderableWidget(petButton);
            buttonId++;
        }

        if (buttonId == 0) {
            this.addRenderableWidget(new Button(this.width / 2 - 100, this.height / 2, 200, 20,
                    Component.literal("No tracked pets nearby."), btn -> {}));
        }
    }


    private void selectPet(TamableAnimal pet) {
        Minecraft.getInstance().player.displayClientMessage(Component.literal("Selected " + pet.getName().getString()), true);

        this.onClose();
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

}
