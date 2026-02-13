package fr.ftnl.tools.messageBuilder.core.dsl.components.builders.interactive

import fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
import fr.ftnl.tools.messageBuilder.core.dsl.components.dsl.MessageComponentBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.dsl.ModalComponentBuilder
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.Button
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.Checkbox

@ComponentDsl
class CheckboxBuilder(val customId: String): ModalComponentBuilder {
    
    var default: Boolean = false
    var id: Int? = null
    
    
    override fun build(): Checkbox {
        return Checkbox(
            customId = customId,
            default = default
        )
    }
    
}