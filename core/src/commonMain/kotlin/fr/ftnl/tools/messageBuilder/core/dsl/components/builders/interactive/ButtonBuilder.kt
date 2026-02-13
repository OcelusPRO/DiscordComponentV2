package fr.ftnl.tools.messageBuilder.core.dsl.components.builders.interactive

import fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
import fr.ftnl.tools.messageBuilder.core.dsl.components.dsl.MessageComponentBuilder
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.Button
import fr.ftnl.tools.messageBuilder.core.dto.components.utils.DiscordEmoji

@ComponentDsl
class ButtonBuilder(val customId: String, val style: Int): MessageComponentBuilder {
    var label: String? = null
    var url: String? = null
    var emoji: DiscordEmoji? = null
    var disabled: Boolean = false
    var skuId: String? = null
    
    override fun build(): Button {
        return Button(
            style = style,
            label = label,
            emoji = emoji,
            customId = customId,
            url = url,
            skuId = skuId,
            disabled = disabled
        )
    }
}