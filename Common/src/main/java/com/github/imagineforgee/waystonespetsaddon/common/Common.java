package com.github.imagineforgee.waystonespetsaddon.common;

import com.github.imagineforgee.waystonespetsaddon.common.handlers.ModEventHandlers;
import com.github.imagineforgee.waystonespetsaddon.common.menu.ModMenus;
import net.blay09.mods.balm.api.Balm;

public class Common {
    public static void initialize() {
        ModEventHandlers.initialize();
        ModMenus.initialize(Balm.getMenus());
    }

}
