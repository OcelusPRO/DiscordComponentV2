@file:JsExport
@file:OptIn(ExperimentalJsExport::class)

package fr.ftnl.tools.messageBuilder.core.dto.components.layout

import fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import fr.ftnl.tools.messageBuilder.core.serializers.ColorHexSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
class Container() : DiscordComponent, ContainerChildComponent {
    
    @JsName("createFull") constructor(
        id: Int? = null,
        components: List<DiscordComponent> = emptyList(),
        accentColor: Int? = null,
        spoiler: Boolean = false
    ) : this() {
        this.id = id
        this.components = components.toMutableList()
        this.accentColor = accentColor
        this.spoiler = spoiler
    }
    
    override var id: Int? = null
    var components: MutableList<DiscordComponent> = mutableListOf()
    @Serializable(with = ColorHexSerializer::class)
    @SerialName("accent_color")
    var accentColor: Int? = null // RGB int 0x000000
    var spoiler: Boolean = false
    override val type: Int = 17
    
    fun setId(id: Int?): Container {
        this.id = id
        return this
    }
    
    fun addComponent(component: DiscordComponent): Container {
        this.components.add(component)
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