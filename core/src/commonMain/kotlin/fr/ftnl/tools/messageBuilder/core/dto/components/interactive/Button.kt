@file:JsExport
@file:OptIn(ExperimentalJsExport::class)

package fr.ftnl.tools.messageBuilder.core.dto.components.interactive

import fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.SectionChildComponent
import fr.ftnl.tools.messageBuilder.core.dto.components.utils.DiscordEmoji
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
class Button(
    var style: Int
) : DiscordComponent, ContainerChildComponent, SectionChildComponent {
    
    @JsName("createFull") constructor(
        id: Int? = null,
        style: Int,
        label: String? = null,
        emoji: DiscordEmoji? = null,
        customId: String? = null,
        url: String? = null,
        skuId: String? = null,
        disabled: Boolean = false
    ) : this(style) {
        this.id = id
        this.label = label
        this.emoji = emoji
        this.customId = customId
        this.url = url
        this.skuId = skuId
        this.disabled = disabled
    }
    
    override var id: Int? = null
    var label: String? = null
    var emoji: DiscordEmoji? = null
    
    @SerialName("custom_id")
    var customId: String? = null
    
    var url: String? = null
    
    @SerialName("sku_id")
    var skuId: String? = null
    
    var disabled: Boolean = false
    
    override val type: Int = 2
    
    fun setId(id: Int?): Button {
        this.id = id
        return this
    }
    
    fun setLabel(label: String?): Button {
        this.label = label
        return this
    }
    
    fun setEmoji(emoji: DiscordEmoji?): Button {
        this.emoji = emoji
        return this
    }
    
    fun setCustomId(customId: String?): Button {
        this.customId = customId
        return this
    }
    
    fun setUrl(url: String?): Button {
        this.url = url
        return this
    }
    
    fun setSkuId(skuId: String?): Button {
        this.skuId = skuId
        return this
    }
    
    fun setDisabled(disabled: Boolean): Button {
        this.disabled = disabled
        return this
    }
}

@JsExport
object ButtonStyles {
    const val PRIMARY = 1
    const val SECONDARY = 2
    const val SUCCESS = 3
    const val DANGER = 4
    const val LINK = 5
    const val PREMIUM = 6
}