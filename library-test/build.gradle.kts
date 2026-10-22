import xyz.jpenilla.resourcefactory.bukkit.BukkitPluginYaml

plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlin.plugin.serialization)
    alias(libs.plugins.shadow)
    alias(libs.plugins.resourceFactory)
    alias(libs.plugins.runServer)
}

dependencies {
    implementation(libs.kotlinx.serialization.core)
    implementation(project(":library"))
    compileOnly(libs.paper)
}

paperPluginYaml {
    name.set("${rootProject.name}-test-plugin")
    description.set("Testing plugin for compact-lib.")
    version.set(Env.version)
    author.set("Runkang10")

    apiVersion.set("26.3")
    load.set(BukkitPluginYaml.PluginLoadOrder.STARTUP)
    bootstrapper.set("io.github.runkang10.compactlib.test.TestBootstrap")
    main.set("io.github.runkang10.compactlib.test.TestPlugin")
}

tasks {
    shadowJar {
        archiveBaseName.set("${rootProject.name}-test-plugin")
        archiveClassifier.set("")
        archiveVersion.set("")

        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }

    runServer {
        minecraftVersion("26.3")

        jvmArgs("-Dcom.mojang.eula.agree=true")
    }
}

kotlin {
    jvmToolchain(25)
}