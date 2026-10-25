package io.github.runkang10.compactlib.text

import net.kyori.adventure.text.ComponentLike
import net.kyori.adventure.text.TranslatableComponent
import net.kyori.adventure.text.TranslationArgument
import net.kyori.adventure.translation.Translatable

@DslComponentBuilder
class TranslatableComponentBuilder private constructor(delegate: ComponentBuilder) : TypedGenericComponent<TranslatableComponent>(
    TranslatableComponent::class.java,
    delegate,
) {
    constructor(component: TranslatableComponent) : this(ComponentBuilder(component))


    fun arguments(args: List<ComponentLike>) {
        replaceTyped(typed.arguments(args))
    }

    fun arguments(vararg args: ComponentLike) {
        replaceTyped(typed.arguments(*args))
    }

    fun arguments(): List<TranslationArgument> = typed.arguments()


    fun fallback(fallback: String?) {
        replaceTyped(typed.fallback(fallback))
    }

    fun fallback(): String? = typed.fallback()


    fun key(key: String) {
        replaceTyped(typed.key(key))
    }

    fun key(translatable: Translatable) {
        replaceTyped(typed.key(translatable))
    }

    fun key(): String = typed.key()
}