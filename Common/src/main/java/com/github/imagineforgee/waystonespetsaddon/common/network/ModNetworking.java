package com.github.imagineforgee.waystonespetsaddon.common.network;

import com.github.imagineforgee.waystonespetsaddon.common.network.packets.RequestPetsPacket;
import com.github.imagineforgee.waystonespetsaddon.common.network.packets.SyncPetDataPacket;
import net.blay09.mods.balm.api.Balm;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import java.util.Set;
import java.util.UUID;

import static com.github.imagineforgee.waystonespetsaddon.common.network.packets.RequestPetsPacket.REQUEST_PETS;
import static com.github.imagineforgee.waystonespetsaddon.common.network.packets.SyncPetDataPacket.SYNC_PETS;

public class ModNetworking {


    public static void initialize() {
        Balm.getNetworking().registerClientboundPacket(
                SYNC_PETS,
                SyncPetDataPacket.class,
                SyncPetDataPacket::write,
                SyncPetDataPacket::new,
                SyncPetDataPacket::handleClient
        );

        Balm.getNetworking().registerServerboundPacket(
                REQUEST_PETS,
                RequestPetsPacket.class,
                RequestPetsPacket::write,
                RequestPetsPacket::new,
                RequestPetsPacket::handle
        );
    }

    public static void sendPetDataTo(Player player, Set<UUID> pets) {
        if (player instanceof ServerPlayer serverPlayer) {
            Balm.getNetworking().sendTo(serverPlayer, new SyncPetDataPacket(pets));
        }
    }

}
