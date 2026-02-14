package fr.ftnl.tools.messageBuilder.core.dto.components.layout

import fr.ftnl.tools.messageBuilder.core.interfaces.components.ActionRowChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.ActionRowChildList
import fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.MessageCompatibleComponent
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.Serializable
import kotlin.js.JsName

@Serializable
class ActionRow() : DiscordComponent, ContainerChildComponent, MessageCompatibleComponent {
    @JsName("createFull") constructor(id: Int? = null, components: List<ActionRowChildComponent> = emptyList()) : this() {
        this.setId(id)
        this.setComponents(components)
    }
    
    override var id: Int? = null
    var components: ActionRowChildList = ActionRowChildList()
    @EncodeDefault override val type: Int = 1
    
    fun setId(id: Int?): ActionRow {
        this.id = id
        return this
    }
    
    fun addComponent(component: ActionRowChildComponent): ActionRow {
        this.components = ActionRowChildList(this.components.elements + component)
        return this
    }
    
    fun setComponents(components: List<ActionRowChildComponent>): ActionRow {
        this.components = ActionRowChildList(components)
        return this
    }
}