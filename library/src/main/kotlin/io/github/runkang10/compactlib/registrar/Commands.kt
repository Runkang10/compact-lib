package io.github.runkang10.compactlib.registrar

import io.github.runkang10.compactlib.commands.CommandNode
import io.papermc.paper.plugin.configuration.PluginMeta
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents

class Commands {
    private val storedCommands = mutableSetOf<CommandNode>()


    fun addCommands(vararg commandNode: CommandNode) {
        storedCommands.addAll(commandNode)
    }

    fun addCommands(commandNodes: Collection<CommandNode>) {
        storedCommands.addAll(commandNodes)
    }


    fun register(
        lifecycleManager: LifecycleEventManager<*>,
        pluginMeta: PluginMeta?,
    ) {
        lifecycleManager.registerEventHandler(LifecycleEvents.COMMANDS) { registrar ->
            storedCommands.forEach { commandNode ->
                if (pluginMeta != null) {
                    registrar
                        .registrar()
                        .register(
                            pluginMeta,
                            commandNode.execute(),
                            commandNode.description,
                            commandNode.aliases,
                        )
                    return@forEach
                }

                registrar.registrar().register(
                    commandNode.execute(),
                    commandNode.description,
                    commandNode.aliases,
                )
            }
        }
    }
}