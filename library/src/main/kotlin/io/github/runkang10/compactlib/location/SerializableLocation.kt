package io.github.runkang10.compactlib.location

import kotlinx.serialization.Serializable

@Serializable
data class SerializableLocation(
    val world: String,
    val x: Double,
    val y: Double,
    val z: Double,
    val yaw: Float,
    val pitch: Float,
)