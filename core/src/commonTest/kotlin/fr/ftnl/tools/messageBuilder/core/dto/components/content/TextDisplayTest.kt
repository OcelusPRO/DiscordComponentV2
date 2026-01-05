package fr.ftnl.tools.messageBuilder.core.dto.components.content

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertTrue

class TextDisplayTest {
    @Test
    fun testSerialization() {
        val textDisplay = TextDisplay(content = "Hello")
        val json = Json { encodeDefaults = true }
        val encoded = json.encodeToString(textDisplay)
        assertTrue(encoded.contains("\"type\":10"))
        assertTrue(encoded.contains("\"content\":\"Hello\""))
    }
}
