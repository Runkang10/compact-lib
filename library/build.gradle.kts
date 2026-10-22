import com.vanniktech.maven.publish.Checksum
import com.vanniktech.maven.publish.DeploymentValidation

plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlin.plugin.serialization)
    alias(libs.plugins.mavenPublish)
}

dependencies {
    compileOnly(libs.kotlinx.serialization.core)
    compileOnly(libs.paper)
}

if (Env.isProduction) mavenPublishing {
    publishToMavenCentral(true, DeploymentValidation.PUBLISHED)

    signAllPublications()
    checksums(Checksum.MD5, Checksum.SHA1)

    coordinates("io.github.runkang10", "compact-lib", Env.version)

    pom {
        name.set(rootProject.name)
        description.set(property("description")?.toString())
        url.set("https://github.com/Runkang10/compact-lib")

        licenses {
            license {
                name.set("MIT License")
                url.set("https://github.com/Runkang10/compact-lib/blob/main/LICENSE")
            }
        }
        developers {
            developer {
                id.set("runkang10")
                name.set("Runkang10")
                url.set("https://github.com/Runkang10")
            }
        }
        scm {
            url.set("https://github.com/Runkang10/compact-lib")
            connection.set("scm:git:git://github.com/Runkang10/compact-lib.git")
            developerConnection.set("scm:git:ssh://git@github.com/Runkang10/compact-lib.git")
        }
    }
}

kotlin {
    jvmToolchain(25)
}