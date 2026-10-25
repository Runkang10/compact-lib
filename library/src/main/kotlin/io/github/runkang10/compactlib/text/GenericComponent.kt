package io.github.runkang10.compactlib.text

import net.kyori.adventure.key.Key
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentBuilder
import net.kyori.adventure.text.ComponentLike
import net.kyori.adventure.text.TextReplacementConfig
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.event.HoverEventSource
import net.kyori.adventure.text.format.*
import net.kyori.adventure.util.ARGBLike
import java.util.function.BiPredicate
import java.util.function.Consumer

interface GenericComponent {
    fun append(component: Component)

    fun append(component: ComponentLike)

    fun append(builder: ComponentBuilder<*, *>)

    fun append(vararg components: ComponentLike)

    fun append(components: List<ComponentLike>)

    fun appendSpace()

    fun appendNewline()


    fun children(children: List<ComponentLike>)

    fun children(): List<Component>


    fun clickEvent(event: ClickEvent<*>?)

    fun clickEvent(): ClickEvent<*>?


    fun color(color: TextColor?)

    fun color(): TextColor?

    fun colorIfAbsent(color: TextColor)

    fun shadowColor(color: ARGBLike?)

    fun shadowColor(): ShadowColor?

    fun shadowColorIfAbsent(color: ARGBLike?)


    fun compact(style: Style?)

    fun compact()


    fun contains(component: Component): Boolean

    fun contains(
        component: Component,
        equals: BiPredicate<in Component, in Component>,
    ): Boolean


    fun decorate(decoration: TextDecoration)

    fun decorate(vararg decorations: TextDecoration)

    fun decoration(decoration: TextDecoration): TextDecoration.State

    fun decoration(
        decoration: TextDecoration,
        state: TextDecoration.State,
    )

    fun decoration(
        decoration: TextDecoration,
        flag: Boolean,
    )

    fun decorationIfAbsent(
        decoration: TextDecoration,
        state: TextDecoration.State,
    )

    fun decorations(
        decorations: Set<TextDecoration>,
        flag: Boolean,
    )

    fun decorations(decorations: Map<TextDecoration, TextDecoration.State>)

    fun decorations(): Map<TextDecoration, TextDecoration.State>


    fun font(key: Key?)

    fun font(): Key?


    fun hasDecoration(decoration: TextDecoration): Boolean

    fun hasStyling(): Boolean


    fun hoverEvent(source: HoverEventSource<*>?)

    fun hoverEvent(): HoverEvent<*>?


    fun insertion(insertion: String?)

    fun insertion(): String?


    fun replaceText(configurer: Consumer<TextReplacementConfig.Builder>)

    fun replaceText(config: TextReplacementConfig)


    fun applyFallbackStyle(style: Style)

    fun applyFallbackStyle(vararg styles: StyleBuilderApplicable)

    fun style(style: Style)

    fun style(style: Style.Builder)

    fun style(): Style

    fun style(consumer: Consumer<Style.Builder>)

    fun style(
        consumer: Consumer<Style.Builder>,
        strategy: Style.Merge.Strategy,
    )


    fun mergeStyle(component: Component)

    fun mergeStyle(
        component: Component,
        vararg merges: Style.Merge,
    )

    fun mergeStyle(
        component: Component,
        merges: Set<Style.Merge>,
    )


    fun asComponent(): Component
}