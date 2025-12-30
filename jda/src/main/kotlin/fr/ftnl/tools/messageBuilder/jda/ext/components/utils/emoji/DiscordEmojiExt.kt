package fr.ftnl.tools.messageBuilder.jda.ext.components.utils.emoji

import fr.ftnl.tools.messageBuilder.core.dto.components.utils.DiscordEmoji
import net.dv8tion.jda.api.entities.emoji.Emoji


fun DiscordEmoji.toJda(): Emoji {
    return if (id == null) {
        requireNotNull(name) { "Name must be provided for unicode emojis" }
        Emoji.fromUnicode(name!!)
    } else {
        requireNotNull(name) { "Name must be provided for custom emojis" }
        Emoji.fromCustom(name ?: "invalid_name", id!!, animated ?: false)
    }
}