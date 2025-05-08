package com.github.imagineforgee.waystonespetsaddon.client;

import com.github.imagineforgee.waystonespetsaddon.common.Constants;
import com.github.imagineforgee.waystonespetsaddon.common.client.CommonClient;
import net.blay09.mods.balm.api.client.BalmClient;
import net.fabricmc.api.ClientModInitializer;

public class WaystonesPetsAddonClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BalmClient.initialize(Constants.MOD_ID, CommonClient::initialize);
    }
}
