@file:JsExport
@file:OptIn(ExperimentalJsExport::class)

package fr.ftnl.tools.messageBuilder.core.dto.components.interactive

import fr.ftnl.tools.messageBuilder.core.interfaces.components.ActionRowChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.SectionChildComponent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
class TextInput(
    @SerialName("custom_id") var customId: String,
    var style: Int
) : DiscordComponent, ContainerChildComponent, ActionRowChildComponent, SectionChildComponent {
    
    @JsName("createFull") constructor(
        id: Int? = null,
        customId: String,
        style: Int,
        minLength: Int? = null,
        maxLength: Int? = null,
        required: Boolean = true,
        value: String? = null,
        placeholder: String? = null
    ) : this(customId, style) {
        this.id = id
        this.minLength = minLength
        this.maxLength = maxLength
        this.required = required
        this.value = value
        this.placeholder = placeholder
    }
    
    override var id: Int? = null
    
    @SerialName("min_length") var minLength: Int? = null
    @SerialName("max_length") var maxLength: Int? = null
    
    var required: Boolean = true
    var value: String? = null
    var placeholder: String? = null
    
    override val type: Int = 4
    
    fun setId(id: Int?): TextInput {
        this.id = id
        return this
    }
    
    fun setMinLength(minLength: Int?): TextInput {
        this.minLength = minLength
        return this
    }
    
    fun setMaxLength(maxLength: Int?): TextInput {
        this.maxLength = maxLength
        return this
    }
    
    fun setRequired(required: Boolean): TextInput {
        this.required = required
        return this
    }
    
    fun setValue(value: String?): TextInput {
        this.value = value
        return this
    }
    
    fun setPlaceholder(placeholder: String?): TextInput {
        this.placeholder = placeholder
        return this
    }
}