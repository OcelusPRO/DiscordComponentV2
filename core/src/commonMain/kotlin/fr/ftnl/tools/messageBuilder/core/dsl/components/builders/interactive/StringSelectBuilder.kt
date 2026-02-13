@file:JsExport
@file:OptIn(ExperimentalJsExport::class)

package fr.ftnl.tools.messageBuilder.core.dsl.components.builders.interactive

import fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
import fr.ftnl.tools.messageBuilder.core.dsl.components.dsl.MessageComponentBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.dsl.ModalComponentBuilder
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.SelectOption
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.StringSelect
import fr.ftnl.tools.messageBuilder.core.dto.components.utils.DiscordEmoji
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport


@ComponentDsl
class StringSelectBuilder(private val customId: String): MessageComponentBuilder, ModalComponentBuilder {
    var placeholder: String? = null
    var minValues: Int = 1
    var maxValues: Int = 1
    var disabled: Boolean = false
    var required: Boolean = true
    private val options = mutableListOf<SelectOption>()
    
    fun option(label: String, value: String, block: SelectOptionBuilder.() -> Unit = {}) {
        val builder = SelectOptionBuilder(label, value)
        builder.block()
        options.add(builder.build())
    }
    
    override fun build(): StringSelect {
        return StringSelect(
            customId = customId,
            options = options,
            placeholder = placeholder,
            minValues = minValues,
            maxValues = maxValues,
            disabled = disabled,
            required = required
        )
    }
}


@ComponentDsl
class SelectOptionBuilder(private val label: String, private val value: String) {
    var description: String? = null
    var emoji: DiscordEmoji? = null
    var default: Boolean = false
    
    fun build(): SelectOption {
        return SelectOption(
            label = label,
            value = value,
            description = description,
            emoji = emoji,
            default = default
        )
    }
}