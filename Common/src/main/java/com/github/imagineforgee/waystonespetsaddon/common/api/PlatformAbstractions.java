package com.github.imagineforgee.waystonespetsaddon.common.api;

public class PlatformAbstractions {
    public static DelayedTaskFactory delayedTaskFactory;

    public static IDelayedTask createDelayedTask(int ticks, Runnable task) {
        if (delayedTaskFactory == null)
            throw new IllegalStateException("Platform not initialized");
        return delayedTaskFactory.create(ticks, task);
    }

    @FunctionalInterface
    public interface DelayedTaskFactory {
        IDelayedTask create(int ticks, Runnable task);
    }
}
