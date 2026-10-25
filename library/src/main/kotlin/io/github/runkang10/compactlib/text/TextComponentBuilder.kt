package io.github.runkang10.compactlib.text

import net.kyori.adventure.text.TextComponent

@DslComponentBuilder
class TextComponentBuilder private constructor(delegate: ComponentBuilder) : TypedGenericComponent<TextComponent>(
    TextComponent::class.java,
    delegate,
) {
    constructor(component: TextComponent) : this(ComponentBuilder(component))


    fun content() = typed.content()

    fun content(content: String) {
        replaceTyped(typed.content(content))
    }
}