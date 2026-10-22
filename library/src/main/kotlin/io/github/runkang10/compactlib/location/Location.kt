package io.github.runkang10.compactlib.location

import org.bukkit.Bukkit
import org.bukkit.Location

fun Location.toSerializableLocation() = SerializableLocation(world.name, x, y, z, yaw, pitch)

fun SerializableLocation.toLocation() = Location(Bukkit.getWorld(world), x, y, z, yaw, pitch)