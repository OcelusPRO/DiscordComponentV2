package fr.ftnl.tools.messageBuilder.core.dsl.components.builders.interactive

import fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
import fr.ftnl.tools.messageBuilder.core.dsl.components.dsl.MessageComponentBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.dsl.ModalComponentBuilder
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.Button
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.Checkbox
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.CheckboxGroup
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.TextInput

@ComponentDsl
class TextInputBuilder(private val customId: String, private val style: Int): ModalComponentBuilder {
    var minLength: Int? = null
    var maxLength: Int? = null
    var required: Boolean = true
    var value: String? = null
    var placeholder: String? = null
    
    override fun build(): TextInput {
        return TextInput(
            customId = customId,
            style = style,
            minLength = minLength,
            maxLength = maxLength,
            required = required,
            value = value,
            placeholder = placeholder
        )
    }
}