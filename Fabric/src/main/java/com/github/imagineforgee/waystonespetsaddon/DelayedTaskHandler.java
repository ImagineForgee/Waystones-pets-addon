package com.github.imagineforgee.waystonespetsaddon;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DelayedTaskHandler {
    private static final List<DelayedTask> tasks = new LinkedList<>();

    static {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            Iterator<DelayedTask> iterator = tasks.iterator();
            while (iterator.hasNext()) {
                DelayedTask task = iterator.next();
                task.ticksRemaining--;
                if (task.ticksRemaining <= 0) {
                    task.task.run();
                    iterator.remove();
                }
            }
        });
    }

    public static void schedule(int delayTicks, Runnable task) {
        tasks.add(new DelayedTask(delayTicks, task));
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
