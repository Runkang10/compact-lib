package io.github.runkang10.compactlib

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver

@DslMarker
annotation class MiniMessageDsl

@MiniMessageDsl
class MiniMessageBuilder(
    private val miniMessage: MiniMessage,
    private val tags: TagResolver,
) {
    private val contents = mutableListOf<String>()


    fun append(content: String) = contents.add(content)

    fun appendLine(content: String) = append("$content<newline>")

    fun appendSpace() = append(" ")

    fun appendNewline() = append("<newline>")

    fun build(): Component = miniMessage.deserialize(contents.joinToString(""), tags)
}

fun buildMiniMessage(
    miniMessage: MiniMessage = MiniMessage.miniMessage(),
    tags: TagResolver = TagResolver.empty(),
    block: MiniMessageBuilder.() -> Unit,
) = MiniMessageBuilder(miniMessage, tags).apply(block).build()