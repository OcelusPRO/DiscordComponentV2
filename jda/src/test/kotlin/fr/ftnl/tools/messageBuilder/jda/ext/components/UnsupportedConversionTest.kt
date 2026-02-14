package fr.ftnl.tools.messageBuilder.jda.ext.components

import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.Checkbox
import kotlin.test.Test
import kotlin.test.assertFailsWith

class UnsupportedConversionTest {
    @Test
    fun testUnsupportedCheckboxConversion() {
        assertFailsWith<IllegalArgumentException> {
            Checkbox("check").toJdaModalComponents()
        }
    }
}
