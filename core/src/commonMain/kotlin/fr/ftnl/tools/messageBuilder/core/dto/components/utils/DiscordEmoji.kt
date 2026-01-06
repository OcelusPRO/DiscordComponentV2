@file:JsExport
@file:OptIn(ExperimentalJsExport::class)

package fr.ftnl.tools.messageBuilder.core.dto.components.utils

import kotlinx.serialization.Serializable
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
class DiscordEmoji() {
    @JsName("createFull") constructor(id: Long?, name: String?, animated: Boolean?) : this() {
        this.id = id
        this.name = name
        this.animated = animated
    }
    
    var id: Long? = null
    var name: String? = null
    var animated: Boolean? = null
    
    fun setId(id: Long?): DiscordEmoji {
        this.id = id
        return this
    }
    
    fun setName(name: String?): DiscordEmoji {
        this.name = name
        return this
    }
    
    fun setAnimated(animated: Boolean?): DiscordEmoji {
        this.animated = animated
        return this
    }
}