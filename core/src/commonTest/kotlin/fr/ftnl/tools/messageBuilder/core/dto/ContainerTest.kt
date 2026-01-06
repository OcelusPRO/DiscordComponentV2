package fr.ftnl.tools.messageBuilder.core.dto

import fr.ftnl.tools.messageBuilder.core.dto.components.content.TextDisplay
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.Container
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertTrue

class ContainerTest {
    
    @Test
    fun testSerialization() {
        val container = Container(
            id = 1,
            components = listOf(TextDisplay(content = "Test")),
            accentColor = 0xFFFFFF,
            spoiler = false
        )
        
        val json = Json { encodeDefaults = true }
        val encoded = json.encodeToString(container)
        
        assertTrue(encoded.contains("\"type\":17"))
        assertTrue(encoded.contains("\"components\":["))
        assertTrue(encoded.contains("\"content\":\"Test\""))
        assertTrue(encoded.contains("\"accent_color\":\"#FFFFFF\"")) // Assuming ColorHexSerializer outputs hex string
    }
}
