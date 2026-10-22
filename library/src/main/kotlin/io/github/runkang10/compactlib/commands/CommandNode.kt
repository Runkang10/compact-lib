package io.github.runkang10.compactlib.commands

import com.mojang.brigadier.tree.LiteralCommandNode
import io.papermc.paper.command.brigadier.CommandSourceStack

interface CommandNode {
    val description: String
    val aliases: List<String> get() = emptyList()

    fun execute(): LiteralCommandNode<CommandSourceStack>
}