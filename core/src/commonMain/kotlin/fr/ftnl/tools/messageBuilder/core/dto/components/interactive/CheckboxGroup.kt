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

@Serializable
class CheckboxGroup(
    @SerialName("custom_id") var customId: String,
) : DiscordComponent, ContainerChildComponent, ActionRowChildComponent, SectionChildComponent, ModalCompatibleComponent {
    
    @JsName("createFull") constructor(
        id: Int? = null,
        customId: String,
    ) : this(customId) {
        this.id = id
    }
    
    override var id: Int? = null
    override val type: Int = 22
    
    
    // options: list checkbox group option min 1 max 10
    // min_values: int? max item selected, 0 to 10 default 1, if 0 required must be false
    // max_values: int? max item selected, 1 to 10 default number of options
    // required: bool default true
    
    
}

data class CheckbocxGroupOption(
    val value: String,
    val label: String,
    val description: String? = null,
    val default: Boolean = false
)