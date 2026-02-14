package fr.ftnl.tools.messageBuilder.jda.ext.components

import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.Button
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.ButtonStyles
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.ActionRow
import fr.ftnl.tools.messageBuilder.jda.ext.components.layout.actionRow.toJda
import kotlin.test.Test
import kotlin.test.assertTrue

class ActionRowExtTest {
    @Test
    fun testActionRowToJda() {
        val row = ActionRow(components = listOf(
            Button(ButtonStyles.PRIMARY).setCustomId("btn").setLabel("OK")
        ))
        val jda = row.toJda()

        assertTrue(jda.components.isNotEmpty())
        assertTrue(jda.components.size == 1)
    }
}
