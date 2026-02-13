@file:JsExport
@file:OptIn(ExperimentalJsExport::class)

package fr.ftnl.tools.messageBuilder.core.dsl.components.builders.interactive

import fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
import fr.ftnl.tools.messageBuilder.core.dsl.components.dsl.MessageComponentBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.dsl.ModalComponentBuilder
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.Button
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.Checkbox
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.CheckboxGroup
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.RadioGroup
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.RadioGroupOption
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@ComponentDsl
class RadioGroupBuilder(val customId: String): ModalComponentBuilder {
    
    private val radioElements = mutableListOf<RadioGroupOption>()
    var required: Boolean = true
    
    override fun build(): RadioGroup {
        require(radioElements.size in 2..10) { "Radio group can't have less than 2 or more than 10 options" }
        
        return RadioGroup(
            customId = customId,
            options = radioElements,
            required = required
        )
    }
    
}