package io.github.runkang10.compactlib.commands.arguments

import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import org.bukkit.event.EventHandler
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin
import java.util.*
import java.util.concurrent.ConcurrentHashMap

class OfflinePlayerCache(private val plugin: JavaPlugin) {
    private val listener = object : Listener {
        @EventHandler
        fun onPlayerJoin(event: PlayerJoinEvent) = newEntry(event.player)
    }

    private val uuidToNameCache = ConcurrentHashMap<UUID, String>()
    private val nameToUuidCache = ConcurrentHashMap<String, UUID>()

    private var loaded = false


    /**
     * Loads all player data from the disk in async at the same time registering an event listener
     * to update the cache when a player joins.
     *
     * **You must call it on Plugin#onEnable() phase only!**
     *
     * @see org.bukkit.plugin.Plugin.onEnable
     * @exception IllegalStateException
     */
    fun load() {
        check(plugin.isEnabled) { "${this::class.simpleName} must be loaded at Plugin#onEnable phase" }
        loaded = true

        plugin.server.asyncScheduler.runNow(plugin) {
            plugin.server.offlinePlayers.forEach(::newEntry)
        }

        plugin.server.pluginManager.registerEvents(listener, plugin)
    }

    fun unload() {
        loaded = false

        HandlerList.unregisterAll(listener)

        uuidToNameCache.clear()
        nameToUuidCache.clear()
    }


    fun getNames(): Collection<String> = uuidToNameCache.values

    fun getNameByUuid(uuid: UUID) = uuidToNameCache[uuid]


    fun getUuids(): Collection<UUID> = uuidToNameCache.keys

    fun getUuidByName(name: String) = nameToUuidCache[name.lowercase()]


    fun getPlayerByName(name: String) = Bukkit.getOfflinePlayerIfCached(name.lowercase())

    fun getPlayerByUuid(uuid: UUID) = Bukkit.getOfflinePlayer(uuid)


    private fun newEntry(player: OfflinePlayer) {
        val uuid = player.uniqueId
        val name = player.name ?: return

        uuidToNameCache[uuid] = name
        nameToUuidCache[name.lowercase()] = uuid
    }
}