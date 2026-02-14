package fr.ftnl.tools.messageBuilder.jda.ext.components

import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.TextInput
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.Label
import fr.ftnl.tools.messageBuilder.jda.ext.components.layout.label.toJda
import kotlin.test.Test
import kotlin.test.assertTrue

class LabelExtTest {
    @Test
    fun testLabelToJda() {
        val label = Label("Label", TextInput("input", 1))
        val jda = label.toJda()

        assertTrue(jda.label.isNotBlank())
    }
}
