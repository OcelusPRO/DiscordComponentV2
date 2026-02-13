@file:JsExport
@file:OptIn(ExperimentalJsExport::class)

package fr.ftnl.tools.messageBuilder.core.dto.components.interactive

import fr.ftnl.tools.messageBuilder.core.interfaces.components.ActionRowChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.ModalCompatibleComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.SectionChildComponent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.math.max
import kotlin.math.min

@Serializable
class CheckboxGroup(
    @SerialName("custom_id") var customId: String,
) : DiscordComponent, ContainerChildComponent, ActionRowChildComponent, SectionChildComponent, ModalCompatibleComponent {
    
    @JsName("createFull") constructor(
        id: Int? = null,
        customId: String,
        options: List<CheckboxGroupOption> = emptyList(),
        required: Boolean? = null,
        minValues: Int = 1,
        maxValues: Int = 10
    ) : this(customId) {
        this.id = id
        this.setOptions(options.toTypedArray())
        this.setRequired(required)
        this.setValueRange(minValues, maxValues)
    }
    
    override var id: Int? = null
    override val type: Int = 22
    
    @SerialName("min_values") var minValues: Int = 1
    @SerialName("max_values") var maxValues: Int = 10
    var required: Boolean? = true
    var options: MutableList<CheckboxGroupOption> = mutableListOf()
    
    fun setId(id: Int?): CheckboxGroup {
        this.id = id
        return this
    }
    
    fun setMinValues(minValues: Int): CheckboxGroup {
        this.minValues = max(0, min(10, minValues))
        return this
    }
    fun setMaxValues(maxValues: Int): CheckboxGroup {
        this.maxValues = min(10, max(1, maxValues))
        return this
    }
    fun setValueRange(minValues: Int, maxValues: Int): CheckboxGroup {
        this.setMinValues(minValues)
        this.setMaxValues(maxValues)
        return this
    }
    @JsName("setValueIntRange") fun setValueRange(valueRange: IntRange): CheckboxGroup {
        val min = valueRange.min()
        val max = valueRange.max()
        return setValueRange(min, max)
    }
    
    fun setRequired(required: Boolean?): CheckboxGroup {
        this.required = required
        return this
    }
    

    fun addOptions(vararg newOptions: CheckboxGroupOption): CheckboxGroup {
        require(newOptions.size + this.options.size <= 10) { "Checkbox group can't have more than 10 options" }
        this.options.addAll(newOptions)
        return this
    }
    fun setOptions(newOptions: Array<CheckboxGroupOption>): CheckboxGroup {
        require(newOptions.size <= 10) { "Checkbox group can't have more than 10 options" }
        require(newOptions.isNotEmpty()) { "Checkbox group can't have less than 1 option" }
        this.options = options.toMutableList()
        return this
    }
    
    
}

@Serializable
data class CheckboxGroupOption(
    val value: String,
    val label: String,
    val description: String? = null,
    val default: Boolean = false
)