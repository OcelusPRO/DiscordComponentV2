@file:JsExport
@file:OptIn(ExperimentalJsExport::class)

package fr.ftnl.tools.messageBuilder.core.dto.components.interactive

import fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
abstract class BaseEntitySelect : DiscordComponent, ContainerChildComponent {
    @SerialName("custom_id") abstract var customId: String
    abstract var placeholder: String?
    @SerialName("min_values") abstract var minValues: Int
    @SerialName("max_values") abstract var maxValues: Int
    abstract var disabled: Boolean
    abstract var required: Boolean
    @SerialName("default_values") abstract var defaultValues: MutableList<SelectDefaultValue>?
    
    fun setPlaceholder(placeholder: String?): BaseEntitySelect {
        this.placeholder = placeholder
        return this
    }
    
    fun setMinValues(minValues: Int): BaseEntitySelect {
        this.minValues = minValues
        return this
    }
    
    fun setMaxValues(maxValues: Int): BaseEntitySelect {
        this.maxValues = maxValues
        return this
    }
    
    fun setDisabled(disabled: Boolean): BaseEntitySelect {
        this.disabled = disabled
        return this
    }
    
    fun setRequired(required: Boolean): BaseEntitySelect {
        this.required = required
        return this
    }
    
    fun addDefaultValue(value: SelectDefaultValue): BaseEntitySelect {
        if (this.defaultValues == null) this.defaultValues = mutableListOf()
        this.defaultValues?.add(value)
        return this
    }
}

@Serializable
class UserSelect(@SerialName("custom_id") override var customId: String) : BaseEntitySelect() {
    @JsName("createFull") constructor(
        id: Int? = null,
        customId: String,
        placeholder: String? = null,
        minValues: Int = 1,
        maxValues: Int = 1,
        disabled: Boolean = false,
        required: Boolean = true,
        defaultValues: List<SelectDefaultValue>? = null
    ) : this(customId) {
        this.id = id
        this.placeholder = placeholder
        this.minValues = minValues
        this.maxValues = maxValues
        this.disabled = disabled
        this.required = required
        this.defaultValues = defaultValues?.toMutableList()
    }
    
    override var id: Int? = null
    override var placeholder: String? = null
    override var minValues: Int = 1
    override var maxValues: Int = 1
    override var disabled: Boolean = false
    override var required: Boolean = true
    override var defaultValues: MutableList<SelectDefaultValue>? = null
    override val type: Int = 5
    
    fun setId(id: Int?): UserSelect {
        this.id = id
        return this
    }
}

@Serializable
class RoleSelect(@SerialName("custom_id") override var customId: String) : BaseEntitySelect() {
    @JsName("createFull") constructor(
        id: Int? = null,
        customId: String,
        placeholder: String? = null,
        minValues: Int = 1,
        maxValues: Int = 1,
        disabled: Boolean = false,
        required: Boolean = true,
        defaultValues: List<SelectDefaultValue>? = null
    ) : this(customId) {
        this.id = id
        this.placeholder = placeholder
        this.minValues = minValues
        this.maxValues = maxValues
        this.disabled = disabled
        this.required = required
        this.defaultValues = defaultValues?.toMutableList()
    }
    
    override var id: Int? = null
    override var placeholder: String? = null
    override var minValues: Int = 1
    override var maxValues: Int = 1
    override var disabled: Boolean = false
    override var required: Boolean = true
    override var defaultValues: MutableList<SelectDefaultValue>? = null
    override val type: Int = 6
    
    fun setId(id: Int?): RoleSelect {
        this.id = id
        return this
    }
}

@Serializable
class MentionableSelect(@SerialName("custom_id") override var customId: String) : BaseEntitySelect() {
    @JsName("createFull") constructor(
        id: Int? = null,
        customId: String,
        placeholder: String? = null,
        minValues: Int = 1,
        maxValues: Int = 1,
        disabled: Boolean = false,
        required: Boolean = true,
        defaultValues: List<SelectDefaultValue>? = null
    ) : this(customId) {
        this.id = id
        this.placeholder = placeholder
        this.minValues = minValues
        this.maxValues = maxValues
        this.disabled = disabled
        this.required = required
        this.defaultValues = defaultValues?.toMutableList()
    }
    
    override var id: Int? = null
    override var placeholder: String? = null
    override var minValues: Int = 1
    override var maxValues: Int = 1
    override var disabled: Boolean = false
    override var required: Boolean = true
    override var defaultValues: MutableList<SelectDefaultValue>? = null
    override val type: Int = 7
    
    fun setId(id: Int?): MentionableSelect {
        this.id = id
        return this
    }
}

@Serializable
class ChannelSelect(@SerialName("custom_id") override var customId: String) : BaseEntitySelect() {
    @JsName("createFull") constructor(
        id: Int? = null,
        customId: String,
        channelTypes: List<Int>? = null,
        placeholder: String? = null,
        minValues: Int = 1,
        maxValues: Int = 1,
        disabled: Boolean = false,
        required: Boolean = true,
        defaultValues: List<SelectDefaultValue>? = null
    ) : this(customId) {
        this.id = id
        this.channelTypes = channelTypes?.toMutableList()
        this.placeholder = placeholder
        this.minValues = minValues
        this.maxValues = maxValues
        this.disabled = disabled
        this.required = required
        this.defaultValues = defaultValues?.toMutableList()
    }
    
    override var id: Int? = null
    
    @SerialName("channel_types")
    var channelTypes: MutableList<Int>? = null
    
    override var placeholder: String? = null
    override var minValues: Int = 1
    override var maxValues: Int = 1
    override var disabled: Boolean = false
    override var required: Boolean = true
    override var defaultValues: MutableList<SelectDefaultValue>? = null
    override val type: Int = 8
    
    fun setId(id: Int?): ChannelSelect {
        this.id = id
        return this
    }
    
    fun addChannelType(type: Int): ChannelSelect {
        if (this.channelTypes == null) this.channelTypes = mutableListOf()
        this.channelTypes?.add(type)
        return this
    }
}

@Serializable
class SelectDefaultValue(
    var id: String,
    var type: String
)