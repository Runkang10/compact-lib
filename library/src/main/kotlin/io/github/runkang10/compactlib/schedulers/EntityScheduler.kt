package io.github.runkang10.compactlib.schedulers

import io.papermc.paper.threadedregions.scheduler.ScheduledTask
import org.bukkit.entity.Entity
import org.bukkit.plugin.java.JavaPlugin

class EntityScheduler(
    private val plugin: JavaPlugin,
) {
    fun run(
        entity: Entity,
        retired: (() -> Unit)? = null,
        scheduledTask: (ScheduledTask) -> Unit,
    ) = entity.scheduler.run(plugin, scheduledTask, retired)

    fun runDelayed(
        entity: Entity,
        delay: Long,
        retired: (() -> Unit)? = null,
        scheduledTask: (ScheduledTask) -> Unit,
    ) = entity.scheduler.runDelayed(plugin, scheduledTask, retired, delay)

    fun runAtFixedRate(
        entity: Entity,
        initialDelay: Long,
        delay: Long,
        retired: (() -> Unit)? = null,
        scheduledTask: (ScheduledTask) -> Unit,
    ) = entity.scheduler.runAtFixedRate(plugin, scheduledTask, retired, initialDelay, delay)
}