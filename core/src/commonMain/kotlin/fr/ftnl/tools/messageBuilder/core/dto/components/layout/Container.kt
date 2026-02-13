@file:JsExport
@file:OptIn(ExperimentalJsExport::class)

package fr.ftnl.tools.messageBuilder.core.dto.components.layout

import fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildList
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.MessageCompatibleComponent
import fr.ftnl.tools.messageBuilder.core.serializers.ColorHexSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
class Container() : DiscordComponent, MessageCompatibleComponent {
    
    @JsName("createFull") constructor(
        id: Int? = null,
        components: List<ContainerChildComponent> = emptyList(),
        accentColor: Int? = null,
        spoiler: Boolean = false
    ) : this() {
        this.setId(id)
        this.setComponents(components)
        this.setAccentColor(accentColor)
        this.setSpoiler(spoiler)
    }
    
    override var id: Int? = null
    private var components: ContainerChildList = ContainerChildList()
    @Serializable(with = ColorHexSerializer::class)
    @SerialName("accent_color")
    var accentColor: Int? = null // RGB int 0x000000
    var spoiler: Boolean = false
    override val type: Int = 17
    
    fun setId(id: Int?): Container {
        this.id = id
        return this
    }
    
    fun addComponent(component: ContainerChildComponent): Container {
        this.components = ContainerChildList(this.components.elements + component)
        return this
    }
    
    fun setComponents(components: List<ContainerChildComponent>): Container {
        this.components = ContainerChildList(components)
        return this
    }
    
    fun setAccentColor(accentColor: Int?): Container {
        this.accentColor = accentColor
        return this
    }
    
    fun setSpoiler(spoiler: Boolean): Container {
        this.spoiler = spoiler
        return this
    }
}