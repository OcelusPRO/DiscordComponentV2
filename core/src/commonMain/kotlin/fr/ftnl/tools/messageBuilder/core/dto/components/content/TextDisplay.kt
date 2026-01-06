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
class TextDisplay(
    var content: String
) : DiscordComponent, ContainerChildComponent, SectionChildComponent {
    
    @JsName("createFull") constructor(
        id: Int? = null,
        content: String
    ) : this(content) {
        this.id = id
    }
    
    override var id: Int? = null
    override val type: Int = 10
    
    fun setId(id: Int?): TextDisplay {
        this.id = id
        return this
    }
}