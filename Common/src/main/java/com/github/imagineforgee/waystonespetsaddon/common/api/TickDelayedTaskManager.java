package com.github.imagineforgee.waystonespetsaddon.common.api;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TickDelayedTaskManager {
    private static final List<IDelayedTask> tasks = new LinkedList<>();

    public static void schedule(IDelayedTask task) {
        tasks.add(task);
    }

    public static void tick() {
        Iterator<IDelayedTask> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            IDelayedTask task = iterator.next();
            if (task.tick()) {
                task.run();
                iterator.remove();
            }
        }
    }
}
