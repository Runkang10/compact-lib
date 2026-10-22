@Suppress("unused")
object Env {
    val isProduction = System.getenv("production")?.toBooleanStrictOrNull() ?: false

    val version = System.getenv("version") ?: "0.0.0-DEV"
}