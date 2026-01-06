@file:JsExport
@file:OptIn(ExperimentalJsExport::class)

package fr.ftnl.tools.messageBuilder.core.dto.components.content

import fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.SectionChildComponent
import kotlinx.serialization.Serializable
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
class Thumbnail(
    var media: UnfurledMediaItem
) : DiscordComponent, ContainerChildComponent, SectionChildComponent {
    
    @JsName("createFull") constructor(
        id: Int? = null,
        media: UnfurledMediaItem,
        description: String? = null,
        spoiler: Boolean = false
    ) : this(media) {
        this.id = id
        this.description = description
        this.spoiler = spoiler
    }
    
    override var id: Int? = null
    var description: String? = null
    var spoiler: Boolean = false
    
    override val type: Int = 11
    
    fun setId(id: Int?): Thumbnail {
        this.id = id
        return this
    }
    
    fun setDescription(description: String?): Thumbnail {
        this.description = description
        return this
    }
    
    fun setSpoiler(spoiler: Boolean): Thumbnail {
        this.spoiler = spoiler
        return this
    }
}