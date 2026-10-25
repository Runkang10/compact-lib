package io.github.runkang10.compactlib.test

import com.mojang.brigadier.tree.LiteralCommandNode
import io.github.runkang10.compactlib.ColoredLogger
import io.github.runkang10.compactlib.commands.*
import io.github.runkang10.compactlib.commands.arguments.OfflinePlayerArgument
import io.github.runkang10.compactlib.commands.arguments.OfflinePlayerCache
import io.github.runkang10.compactlib.registrar.Commands
import io.github.runkang10.compactlib.registrar.Listeners
import io.github.runkang10.compactlib.text.textComponent
import io.github.runkang10.compactlib.text.translatableComponent
import io.papermc.paper.command.brigadier.CommandSourceStack
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.OfflinePlayer
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.server.ServerLoadEvent
import org.bukkit.plugin.java.JavaPlugin

class TestPlugin(private val logger: ColoredLogger) : JavaPlugin(), Listener, CommandNode {
    private val commands = Commands()
    private val listeners = Listeners()

    private val offlinePlayerCache = OfflinePlayerCache(this)


    override val description = "A test command."

    override fun execute(): LiteralCommandNode<CommandSourceStack> = command("compact-lib") {
        execute { context ->
            val executor = context.getExecutor()
            executor.sendMessage(
                textComponent("Hello from executor!") {
                    content(content() + " CHANGED")

                    hoverEvent(textComponent("You hovered!"))
                    clickEvent(ClickEvent.callback { it.sendMessage(textComponent("You clicked me!")) })
                },
            )
            executor.sendMessage(
                translatableComponent("debug.gamemodes.error") {
                    color(NamedTextColor.YELLOW)
                },
            )
        }
        argument("target", OfflinePlayerArgument(offlinePlayerCache)) {
            execute { context ->
                val target = context.getArgument<OfflinePlayer>("target")
                context.getExecutor().sendMessage("Identified the target as $target.")
            }
        }
    }


    @EventHandler
    fun onServerLoad(event: ServerLoadEvent) {
        logger.info("The server has been loaded!")
    }


    override fun onLoad() {
        commands.addCommands(this)
        commands.register(lifecycleManager, pluginMeta)
    }

    override fun onEnable() {
        listeners.addListeners(this)
        listeners.register(this)

        offlinePlayerCache.load()
    }

    override fun onDisable() {
        offlinePlayerCache.unload()

        listeners.unregister(this)
    }
}