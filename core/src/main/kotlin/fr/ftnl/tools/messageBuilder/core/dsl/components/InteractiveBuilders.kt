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

@fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
class ButtonBuilder(private val style: Int) {
    var label: String? = null
    var customId: String? = null
    var url: String? = null
    var emoji: fr.ftnl.tools.messageBuilder.core.dto.components.utils.DiscordEmoji? = null
    var disabled: Boolean = false
    var skuId: String? = null

    fun build(): fr.ftnl.tools.messageBuilder.core.dto.components.interactive.Button {
        return _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.interactive.Button(
            style = style, label = label, emoji = emoji, customId = customId, url = url, skuId = skuId, disabled = disabled
        )
    }
}

@fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
class StringSelectBuilder(private val customId: String) {
    var placeholder: String? = null
    var minValues: Int = 1
    var maxValues: Int = 1
    var disabled: Boolean = false
    var required: Boolean = true
    private val options = mutableListOf<fr.ftnl.tools.messageBuilder.core.dto.components.interactive.SelectOption>()

    fun option(label: String, value: String, block: fr.ftnl.tools.messageBuilder.core.dsl.components.SelectOptionBuilder.() -> Unit = {}) {
        val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.SelectOptionBuilder(label, value)
        builder.block()
        options.add(builder.build())
    }

    fun build(): fr.ftnl.tools.messageBuilder.core.dto.components.interactive.StringSelect {
        return _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.interactive.StringSelect(
            customId = customId, options = options, placeholder = placeholder, minValues = minValues, maxValues = maxValues, disabled = disabled, required = required
        )
    }
}

@fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
class SelectOptionBuilder(private val label: String, private val value: String) {
    var description: String? = null
    var emoji: fr.ftnl.tools.messageBuilder.core.dto.components.utils.DiscordEmoji? = null
    var default: Boolean = false

    fun build(): fr.ftnl.tools.messageBuilder.core.dto.components.interactive.SelectOption {
        return _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.interactive.SelectOption(
            label = label, value = value, description = description, emoji = emoji, default = default
        )
    }
}

@fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
class UserSelectBuilder(private val customId: String) : fr.ftnl.tools.messageBuilder.core.dsl.components.BaseEntitySelectBuilder<fr.ftnl.tools.messageBuilder.core.dto.components.interactive.UserSelect>() {
    override fun build(): fr.ftnl.tools.messageBuilder.core.dto.components.interactive.UserSelect {
        return _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.interactive.UserSelect(
            customId = customId,
            placeholder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseEntitySelectBuilder.placeholder,
            minValues = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseEntitySelectBuilder.minValues,
            maxValues = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseEntitySelectBuilder.maxValues,
            disabled = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseEntitySelectBuilder.disabled,
            required = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseEntitySelectBuilder.required,
            defaultValues = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseEntitySelectBuilder.defaultValues
        )
    }
}

@fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
class RoleSelectBuilder(private val customId: String) : fr.ftnl.tools.messageBuilder.core.dsl.components.BaseEntitySelectBuilder<fr.ftnl.tools.messageBuilder.core.dto.components.interactive.RoleSelect>() {
    override fun build(): fr.ftnl.tools.messageBuilder.core.dto.components.interactive.RoleSelect {
        return _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.interactive.RoleSelect(
            customId = customId,
            placeholder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseEntitySelectBuilder.placeholder,
            minValues = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseEntitySelectBuilder.minValues,
            maxValues = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseEntitySelectBuilder.maxValues,
            disabled = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseEntitySelectBuilder.disabled,
            required = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseEntitySelectBuilder.required,
            defaultValues = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseEntitySelectBuilder.defaultValues
        )
    }
}

@fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
class MentionableSelectBuilder(private val customId: String) : fr.ftnl.tools.messageBuilder.core.dsl.components.BaseEntitySelectBuilder<fr.ftnl.tools.messageBuilder.core.dto.components.interactive.MentionableSelect>() {
    override fun build(): fr.ftnl.tools.messageBuilder.core.dto.components.interactive.MentionableSelect {
        return _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.interactive.MentionableSelect(
            customId = customId,
            placeholder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseEntitySelectBuilder.placeholder,
            minValues = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseEntitySelectBuilder.minValues,
            maxValues = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseEntitySelectBuilder.maxValues,
            disabled = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseEntitySelectBuilder.disabled,
            required = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseEntitySelectBuilder.required,
            defaultValues = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseEntitySelectBuilder.defaultValues
        )
    }
}

@fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
class ChannelSelectBuilder(private val customId: String) : fr.ftnl.tools.messageBuilder.core.dsl.components.BaseEntitySelectBuilder<fr.ftnl.tools.messageBuilder.core.dto.components.interactive.ChannelSelect>() {
    var channelTypes: List<Int>? = null

    override fun build(): fr.ftnl.tools.messageBuilder.core.dto.components.interactive.ChannelSelect {
        return _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.interactive.ChannelSelect(
            customId = customId,
            channelTypes = channelTypes,
            placeholder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseEntitySelectBuilder.placeholder,
            minValues = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseEntitySelectBuilder.minValues,
            maxValues = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseEntitySelectBuilder.maxValues,
            disabled = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseEntitySelectBuilder.disabled,
            required = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseEntitySelectBuilder.required,
            defaultValues = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseEntitySelectBuilder.defaultValues
        )
    }
}

@fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
abstract class BaseEntitySelectBuilder<T : fr.ftnl.tools.messageBuilder.core.dto.components.interactive.BaseEntitySelect> {
    var placeholder: String? = null
    var minValues: Int = 1
    var maxValues: Int = 1
    var disabled: Boolean = false
    var required: Boolean = true
    var defaultValues: List<fr.ftnl.tools.messageBuilder.core.dto.components.interactive.SelectDefaultValue>? = null

    abstract fun build(): T
}

@fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
class TextInputBuilder(private val customId: String, private val style: Int) {
    var minLength: Int? = null
    var maxLength: Int? = null
    var required: Boolean = true
    var value: String? = null
    var placeholder: String? = null

    fun build(): fr.ftnl.tools.messageBuilder.core.dto.components.interactive.TextInput {
        return _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.interactive.TextInput(
            customId = customId, style = style, minLength = minLength, maxLength = maxLength, required = required, value = value, placeholder = placeholder
        )
    }
}
