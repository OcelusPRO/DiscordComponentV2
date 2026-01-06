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
class Section(
    var accessory: DiscordComponent
) : DiscordComponent, ContainerChildComponent {
    
    @JsName("createFull") constructor(id: Int? = null, components: List<DiscordComponent> = emptyList(), accessory: DiscordComponent) : this(accessory) {
        this.id = id
        this.components = components.toMutableList()
        this.accessory = accessory
    }
    
    override var id: Int? = null
    var components: MutableList<DiscordComponent> = mutableListOf()
    
    override val type: Int = 9
    
    fun setId(id: Int?): Section {
        this.id = id
        return this
    }
    
    fun addComponent(component: DiscordComponent): Section {
        this.components.add(component)
        return this
    }
    
    fun setComponents(components: List<DiscordComponent>): Section {
        this.components = components.toMutableList()
        return this
    }

}