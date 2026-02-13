@file:JsExport
@file:OptIn(ExperimentalJsExport::class)

package fr.ftnl.tools.messageBuilder.core.dto.components.layout

import fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.MessageCompatibleComponent
import kotlinx.serialization.Serializable
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
class Separator() : DiscordComponent, ContainerChildComponent, MessageCompatibleComponent {
    
    @JsName("createFull") constructor(id: Int? = null, divider: Boolean = true, spacing: SpacingType = SpacingType.SMALL) : this() {
        this.id = id
        this.setDivider(divider)
        this.setSpacing(spacing)
    }
    
    @JsName("fromIntSpacing") constructor(id: Int? = null, divider: Boolean = true, spacing: Int = SpacingType.SMALL.value) : this() {
        this.id = id
        this.setDivider(divider)
        this.setSpacing(SpacingType.values().firstOrNull { it.value == spacing } ?: SpacingType.SMALL)
    }
    
    override var id: Int? = null
    var divider: Boolean = true
    var spacing: Int = SpacingType.SMALL.value // 1 (small) ou 2 (large)
    
    override val type: Int = 14
    
    fun setId(id: Int?): Separator {
        this.id = id
        return this
    }
    
    fun setDivider(divider: Boolean): Separator {
        this.divider = divider
        return this
    }
    
    fun setSpacing(spacing: SpacingType): Separator {
        this.spacing = spacing.value
        return this
    }
}

enum class SpacingType(val value: Int) {
    SMALL(1),
    LARGE(2)
}