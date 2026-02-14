package fr.ftnl.tools.messageBuilder.core.dto.components.layout

import fr.ftnl.tools.messageBuilder.core.dto.components.content.TextDisplay
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.Button
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.ButtonStyles
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.TextInput
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertTrue

class LayoutComponentsTest {
    private val json = Json { encodeDefaults = true }

    @Test
    fun testActionRowSerialization() {
        val row = ActionRow(components = listOf(Button(ButtonStyles.PRIMARY).setCustomId("b")))
        val encoded = json.encodeToString(row)

        assertTrue(encoded.contains("\"type\":1"))
        assertTrue(encoded.contains("\"components\":["))
        assertTrue(encoded.contains("\"custom_id\":\"b\""))
    }

    @Test
    fun testSectionSerialization() {
        val section = Section()
            .addComponent(TextDisplay("Hello"))
            .setAccessory(Button(ButtonStyles.SECONDARY).setCustomId("a"))
        val encoded = json.encodeToString(section)

        assertTrue(encoded.contains("\"type\":9"))
        assertTrue(encoded.contains("\"components\":["))
        assertTrue(encoded.contains("\"content\":\"Hello\""))
    }

    @Test
    fun testSeparatorSerialization() {
        val separator = Separator().setDivider(false).setSpacing(SpacingType.LARGE)
        val encoded = json.encodeToString(separator)

        assertTrue(encoded.contains("\"type\":14"))
        assertTrue(encoded.contains("\"divider\":false"))
        assertTrue(encoded.contains("\"spacing\":2"))
    }

    @Test
    fun testLabelSerialization() {
        val label = Label("Label", TextInput("input", 1))
        val encoded = json.encodeToString(label)

        assertTrue(encoded.contains("\"type\":18"))
        assertTrue(encoded.contains("\"label\":\"Label\""))
        assertTrue(encoded.contains("\"custom_id\":\"input\""))
    }
}

