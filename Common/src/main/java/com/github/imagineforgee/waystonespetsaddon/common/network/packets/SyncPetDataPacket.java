package com.github.imagineforgee.waystonespetsaddon.common.network.packets;

import com.github.imagineforgee.waystonespetsaddon.common.Constants;
import com.github.imagineforgee.waystonespetsaddon.common.client.ClientPetData;
import com.github.imagineforgee.waystonespetsaddon.common.client.gui.screen.PetSelectionScreen;
import com.github.imagineforgee.waystonespetsaddon.common.network.ModNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class SyncPetDataPacket {
    public static final ResourceLocation SYNC_PETS = new ResourceLocation(Constants.MOD_ID, "sync_pets");
    private final Set<UUID> pets;

    public SyncPetDataPacket(Set<UUID> pets) {
        this.pets = pets;
    }

    public SyncPetDataPacket(FriendlyByteBuf buf) {
        int size = buf.readVarInt();
        this.pets = new HashSet<>();
        for (int i = 0; i < size; i++) {
            this.pets.add(buf.readUUID());
        }
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeVarInt(pets.size());
        for (UUID uuid : pets) {
            buf.writeUUID(uuid);
        }
    }

    public static void handleClient(Player player, SyncPetDataPacket msg) {
        Minecraft.getInstance().execute(() -> {
            Constants.LOG.info(msg.pets.toString());
            ClientPetData.setPets(msg.pets);
        });
    }
}
