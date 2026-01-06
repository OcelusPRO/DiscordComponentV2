@file:JsExport
@file:OptIn(ExperimentalJsExport::class)

package fr.ftnl.tools.messageBuilder.core.dto.components.content

import fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import kotlinx.serialization.Serializable
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
class FileComponent(
    var file: UnfurledMediaItem
) : DiscordComponent, ContainerChildComponent {
    
    @JsName("createFull") constructor(
        id: Int? = null,
        file: UnfurledMediaItem,
        spoiler: Boolean = false,
        name: String? = null,
        size: Int? = null
    ) : this(file) {
        this.id = id
        this.spoiler = spoiler
        this.name = name
        this.size = size
    }
    
    override var id: Int? = null
    var spoiler: Boolean = false
    var name: String? = null
    var size: Int? = null
    
    override val type: Int = 13
    
    fun setId(id: Int?): FileComponent {
        this.id = id
        return this
    }
    
    fun setSpoiler(spoiler: Boolean): FileComponent {
        this.spoiler = spoiler
        return this
    }
    
    fun setName(name: String?): FileComponent {
        this.name = name
        return this
    }
    
    fun setSize(size: Int?): FileComponent {
        this.size = size
        return this
    }
}