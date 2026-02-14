package fr.ftnl.tools.messageBuilder.jda.ext.components

import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.Button
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.ButtonStyles
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.ActionRow
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.Container
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.Separator
import fr.ftnl.tools.messageBuilder.jda.ext.components.layout.container.toJda
import kotlin.test.Test
import kotlin.test.assertTrue

class ContainerExtTest {
    @Test
    fun testContainerToJda() {
        val row = ActionRow(components = listOf(
            Button(ButtonStyles.SECONDARY).setCustomId("b").setLabel("Go")
        ))
        val container = Container(components = listOf(row, Separator()))
            .setAccentColor(0xFF00FF)
            .setSpoiler(true)

        val jda = container.toJda()
        assertTrue(jda.components.isNotEmpty())
    }
}
