import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.ButtonStyles
import kotlin.test.Test
import kotlin.test.assertTrue

class JsDslTest {
    @Test
    fun testMessageDslToJson() {
        val components = JsDsl.message { m ->
            m.text("Hello")
            m.actionRow { row ->
                row.button("b1", ButtonStyles.PRIMARY) { b ->
                    b.label = "OK"
                }
            }
        }
        val json = JsDsl.toJsonStringArray(components, pretty = false)
        assertTrue(json.contains("\"type\":10"))
        assertTrue(json.contains("\"type\":1"))
        assertTrue(json.contains("\"custom_id\":\"b1\""))
    }

    @Test
    fun testModalDslToJson() {
        val components = JsDsl.modal { m ->
            m.label("Name") { label ->
                label.textInput("input", 1)
            }
        }
        val json = JsDsl.toJsonStringArray(components)
        assertTrue(json.contains("\"type\":18"))
        assertTrue(json.contains("\"custom_id\":\"input\""))
    }
}
