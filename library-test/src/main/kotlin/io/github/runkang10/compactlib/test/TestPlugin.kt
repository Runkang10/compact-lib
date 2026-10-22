package io.github.runkang10.compactlib.test

import com.mojang.brigadier.tree.LiteralCommandNode
import io.github.runkang10.compactlib.commands.CommandNode
import io.github.runkang10.compactlib.commands.command
import io.github.runkang10.compactlib.commands.execute
import io.github.runkang10.compactlib.commands.getExecutor
import io.github.runkang10.compactlib.registrar.Commands
import io.github.runkang10.compactlib.registrar.Listeners
import io.papermc.paper.command.brigadier.CommandSourceStack
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.server.ServerLoadEvent
import org.bukkit.plugin.java.JavaPlugin

class TestPlugin : JavaPlugin(), Listener, CommandNode {
    private val commands = Commands()
    private val listeners = Listeners()


    override val description = "A test command."

    override fun execute(): LiteralCommandNode<CommandSourceStack> = command("compact-lib") {
        execute { context ->
            val executor = context.getExecutor()
            executor.sendMessage("Hello from executor!")
        }
    }


    @EventHandler
    fun onServerLoad(event: ServerLoadEvent) {
        server.consoleSender.sendMessage("The server has been loaded!")
    }


    override fun onLoad() {
        commands.addCommands(this)
        commands.register(lifecycleManager, pluginMeta)
    }

    override fun onEnable() {
        listeners.addListeners(this)
        listeners.register(this)
    }

    override fun onDisable() {
        listeners.unregister(this)
    }
}