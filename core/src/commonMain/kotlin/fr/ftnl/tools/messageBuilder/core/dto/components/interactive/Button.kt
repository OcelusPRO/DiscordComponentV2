@file:JsExport
@file:OptIn(ExperimentalJsExport::class)

package fr.ftnl.tools.messageBuilder.core.dto.components.interactive

import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import fr.ftnl.tools.messageBuilder.core.dto.components.utils.DiscordEmoji
import fr.ftnl.tools.messageBuilder.core.interfaces.components.ActionRowChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.MessageCompatibleComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.SectionAccessoryComponent
import fr.ftnl.tools.messageBuilder.core.serializers.components.ButtonStyleSerializer
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
class Button(
    var style: ButtonStyles
) : DiscordComponent, SectionAccessoryComponent, MessageCompatibleComponent, ActionRowChildComponent {
    
    @JsName("createFull") constructor(
        id: Int? = null,
        style: ButtonStyles,
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
    
    @EncodeDefault var disabled: Boolean = false
    
    @EncodeDefault override val type: Int = 2
    
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

@Serializable(with = ButtonStyleSerializer::class)
@JsExport
enum class ButtonStyles(val value: Int) {
    PRIMARY(1),
    SECONDARY(2),
    SUCCESS(3),
    DANGER(4),
    LINK(5),
    PREMIUM(6)
}