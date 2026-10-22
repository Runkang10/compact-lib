package io.github.runkang10.compactlib

import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.logger.slf4j.ComponentLogger
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.Tag
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver

class ColoredLogger private constructor(
    private val logger: ComponentLogger,
    private val configuration: Configuration,
) {
    companion object {
        class Configuration {
            var infoColor: TextColor = NamedTextColor.AQUA
            var warnColor: TextColor = NamedTextColor.YELLOW
            var errorColor: TextColor = NamedTextColor.RED
        }
    }

    constructor(
        logger: ComponentLogger,
        configuration: Configuration.() -> Unit = {},
    ) : this(logger, Configuration().apply(configuration))

    constructor(
        name: String,
        configuration: Configuration.() -> Unit = {},
    ) : this(ComponentLogger.logger(name), Configuration().apply(configuration))

    constructor(
        clazz: Class<*>,
        configuration: Configuration.() -> Unit = {},
    ) : this(ComponentLogger.logger(clazz), Configuration().apply(configuration))

    private val miniMessage = MiniMessage.builder(MiniMessage.Preset.DEFAULT)
        .editTags { tag ->
            tag.tag("info", Tag.styling(configuration.infoColor))
            tag.tag("warn", Tag.styling(configuration.warnColor))
            tag.tag("error", Tag.styling(configuration.errorColor))
        }
        .build()


    fun info(
        message: String,
        tags: TagResolver = TagResolver.empty(),
        throwable: Throwable? = null,
    ) = logger.info(miniMessage.deserialize("<info>$message", tags), throwable)

    fun warn(
        message: String,
        tags: TagResolver = TagResolver.empty(),
        throwable: Throwable? = null,
    ) = logger.warn(miniMessage.deserialize("<warn>$message", tags), throwable)

    fun error(
        message: String,
        tags: TagResolver = TagResolver.empty(),
        throwable: Throwable? = null,
    ) = logger.error(miniMessage.deserialize("<error>$message", tags), throwable)
}