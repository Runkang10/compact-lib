package io.github.runkang10.compactlib.commands.arguments

import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType
import com.mojang.brigadier.suggestion.Suggestions
import com.mojang.brigadier.suggestion.SuggestionsBuilder
import io.github.runkang10.compactlib.text.translatableComponent
import io.papermc.paper.command.brigadier.MessageComponentSerializer
import io.papermc.paper.command.brigadier.argument.CustomArgumentType
import org.bukkit.OfflinePlayer
import java.util.concurrent.CompletableFuture

class OfflinePlayerArgument(
    private val cache: OfflinePlayerCache,
    private val take: Int = 50,
) : CustomArgumentType.Converted<OfflinePlayer, String> {
    private val unknownPlayerException = DynamicCommandExceptionType {
        MessageComponentSerializer
            .message()
            .serialize(
                translatableComponent("argument.entity.notfound.player") {
                    fallback("No player was found")
                },
            )
    }


    override fun convert(nativeType: String) =
        cache.getPlayerByName(nativeType.lowercase()) ?: throw unknownPlayerException.create(nativeType)

    override fun getNativeType(): ArgumentType<String> = StringArgumentType.word()

    override fun <S : Any> listSuggestions(
        context: CommandContext<S>,
        builder: SuggestionsBuilder,
    ): CompletableFuture<Suggestions> {
        cache.getNames()
            .asSequence()
            .filter { it.startsWith(builder.remaining, true) }
            .take(take)
            .forEach(builder::suggest)
        return builder.buildFuture()
    }
}