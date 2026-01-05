package fr.ftnl.tools.messageBuilder.core.dto.components.interactive

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertTrue

class ButtonTest {
    @Test
    fun testSerialization() {
        val button = Button(
            style = ButtonStyles.PRIMARY,
            label = "Click Me",
            customId = "btn_1"
        )
        val json = Json { encodeDefaults = true }
        val encoded = json.encodeToString(button)
        assertTrue(encoded.contains("\"type\":2"))
        assertTrue(encoded.contains("\"style\":1"))
        assertTrue(encoded.contains("\"label\":\"Click Me\""))
        assertTrue(encoded.contains("\"custom_id\":\"btn_1\""))
    }

    @Test
    fun testLinkButtonSerialization() {
        val button = Button(
            style = ButtonStyles.LINK,
            label = "Link",
            url = "https://google.com"
        )
        val json = Json { encodeDefaults = true }
        val encoded = json.encodeToString(button)
        assertTrue(encoded.contains("\"style\":5"))
        assertTrue(encoded.contains("\"url\":\"https://google.com\""))
    }
}
