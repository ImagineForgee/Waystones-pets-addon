package com.github.imagineforgee.waystonespetsaddon.common.client.gui.widgets;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class IconButton extends Button {
    private final ResourceLocation iconTexture;
    private final int iconU, iconV, iconWidth, iconHeight;

    public IconButton(int x, int y, int width, int height, ResourceLocation iconTexture, int u, int v, int iconWidth, int iconHeight, OnPress onPress) {
        super(x, y, width, height, Component.empty(), onPress);
        this.iconTexture = iconTexture;
        this.iconU = u;
        this.iconV = v;
        this.iconWidth = iconWidth;
        this.iconHeight = iconHeight;
    }

    @Override
    public void renderButton(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        super.renderButton(poseStack, mouseX, mouseY, partialTicks);

        Minecraft mc = Minecraft.getInstance();
        mc.getTextureManager().bindForSetup(iconTexture);
        blit(poseStack, this.x + (width - iconWidth) / 2, this.y + (height - iconHeight) / 2,
                iconU, iconV, iconWidth, iconHeight,
                256, 256);
    }
}

