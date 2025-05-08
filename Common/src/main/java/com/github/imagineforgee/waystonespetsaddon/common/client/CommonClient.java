package com.github.imagineforgee.waystonespetsaddon.common.client;

import net.blay09.mods.balm.api.client.BalmClient;

public class CommonClient {
    public static void initialize() {
        ModScreens.initialize(BalmClient.getScreens());
    }
}
