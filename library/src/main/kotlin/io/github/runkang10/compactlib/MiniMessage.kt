package io.github.runkang10.compactlib

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage

@DslMarker
annotation class MiniMessageDsl

@MiniMessageDsl
class MiniMessageBuilder(private val miniMessage: MiniMessage) {
    private val contents = mutableListOf<String>()


    fun append(content: String) = contents.add(content)

    fun appendLine(content: String) = append("$content<newline>")

    fun appendSpace() = append(" ")

    fun appendNewline() = append("<newline>")

    fun build() = Component.textOfChildren(*contents.map { miniMessage.deserialize(it) }.toTypedArray())
}

fun buildMiniMessage(
    miniMessage: MiniMessage = MiniMessage.miniMessage(),
    block: MiniMessageBuilder.() -> Unit,
) = MiniMessageBuilder(miniMessage).apply(block).build()