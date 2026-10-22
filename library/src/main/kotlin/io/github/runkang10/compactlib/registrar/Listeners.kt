package io.github.runkang10.compactlib.registrar

import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class Listeners {
    private val storedListeners = mutableSetOf<Listener>()


    fun addListeners(vararg listeners: Listener) {
        storedListeners.addAll(listeners)
    }

    fun addListeners(listeners: Collection<Listener>) {
        storedListeners.addAll(listeners)
    }


    fun register(plugin: JavaPlugin) {
        storedListeners.forEach { listener ->
            plugin.server.pluginManager.registerEvents(listener, plugin)
        }
    }

    fun unregister(plugin: JavaPlugin) {
        HandlerList.unregisterAll(plugin)
        storedListeners.clear()
    }
}