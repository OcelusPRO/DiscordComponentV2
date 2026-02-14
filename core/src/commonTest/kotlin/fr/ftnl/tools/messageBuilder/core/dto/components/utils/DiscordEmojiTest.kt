package fr.ftnl.tools.messageBuilder.core.dto.components.utils

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertTrue

class DiscordEmojiTest {
    private val json = Json { encodeDefaults = true }

    @Test
    fun testDiscordEmojiSerialization() {
        val emoji = DiscordEmoji().setId(123L).setName("smile").setAnimated(true)
        val encoded = json.encodeToString(emoji)

        assertTrue(encoded.contains("\"id\":123"))
        assertTrue(encoded.contains("\"name\":\"smile\""))
        assertTrue(encoded.contains("\"animated\":true"))
    }
}

