@file:JsExport
@file:OptIn(ExperimentalJsExport::class)

package fr.ftnl.tools.messageBuilder.core.dto.components.interactive

import fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import fr.ftnl.tools.messageBuilder.core.dto.components.utils.DiscordEmoji
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
class StringSelect(
    @SerialName("custom_id") var customId: String
) : DiscordComponent, ContainerChildComponent {
    
    @JsName("createFull") constructor(
        id: Int? = null,
        customId: String,
        options: List<SelectOption> = emptyList(),
        placeholder: String? = null,
        minValues: Int = 1,
        maxValues: Int = 1,
        required: Boolean = true,
        disabled: Boolean = false
    ) : this(customId) {
        this.id = id
        this.options = options.toMutableList()
        this.placeholder = placeholder
        this.minValues = minValues
        this.maxValues = maxValues
        this.required = required
        this.disabled = disabled
    }
    
    override var id: Int? = null
    var options: MutableList<SelectOption> = mutableListOf()
    var placeholder: String? = null
    
    @SerialName("min_values") var minValues: Int = 1
    @SerialName("max_values") var maxValues: Int = 1
    
    var required: Boolean = true
    var disabled: Boolean = false
    
    override val type: Int = 3
    
    fun setId(id: Int?): StringSelect {
        this.id = id
        return this
    }
    
    fun addOption(option: SelectOption): StringSelect {
        this.options.add(option)
        return this
    }
    
    fun setOptions(options: Array<SelectOption>): StringSelect {
        this.options = options.toMutableList()
        return this
    }
    
    fun setPlaceholder(placeholder: String?): StringSelect {
        this.placeholder = placeholder
        return this
    }
    
    fun setMinValues(minValues: Int): StringSelect {
        this.minValues = minValues
        return this
    }
    
    fun setMaxValues(maxValues: Int): StringSelect {
        this.maxValues = maxValues
        return this
    }
    
    fun setRequired(required: Boolean): StringSelect {
        this.required = required
        return this
    }
    
    fun setDisabled(disabled: Boolean): StringSelect {
        this.disabled = disabled
        return this
    }
}

@Serializable
class SelectOption(
    var label: String,
    var value: String
) {
    @JsName("createFull") constructor(
        label: String,
        value: String,
        description: String? = null,
        emoji: DiscordEmoji? = null,
        default: Boolean = false
    ) : this(label, value) {
        this.description = description
        this.emoji = emoji
        this.default = default
    }
    
    var description: String? = null
    var emoji: DiscordEmoji? = null
    var default: Boolean = false
    
    fun setDescription(description: String?): SelectOption {
        this.description = description
        return this
    }
    
    fun setEmoji(emoji: DiscordEmoji?): SelectOption {
        this.emoji = emoji
        return this
    }
    
    fun setDefault(default: Boolean): SelectOption {
        this.default = default
        return this
    }
}