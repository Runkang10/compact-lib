package io.github.runkang10.compactlib.test

import io.github.runkang10.compactlib.ColoredLogger
import io.papermc.paper.plugin.bootstrap.BootstrapContext
import io.papermc.paper.plugin.bootstrap.PluginBootstrap
import io.papermc.paper.plugin.bootstrap.PluginProviderContext

@Suppress("unused", "UnstableApiUsage")
class TestBootstrap : PluginBootstrap {
    private lateinit var logger: ColoredLogger


    override fun bootstrap(context: BootstrapContext) {
        logger = ColoredLogger(context.logger)

        logger.info("This an info level message.")
        logger.warn("This a warn level message.")
        logger.error("This an error level message.")
    }

    override fun createPlugin(context: PluginProviderContext) = TestPlugin(logger)
}