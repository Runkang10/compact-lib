package io.github.runkang10.compactlib.schedulers

import io.papermc.paper.threadedregions.scheduler.ScheduledTask
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.plugin.java.JavaPlugin

class RegionScheduler(private val plugin: JavaPlugin) {
    private val scheduler get() = plugin.server.regionScheduler


    fun execute(
        location: Location,
        runnable: () -> Unit,
    ) = scheduler.execute(plugin, location) { runnable() }

    fun execute(
        world: World,
        chunkX: Int,
        chunkZ: Int,
        runnable: () -> Unit,
    ) = scheduler.run(plugin, world, chunkX, chunkZ) { runnable() }


    fun run(
        location: Location,
        task: (ScheduledTask) -> Unit,
    ) = scheduler.run(plugin, location, task)

    fun run(
        world: World,
        chunkX: Int,
        chunkZ: Int,
        task: (ScheduledTask) -> Unit,
    ) = scheduler.run(plugin, world, chunkX, chunkZ, task)


    fun runDelayed(
        location: Location,
        delayTicks: Long,
        task: (ScheduledTask) -> Unit,
    ) = scheduler.runDelayed(plugin, location, task, delayTicks)

    fun runDelayed(
        world: World,
        chunkX: Int,
        chunkZ: Int,
        delayTicks: Long,
        task: (ScheduledTask) -> Unit,
    ) = scheduler.runDelayed(plugin, world, chunkX, chunkZ, task, delayTicks)


    fun runAtFixedRate(
        location: Location,
        initialDelayTicks: Long,
        delayTicks: Long,
        task: (ScheduledTask) -> Unit,
    ) = scheduler.runAtFixedRate(plugin, location, task, initialDelayTicks, delayTicks)

    fun runAtFixedRate(
        world: World,
        chunkX: Int,
        chunkZ: Int,
        initialDelayTicks: Long,
        delayTicks: Long,
        task: (ScheduledTask) -> Unit,
    ) = scheduler.runAtFixedRate(plugin, world, chunkX, chunkZ, task, initialDelayTicks, delayTicks)
}