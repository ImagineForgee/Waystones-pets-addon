package com.github.imagineforgee.waystonespetsaddon.common.handlers;

import com.github.imagineforgee.waystonespetsaddon.common.PetTeleportHandler;
import com.github.imagineforgee.waystonespetsaddon.common.client.ModScreens;
import com.github.imagineforgee.waystonespetsaddon.common.client.gui.PetSelectionMenu;
import com.github.imagineforgee.waystonespetsaddon.common.client.gui.screen.PetSelectionScreen;
import com.github.imagineforgee.waystonespetsaddon.common.client.gui.widgets.IconButton;
import com.github.imagineforgee.waystonespetsaddon.common.menu.PetMenuProvider;
import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.api.client.BalmClient;
import net.blay09.mods.balm.api.event.TickPhase;
import net.blay09.mods.balm.api.menu.BalmMenuProvider;
import net.blay09.mods.waystones.api.WaystoneTeleportEvent;
import net.blay09.mods.waystones.client.gui.screen.WaystoneSelectionScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

import java.lang.reflect.Method;

public class ModEventHandlers {
    private static Screen lastScreen = null;

    public static void initialize() {
        Balm.getEvents().onEvent(WaystoneTeleportEvent.Pre.class, event ->{
            Entity entity = event.getContext().getEntity();
            if (!(entity instanceof ServerPlayer player)) return;

            Vec3 location = event.getContext().getDestination().getLocation();
            Vec3 targetVec = new Vec3(location.x, location.y, location.z);

            ServerLevel level = player.getServer().getLevel(entity.getLevel().dimension());
            if (level != null) {
                PetTeleportHandler.handleTeleport(player, targetVec, level);
            }
        });

        Balm.getEvents().onEvent(TickPhase.End.getClass(), (screen) -> {
            Minecraft mc = Minecraft.getInstance();
            Screen current = mc.screen;
            LocalPlayer player = mc.player;

            if (current != null && current != lastScreen) {
                lastScreen = current;

                if (current instanceof WaystoneSelectionScreen) {
                    addPetsButton(current, player);
                }
            }
        });
    }

    private static void addPetsButton(Screen screen, LocalPlayer player) {
        int btnWidth = 50;
        int btnHeight = 20;
        int x = 10;
        int y = screen.height / 2 - btnHeight / 2;

        Button petsButton = new Button(
                x, y,
                btnWidth, btnHeight,
                Component.literal("Pets"),
                btn -> {
                    Balm.getNetworking().openGui(
                            player,
                            new PetMenuProvider()
                    );
                }
        );

        BalmClient.getScreens().addRenderableWidget(screen, petsButton);
    }
}

