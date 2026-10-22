package io.github.runkang10.compactlib.commands

import com.mojang.brigadier.Command
import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.builder.RequiredArgumentBuilder
import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.tree.LiteralCommandNode
import io.papermc.paper.command.brigadier.CommandSourceStack
import io.papermc.paper.command.brigadier.Commands

typealias Literal = LiteralCommandNode<CommandSourceStack>
typealias LiteralBuilder = LiteralArgumentBuilder<CommandSourceStack>
typealias ArgumentBuilder <T> = RequiredArgumentBuilder<CommandSourceStack, T>
typealias ContextSourceStack = CommandContext<CommandSourceStack>


inline fun command(
    name: String,
    builder: LiteralBuilder.() -> Unit,
): Literal = Commands.literal(name).apply(builder).build()


inline fun LiteralBuilder.subcommand(
    name: String,
    builder: LiteralBuilder.() -> Unit,
) {
    then(Commands.literal(name).apply(builder))
}

inline fun <T : Any> ArgumentBuilder<T>.subcommand(
    name: String,
    builder: LiteralBuilder.() -> Unit,
) {
    then(Commands.literal(name).apply(builder))
}


inline fun <T : Any> LiteralBuilder.argument(
    name: String,
    argument: ArgumentType<T>,
    builder: ArgumentBuilder<T>.() -> Unit,
) {
    then(Commands.argument(name, argument).apply(builder))
}

inline fun <T : Any, S : Any> ArgumentBuilder<S>.argument(
    name: String,
    argument: ArgumentType<T>,
    builder: ArgumentBuilder<T>.() -> Unit,
) {
    then(Commands.argument(name, argument).apply(builder))
}


fun LiteralBuilder.execute(block: (ContextSourceStack) -> Unit): LiteralBuilder = executes {
    block(it)
    Command.SINGLE_SUCCESS
}

fun <T> ArgumentBuilder<T>.execute(block: (ContextSourceStack) -> Unit): ArgumentBuilder<T> = executes {
    block(it)
    Command.SINGLE_SUCCESS
}


fun LiteralBuilder.permission(
    permission: String,
    condition: (CommandSourceStack) -> Boolean = { true },
): LiteralArgumentBuilder<CommandSourceStack> = requires {
    it.sender.hasPermission(permission) && condition(it)
}

fun <T> ArgumentBuilder<T>.permission(
    permission: String,
    condition: (CommandSourceStack) -> Boolean = { true },
): ArgumentBuilder<T> = requires {
    it.sender.hasPermission(permission) && condition(it)
}