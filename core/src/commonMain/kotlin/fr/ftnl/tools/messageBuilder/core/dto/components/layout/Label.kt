@file:JsExport
@file:OptIn(ExperimentalJsExport::class)

package fr.ftnl.tools.messageBuilder.core.dto.components.layout

import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.LabelChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.ModalCompatibleComponent
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.Serializable
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
class Label(
    var label: String,
    var component: LabelChildComponent
) : DiscordComponent, ModalCompatibleComponent {
    
    @JsName("createFull") constructor(id: Int? = null, label: String, description: String? = null, component: LabelChildComponent) : this(label, component) {
        this.setId(id)
        this.setDescription(description)
    }
    
    override var id: Int? = null
    var description: String? = null
    @EncodeDefault override val type: Int = 18
    
    fun setId(id: Int?): Label {
        this.id = id
        return this
    }
    
    fun setDescription(description: String?): Label {
        this.description = description
        return this
    }
    
    fun setComponent(component: LabelChildComponent): Label {
        this.component = component
        return this
    }
}