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
class ActionRow() : DiscordComponent, ContainerChildComponent {
    @JsName("createFull") constructor(id: Int? = null, components: List<DiscordComponent> = emptyList()) : this() {
        this.id = id
        this.components = components.toMutableList()
    }
    
    override var id: Int? = null
    var components: MutableList<DiscordComponent> = mutableListOf()
    override val type: Int = 1
    
    fun setId(id: Int?): ActionRow {
        this.id = id
        return this
    }
    
    fun addComponent(component: DiscordComponent): ActionRow {
        this.components.add(component)
        return this
    }
    
    fun setComponents(components: Array<DiscordComponent>): ActionRow {
        this.components = components.toMutableList()
        return this
    }
}