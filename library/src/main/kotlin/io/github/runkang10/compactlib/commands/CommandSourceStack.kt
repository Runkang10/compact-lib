package io.github.runkang10.compactlib.commands

inline fun <reified T : Any> ContextSourceStack.getArgument(name: String): T? = getArgument(name, T::class.java)


fun ContextSourceStack.getSender() = source.sender

fun ContextSourceStack.getExecutor() = source.executor ?: getSender()

fun ContextSourceStack.getConsoleSender() = getSender().server.consoleSender