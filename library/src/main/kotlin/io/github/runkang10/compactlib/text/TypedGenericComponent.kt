package io.github.runkang10.compactlib.text

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextReplacementConfig
import net.kyori.adventure.text.format.Style
import java.util.function.Consumer

@DslComponentBuilder
abstract class TypedGenericComponent<C : Component>(
    private val type: Class<C>,
    private val delegate: ComponentBuilder,
) : GenericComponent by delegate {
    protected val typed: C get() = type.cast(delegate.asComponent())

    protected fun replaceTyped(component: C) = delegate.replace(component)


    override fun compact() {
        val compacted = typed.compact()
        if (!type.isInstance(compacted)) return

        delegate.replace(compacted)
    }

    override fun compact(style: Style?) {
        val compacted = typed.compact(style)
        if (!type.isInstance(compacted)) return

        delegate.replace(compacted)
    }


    override fun replaceText(config: TextReplacementConfig) {
        val replaced = typed.replaceText(config)
        check(type.isInstance(replaced)) { "replaceText changed the component type" }
        delegate.replace(replaced)
    }

    override fun replaceText(configurer: Consumer<TextReplacementConfig.Builder>) {
        val replaced = typed.replaceText(configurer)
        check(type.isInstance(replaced)) { "replaceText changed the component type" }
        delegate.replace(replaced)
    }


    override fun asComponent() = typed
}