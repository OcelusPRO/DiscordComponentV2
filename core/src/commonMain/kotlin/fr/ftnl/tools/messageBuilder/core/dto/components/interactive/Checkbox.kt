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
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
class Checkbox(
    @SerialName("custom_id") var customId: String,
) : DiscordComponent, ModalCompatibleComponent, LabelChildComponent {
    
    @JsName("createFull") constructor(
        id: Int? = null,
        customId: String,
        default: Boolean? = null
    ) : this(customId) {
        this.id = id
        this.default = default
    }
    
    override var id: Int? = null
    override val type: Int = 23
    var default: Boolean? = null
    
    fun setId(id: Int?): Checkbox {
        this.id = id
        return this
    }
    
    fun setDefault(default: Boolean?): Checkbox {
        this.default = default
        return this
    }
    
    
    
}