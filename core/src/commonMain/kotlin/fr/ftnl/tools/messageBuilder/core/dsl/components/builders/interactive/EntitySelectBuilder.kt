package fr.ftnl.tools.messageBuilder.core.dsl.components.builders.interactive

import fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
import fr.ftnl.tools.messageBuilder.core.dsl.components.dsl.MessageComponentBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.dsl.ModalComponentBuilder
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.BaseEntitySelect
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.ChannelSelect
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.MentionableSelect
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.RoleSelect
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.SelectDefaultValue
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.UserSelect

@ComponentDsl
class UserSelectBuilder(private val customId: String) : BaseEntitySelectBuilder<UserSelect>() {
    override fun build(): UserSelect {
        return UserSelect(
            customId = customId,
            placeholder = placeholder,
            minValues = minValues,
            maxValues = maxValues,
            disabled = disabled,
            required = required,
            defaultValues = defaultValues
        )
    }
}

@ComponentDsl
class RoleSelectBuilder(private val customId: String) : BaseEntitySelectBuilder<RoleSelect>() {
    override fun build(): RoleSelect {
        return RoleSelect(
            customId = customId,
            placeholder = placeholder,
            minValues = minValues,
            maxValues = maxValues,
            disabled = disabled,
            required = required,
            defaultValues = defaultValues
        )
    }
}

@ComponentDsl
class MentionableSelectBuilder(private val customId: String) : BaseEntitySelectBuilder<MentionableSelect>() {
    override fun build(): MentionableSelect {
        return MentionableSelect(
            customId = customId,
            placeholder = placeholder,
            minValues = minValues,
            maxValues = maxValues,
            disabled = disabled,
            required = required,
            defaultValues = defaultValues
        )
    }
}

@ComponentDsl
class ChannelSelectBuilder(private val customId: String) : BaseEntitySelectBuilder<ChannelSelect>() {
    var channelTypes: List<Int>? = null
    
    override fun build(): ChannelSelect {
        return ChannelSelect(
            customId = customId,
            channelTypes = channelTypes,
            placeholder = placeholder,
            minValues = minValues,
            maxValues = maxValues,
            disabled = disabled,
            required = required,
            defaultValues = defaultValues
        )
    }
}

@ComponentDsl
abstract class BaseEntitySelectBuilder<T : BaseEntitySelect>: ModalComponentBuilder, MessageComponentBuilder {
    var placeholder: String? = null
    var minValues: Int = 1
    var maxValues: Int = 1
    var disabled: Boolean = false
    var required: Boolean = true
    var defaultValues: List<SelectDefaultValue>? = null
    
    abstract override fun build(): T
}