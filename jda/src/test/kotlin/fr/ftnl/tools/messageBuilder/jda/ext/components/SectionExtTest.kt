package fr.ftnl.tools.messageBuilder.jda.ext.components

import fr.ftnl.tools.messageBuilder.core.dto.components.content.TextDisplay
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.Button
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.ButtonStyles
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.Section
import fr.ftnl.tools.messageBuilder.jda.ext.components.layout.section.toJda
import kotlin.test.Test
import kotlin.test.assertNotNull

class SectionExtTest {
    @Test
    fun testSectionToJda() {
        val section = Section()
            .addComponent(TextDisplay("Hello"))
            .setAccessory(Button(ButtonStyles.PRIMARY).setCustomId("acc").setLabel("Open"))

        val jda = section.toJda()

        assertNotNull(jda.accessory)
    }
}
