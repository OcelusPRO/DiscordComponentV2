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
class RadioGroup(
    @SerialName("custom_id") var customId: String,
) : DiscordComponent, ContainerChildComponent, ActionRowChildComponent, SectionChildComponent, ModalCompatibleComponent {
    
    @JsName("createFull") constructor(
        id: Int? = null,
        customId: String,
    ) : this(customId) {
        this.id = id
    }
    
    override var id: Int? = null
    override val type: Int = 21
    // options: list radio group option min 2 max 10
    // required: true
    
}

data class RadioGroupOption(
    val value: String,
    val label: String,
    val description: String? = null,
    val default: Boolean = false
)