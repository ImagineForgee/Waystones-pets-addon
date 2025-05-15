package com.github.imagineforgee.waystonespetsaddon.common.mixins;

import com.github.imagineforgee.waystonespetsaddon.common.Constants;
import com.github.imagineforgee.waystonespetsaddon.common.client.gui.screen.PetSelectionScreen;
import com.github.imagineforgee.waystonespetsaddon.common.network.ModNetworking;
import com.github.imagineforgee.waystonespetsaddon.common.network.packets.RequestPetsPacket;
import com.github.imagineforgee.waystonespetsaddon.common.util.PetTracker;
import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.waystones.client.gui.screen.WaystoneSelectionScreenBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;
import java.util.UUID;


@Mixin(WaystoneSelectionScreenBase.class)
public abstract class WaystoneSelectionScreenMixin extends Screen {

    protected WaystoneSelectionScreenMixin(Component title) {
        super(title);
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void onInit(CallbackInfo ci) {
        Minecraft mc = Minecraft.getInstance();

        int searchBoxX = this.width / 2 - 99;
        int searchBoxY = 64;

        int buttonWidth = 20;
        int buttonHeight = 24;
        int padding = 4;

        int x = searchBoxX - buttonWidth - padding;
        int y = searchBoxY - (buttonHeight - 20) / 2;

        ResourceLocation icon = new ResourceLocation("waystonespetsaddon", "textures/gui/pet_icon.png");

        ImageButton petsButton = new ImageButton(
                x, y,
                buttonWidth, buttonHeight,
                0, 0,
                0,
                icon,
                buttonWidth, buttonHeight,
                btn -> {
                    if (mc.player != null) {
                        Balm.getNetworking().sendToServer(new RequestPetsPacket());
                        Minecraft.getInstance().setScreen(new PetSelectionScreen());
                    }
                },
                Component.literal("Pets")
        );

        this.addRenderableWidget(petsButton);
    }

}

