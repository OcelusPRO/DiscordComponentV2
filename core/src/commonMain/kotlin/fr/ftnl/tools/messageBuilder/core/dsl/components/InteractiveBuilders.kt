package fr.ftnl.tools.messageBuilder.core.dsl.components

import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.BaseEntitySelect
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.Button
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.ChannelSelect
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.MentionableSelect
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.RoleSelect
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.SelectDefaultValue
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.SelectOption
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.StringSelect
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.TextInput
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.UserSelect
import fr.ftnl.tools.messageBuilder.core.dto.components.utils.DiscordEmoji

@ComponentDsl
class ButtonBuilder(private val style: Int) {
    var label: String? = null
    var customId: String? = null
    var url: String? = null
    var emoji: fr.ftnl.tools.messageBuilder.core.dto.components.utils.DiscordEmoji? = null
    var disabled: Boolean = false
    var skuId: String? = null

    fun build(): Button {
        return Button(
            style = style, label = label, emoji = emoji, customId = customId, url = url, skuId = skuId, disabled = disabled
        )
    }
}

@ComponentDsl
class StringSelectBuilder(private val customId: String) {
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

    fun build(): StringSelect {
        return StringSelect(
            customId = customId, options = options, placeholder = placeholder, minValues = minValues, maxValues = maxValues, disabled = disabled, required = required
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
            label = label, value = value, description = description, emoji = emoji, default = default
        )
    }
}

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
abstract class BaseEntitySelectBuilder<T : BaseEntitySelect> {
    var placeholder: String? = null
    var minValues: Int = 1
    var maxValues: Int = 1
    var disabled: Boolean = false
    var required: Boolean = true
    var defaultValues: List<SelectDefaultValue>? = null

    abstract fun build(): T
}

@ComponentDsl
class TextInputBuilder(private val customId: String, private val style: Int) {
    var minLength: Int? = null
    var maxLength: Int? = null
    var required: Boolean = true
    var value: String? = null
    var placeholder: String? = null

    fun build(): TextInput {
        return TextInput(
            customId = customId, style = style, minLength = minLength, maxLength = maxLength, required = required, value = value, placeholder = placeholder
        )
    }
}
