@file:JsExport
@file:OptIn(ExperimentalJsExport::class)

package fr.ftnl.tools.messageBuilder.core.dto.components.layout

import fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import kotlinx.serialization.Serializable
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
class Separator() : DiscordComponent, ContainerChildComponent {
    
    @JsName("createFull") constructor(id: Int? = null, divider: Boolean = true, spacing: Int = 1) : this() {
        this.id = id
        this.divider = divider
        this.spacing = spacing
    }
    
    override var id: Int? = null
    var divider: Boolean = true
    var spacing: Int = 1 // 1 (small) ou 2 (large)
    
    override val type: Int = 14
    
    fun setId(id: Int?): Separator {
        this.id = id
        return this
    }
    
    fun setDivider(divider: Boolean): Separator {
        this.divider = divider
        return this
    }
    
    fun setSpacing(spacing: Int): Separator {
        this.spacing = spacing
        return this
    }
}