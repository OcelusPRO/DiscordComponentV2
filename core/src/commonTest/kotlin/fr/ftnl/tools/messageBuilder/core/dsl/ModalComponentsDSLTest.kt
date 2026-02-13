package fr.ftnl.tools.messageBuilder.core.dsl

import fr.ftnl.tools.messageBuilder.core.dsl.components.dsl.modalComponents
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


class ModalComponentsDSLTest {
    
    @Test
    fun testModalComponentsDSL() {
        val components = modalComponents {
            // --- Contents ---
            textDisplay { }
            
            // --- Interactive ---
            checkbox("chk_custom_id") { }
            checkboxGroup("chk_grp_custom_id") { }
            radioGroup("rad_grp_custom_id") { }
            textInput("txt_in_custom_id", 1) { }
            stringSelect("str_select_id") { }
            userSelect("usr_select_id") { }
            roleSelect("role_select_id") { }
            mentionableSelect("ment_select_id") { }
            channelSelect("chan_select_id") { }
            
            // --- Layout ---
            label("Mon super label") { }
        }
        
        assertNotNull(components)
    }
}