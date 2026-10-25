package io.github.runkang10.compactlib.text

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentLike
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration

@DslMarker
annotation class DslComponentBuilder


fun textComponent(
    content: String,
    color: TextColor? = null,
    vararg decorations: TextDecoration = emptyArray(),
    block: TextComponentBuilder.() -> Unit = {},
) = TextComponentBuilder(Component.text(content, color, *decorations)).apply(block).asComponent()


fun translatableComponent(
    key: String,
    block: TranslatableComponentBuilder.() -> Unit = {},
) = TranslatableComponentBuilder(Component.translatable(key)).apply(block).asComponent()

fun translatableComponent(
    key: String,
    vararg arguments: ComponentLike,
    block: TranslatableComponentBuilder.() -> Unit = {},
) = TranslatableComponentBuilder(Component.translatable(key, *arguments)).apply(block).asComponent()


fun component(
    component: Component,
    block: ComponentBuilder.() -> Unit = {},
) = ComponentBuilder(component).apply(block).asComponent()