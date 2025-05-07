package com.github.imagineforgee.waystonespetsaddon;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Mod.EventBusSubscriber
public class DelayedTaskHandler {
    private static final List<DelayedTask> tasks = new LinkedList<>();

    public static void schedule(int delayTicks, Runnable task) {
        tasks.add(new DelayedTask(delayTicks, task));
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Iterator<DelayedTask> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            DelayedTask task = iterator.next();
            task.ticksRemaining--;
            if (task.ticksRemaining <= 0) {
                task.task.run();
                iterator.remove();
            }
        }
    }

    private static class DelayedTask {
        int ticksRemaining;
        Runnable task;

        DelayedTask(int ticksRemaining, Runnable task) {
            this.ticksRemaining = ticksRemaining;
            this.task = task;
        }
    }
}

