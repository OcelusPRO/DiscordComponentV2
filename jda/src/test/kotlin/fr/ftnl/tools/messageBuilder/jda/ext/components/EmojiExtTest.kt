package fr.ftnl.tools.messageBuilder.jda.ext.components

import fr.ftnl.tools.messageBuilder.core.dto.components.utils.DiscordEmoji
import fr.ftnl.tools.messageBuilder.jda.ext.components.utils.emoji.toJda
import kotlin.test.Test
import kotlin.test.assertTrue

class EmojiExtTest {
    @Test
    fun testUnicodeEmojiToJda() {
        val result = runCatching { DiscordEmoji().setName("😀").toJda() }
        assertTrue(result.isSuccess)
    }

    @Test
    fun testCustomEmojiToJda() {
        val result = runCatching { DiscordEmoji().setId(123L).setName("custom").setAnimated(false).toJda() }
        assertTrue(result.isSuccess)
    }
}

