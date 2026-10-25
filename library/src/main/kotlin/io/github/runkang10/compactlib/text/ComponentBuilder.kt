package io.github.runkang10.compactlib.text

import net.kyori.adventure.key.Key
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentBuilder
import net.kyori.adventure.text.ComponentLike
import net.kyori.adventure.text.TextReplacementConfig
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEventSource
import net.kyori.adventure.text.format.Style
import net.kyori.adventure.text.format.StyleBuilderApplicable
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.util.ARGBLike
import java.util.function.BiPredicate
import java.util.function.Consumer

@DslComponentBuilder
class ComponentBuilder(private var builderComponent: Component) : GenericComponent {
    override fun append(component: Component) {
        builderComponent = builderComponent.append(component)
    }

    override fun append(component: ComponentLike) {
        builderComponent = builderComponent.append(component)
    }

    override fun append(builder: ComponentBuilder<*, *>) {
        builderComponent = builderComponent.append(builder)
    }

    override fun append(vararg components: ComponentLike) {
        builderComponent = builderComponent.append(*components)
    }

    override fun append(components: List<ComponentLike>) {
        builderComponent = builderComponent.append(components)
    }

    override fun appendSpace() {
        builderComponent = builderComponent.appendSpace()
    }

    override fun appendNewline() {
        builderComponent = builderComponent.appendNewline()
    }


    override fun children(children: List<ComponentLike>) {
        builderComponent = builderComponent.children(children)
    }

    override fun children() = builderComponent.children()


    override fun clickEvent(event: ClickEvent<*>?) {
        builderComponent = builderComponent.clickEvent(event)
    }

    override fun clickEvent() = builderComponent.clickEvent()


    override fun color(color: TextColor?) {
        builderComponent = builderComponent.color(color)
    }

    override fun color() = builderComponent.color()

    override fun colorIfAbsent(color: TextColor) {
        builderComponent = builderComponent.colorIfAbsent(color)
    }

    override fun shadowColor(color: ARGBLike?) {
        builderComponent = builderComponent.shadowColor(color)
    }

    override fun shadowColor() = builderComponent.shadowColor()

    override fun shadowColorIfAbsent(color: ARGBLike?) {
        builderComponent = builderComponent.shadowColorIfAbsent(color)
    }


    override fun compact(style: Style?) {
        builderComponent = builderComponent.compact(style)
    }

    override fun compact() {
        builderComponent = builderComponent.compact()
    }


    override fun contains(component: Component) = builderComponent.contains(component)

    override fun contains(
        component: Component,
        equals: BiPredicate<in Component, in Component>,
    ) = builderComponent.contains(component, equals)


    override fun decorate(decoration: TextDecoration) {
        builderComponent = builderComponent.decorate(decoration)
    }

    override fun decorate(vararg decorations: TextDecoration) {
        builderComponent = builderComponent.decorate(*decorations)
    }

    override fun decoration(decoration: TextDecoration) = builderComponent.decoration(decoration)

    override fun decoration(
        decoration: TextDecoration,
        state: TextDecoration.State,
    ) {
        builderComponent = builderComponent.decoration(decoration, state)
    }

    override fun decoration(
        decoration: TextDecoration,
        flag: Boolean,
    ) {
        builderComponent = builderComponent.decoration(decoration, flag)
    }

    override fun decorationIfAbsent(
        decoration: TextDecoration,
        state: TextDecoration.State,
    ) {
        builderComponent = builderComponent.decorationIfAbsent(decoration, state)
    }

    override fun decorations(
        decorations: Set<TextDecoration>,
        flag: Boolean,
    ) {
        builderComponent = builderComponent.decorations(decorations, flag)
    }

    override fun decorations(decorations: Map<TextDecoration, TextDecoration.State>) {
        builderComponent = builderComponent.decorations(decorations)
    }

    override fun decorations() = builderComponent.decorations()


    override fun font(key: Key?) {
        builderComponent = builderComponent.font(key)
    }

    override fun font() = builderComponent.font()


    override fun hasDecoration(decoration: TextDecoration) = builderComponent.hasDecoration(decoration)

    override fun hasStyling() = builderComponent.hasStyling()


    override fun hoverEvent(source: HoverEventSource<*>?) {
        builderComponent = builderComponent.hoverEvent(source)
    }

    override fun hoverEvent() = builderComponent.hoverEvent()


    override fun insertion(insertion: String?) {
        builderComponent = builderComponent.insertion(insertion)
    }

    override fun insertion() = builderComponent.insertion()


    override fun replaceText(configurer: Consumer<TextReplacementConfig.Builder>) {
        builderComponent = builderComponent.replaceText(configurer)
    }

    override fun replaceText(config: TextReplacementConfig) {
        builderComponent = builderComponent.replaceText(config)
    }


    override fun applyFallbackStyle(style: Style) {
        builderComponent = builderComponent.applyFallbackStyle(style)
    }

    override fun applyFallbackStyle(vararg styles: StyleBuilderApplicable) {
        builderComponent = builderComponent.applyFallbackStyle(*styles)
    }

    override fun style(style: Style) {
        builderComponent = builderComponent.style(style)
    }

    override fun style(style: Style.Builder) {
        builderComponent = builderComponent.style(style)
    }

    override fun style() = builderComponent.style()

    override fun style(consumer: Consumer<Style.Builder>) {
        builderComponent = builderComponent.style(consumer)
    }

    override fun style(
        consumer: Consumer<Style.Builder>,
        strategy: Style.Merge.Strategy,
    ) {
        builderComponent = builderComponent.style(consumer, strategy)
    }

    override fun mergeStyle(component: Component) {
        builderComponent = builderComponent.mergeStyle(component)
    }

    override fun mergeStyle(
        component: Component,
        vararg merges: Style.Merge,
    ) {
        builderComponent = builderComponent.mergeStyle(component, *merges)
    }

    override fun mergeStyle(
        component: Component,
        merges: Set<Style.Merge>,
    ) {
        builderComponent = builderComponent.mergeStyle(component, merges)
    }


    fun replace(component: Component) {
        builderComponent = component
    }


    override fun asComponent() = builderComponent
}