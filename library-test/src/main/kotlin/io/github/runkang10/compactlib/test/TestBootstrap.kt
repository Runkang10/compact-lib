package io.github.runkang10.compactlib.test

import io.github.runkang10.compactlib.ColoredLogger
import io.papermc.paper.plugin.bootstrap.BootstrapContext
import io.papermc.paper.plugin.bootstrap.PluginBootstrap

class TestBootstrap : PluginBootstrap {
    override fun bootstrap(context: BootstrapContext) {
        val logger = ColoredLogger(context.logger)

        logger.info("This an info level message.")
        logger.warn("This a warn level message.")
        logger.error("This an error level message.")
    }
}