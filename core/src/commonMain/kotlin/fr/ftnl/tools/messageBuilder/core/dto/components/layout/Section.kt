@file:JsExport
@file:OptIn(ExperimentalJsExport::class)

package fr.ftnl.tools.messageBuilder.core.dto.components.layout

import fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.MessageCompatibleComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.SectionAccessoryComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.SectionChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.SectionChildList
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.Serializable
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
class Section() : DiscordComponent, ContainerChildComponent, MessageCompatibleComponent {
    
    @JsName("createFull") constructor(id: Int? = null, components: List<SectionChildComponent> = emptyList(), accessory: SectionAccessoryComponent) : this() {
        this.setId(id)
        this.setComponents(components)
        this.setAccessory(accessory)
    }
    
    override var id: Int? = null
    var components: SectionChildList = SectionChildList()
    var accessory: SectionAccessoryComponent? = null
    @EncodeDefault override val type: Int = 9
    
    fun setId(id: Int?): Section {
        this.id = id
        return this
    }
    
    fun addComponent(component: SectionChildComponent): Section {
        this.components = SectionChildList(this.components.elements + component)
        return this
    }
    
    fun setComponents(components: List<SectionChildComponent>): Section {
        this.components = SectionChildList(components)
        return this
    }
    
    fun setAccessory(accessory: SectionAccessoryComponent?): Section {
        this.accessory = accessory
        return this
    }

}