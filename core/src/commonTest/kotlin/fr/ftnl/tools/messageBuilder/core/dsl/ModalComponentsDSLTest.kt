package fr.ftnl.tools.messageBuilder.core.dsl

import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.SelectDefaultValue
import fr.ftnl.tools.messageBuilder.core.dsl.components.dsl.modalComponents
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class ModalComponentsDSLTest {
    private val json = Json { encodeDefaults = true }

    @Test
    fun testModalComponentsDSL() {
        val components = modalComponents {
            label("User") {
                textInput("user_input", 1) {
                    placeholder = "Name"
                    minLength = 1
                    maxLength = 32
                    required = false
                }
            }
            textDisplay {
                content = "Modal text"
            }
            checkbox("agree") {
                default = true
            }
            checkboxGroup("group") {
                required = false
                minValues = 1
                maxValues = 2
                option("v1", "Option 1", description = "desc")
                option("v2", "Option 2", default = true)
            }
            radioGroup("radio") {
                required = true
                option("a", "A")
                option("b", "B", default = true)
            }
            textInput("notes", 2) {
                placeholder = "Notes"
                value = "Hello"
            }
            fileUpload("upload") {
                minValues = 2
                maxValues = 5
                required = false
            }
            stringSelect("select") {
                placeholder = "Pick"
                option("Label", "value")
            }
            userSelect("user") {
                placeholder = "User"
                defaultValues = listOf(SelectDefaultValue("123", "user"))
            }
            roleSelect("role") {
                placeholder = "Role"
            }
            mentionableSelect("mention") {
                placeholder = "Mention"
            }
            channelSelect("channel") {
                channelTypes = listOf(0)
                placeholder = "Channel"
            }
        }

        assertNotNull(components)

        val encoded = json.encodeToString(components)
        assertTrue(encoded.contains("\"type\":18"))
        assertTrue(encoded.contains("\"type\":4"))
        assertTrue(encoded.contains("\"custom_id\":\"user_input\""))
        assertTrue(encoded.contains("\"type\":10"))
        assertTrue(encoded.contains("\"type\":23"))
        assertTrue(encoded.contains("\"type\":22"))
        assertTrue(encoded.contains("\"type\":21"))
        assertTrue(encoded.contains("\"type\":19"))
        assertTrue(encoded.contains("\"type\":3"))
        assertTrue(encoded.contains("\"type\":5"))
        assertTrue(encoded.contains("\"type\":6"))
        assertTrue(encoded.contains("\"type\":7"))
        assertTrue(encoded.contains("\"type\":8"))
    }
}
