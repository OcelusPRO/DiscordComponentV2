package fr.ftnl.tools.messageBuilder.core.dsl.components.builders.interactive

import fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
import fr.ftnl.tools.messageBuilder.core.dsl.components.dsl.MessageComponentBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.dsl.ModalComponentBuilder
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.Button
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.Checkbox
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.CheckboxGroup
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.CheckboxGroupOption
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.RadioGroup
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.RadioGroupOption
import kotlin.Int

@ComponentDsl
class CheckboxGroupBuilder(val customId: String): ModalComponentBuilder {
    
    private val checkboxElement = mutableListOf<CheckboxGroupOption>()
    var required: Boolean = true
    var minValues: Int = 1
    var maxValues: Int = 10
    
    
    override fun build(): CheckboxGroup {
        require(checkboxElement.size in 1..10) { "Radio group can't have less than 1 or more than 10 options" }
        
        return CheckboxGroup(
            customId = customId,
            options = checkboxElement,
            required = required,
            minValues = minValues,
            maxValues = maxValues
        )
    }
    
}