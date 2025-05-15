package com.github.imagineforgee.waystonespetsaddon.common.network.packets;

import com.github.imagineforgee.waystonespetsaddon.common.Constants;
import com.github.imagineforgee.waystonespetsaddon.common.util.PetTracker;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import java.util.Set;
import java.util.UUID;

import static com.github.imagineforgee.waystonespetsaddon.common.network.ModNetworking.sendPetDataTo;

public class RequestPetsPacket {
    public static final ResourceLocation REQUEST_PETS = new ResourceLocation(Constants.MOD_ID, "request_pets");

    public RequestPetsPacket() {}

    public RequestPetsPacket(FriendlyByteBuf ignored) {}

    public void write(FriendlyByteBuf buf) {}

    public static void handle(ServerPlayer player, RequestPetsPacket msg) {
        Set<UUID> petUUIDs = PetTracker.get(player).getPets();
        sendPetDataTo(player, petUUIDs);
    }
}
