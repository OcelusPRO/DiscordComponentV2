@file:JsExport
@file:OptIn(ExperimentalJsExport::class)

package fr.ftnl.tools.messageBuilder.core.dto.components.layout

import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import kotlinx.serialization.Serializable
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
class Label(
    var label: String,
    var component: DiscordComponent
) : DiscordComponent {
    
    @JsName("createFull") constructor(id: Int? = null, label: String, description: String? = null, component: DiscordComponent) : this(label, component) {
        this.id = id
        this.description = description
    }
    
    override var id: Int? = null
    var description: String? = null
    
    override val type: Int = 18
    
    fun setId(id: Int?): Label {
        this.id = id
        return this
    }
    
    fun setDescription(description: String?): Label {
        this.description = description
        return this
    }
}