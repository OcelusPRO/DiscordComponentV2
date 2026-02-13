@file:JsExport
@file:OptIn(ExperimentalJsExport::class)

package fr.ftnl.tools.messageBuilder.core.dto.components.interactive

import fr.ftnl.tools.messageBuilder.core.interfaces.components.ActionRowChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.LabelChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.ModalCompatibleComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.SectionChildComponent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.collections.addAll
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
class RadioGroup(
    @SerialName("custom_id") var customId: String,
) : DiscordComponent, ModalCompatibleComponent, LabelChildComponent {
    
    @JsName("createFull") constructor(
        id: Int? = null,
        customId: String,
        required: Boolean? = true,
        options: List<RadioGroupOption>
    ) : this(customId) {
        this.id = id
        this.setRequired(required)
        this.setOptions(options.toTypedArray())
    }
    
    override var id: Int? = null
    override val type: Int = 21
    var required: Boolean? = true
    var options: MutableList<RadioGroupOption> = mutableListOf()
    
    fun setId(id: Int?): RadioGroup {
        this.id = id
        return this
    }
    
    fun setRequired(required: Boolean?): RadioGroup {
        this.required = required
        return this
    }
    
    fun addOptions(vararg newOptions: RadioGroupOption): RadioGroup {
        require(newOptions.size + this.options.size <= 10) { "Radio group can't have more than 10 options" }
        this.options.addAll(newOptions)
        return this
    }
    fun setOptions(newOptions: Array<RadioGroupOption>): RadioGroup {
        require(newOptions.size <= 10) { "Radio group can't have more than 10 options" }
        require(newOptions.size >= 2) { "Radio group can't have less than 2 options" }
        this.options = options.toMutableList()
        return this
    }
    
}

@Serializable
data class RadioGroupOption(
    val value: String,
    val label: String,
    val description: String? = null,
    val default: Boolean = false
)