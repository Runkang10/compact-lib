package io.github.runkang10.compactlib.schedulers

import io.papermc.paper.threadedregions.scheduler.ScheduledTask
import org.bukkit.plugin.java.JavaPlugin
import java.util.concurrent.TimeUnit

class AsyncScheduler(
    private val plugin: JavaPlugin,
) {
    private val scheduler get() = plugin.server.asyncScheduler


    fun runNow(scheduledTask: (ScheduledTask) -> Unit) = scheduler.runNow(plugin, scheduledTask)

    fun runDelayed(
        delay: Long,
        timeUnit: TimeUnit,
        scheduledTask: (ScheduledTask) -> Unit,
    ) = scheduler.runDelayed(plugin, scheduledTask, delay, timeUnit)

    fun runAtFixedRate(
        initialDelay: Long,
        delay: Long,
        timeUnit: TimeUnit,
        scheduledTask: (ScheduledTask) -> Unit,
    ) = scheduler.runAtFixedRate(plugin, scheduledTask, initialDelay, delay, timeUnit)
}