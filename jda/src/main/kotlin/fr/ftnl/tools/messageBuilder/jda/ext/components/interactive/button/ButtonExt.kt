package fr.ftnl.tools.messageBuilder.jda.ext.components.interactive.button

import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.Button
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.ButtonStyles
import fr.ftnl.tools.messageBuilder.jda.ext.components.utils.emoji.toJda
import net.dv8tion.jda.api.components.buttons.Button as JdaButton
import net.dv8tion.jda.api.components.buttons.ButtonStyle as JdaButtonStyle

fun Button.toJda(): JdaButton{
    val idOrUrl = if (style in listOf(ButtonStyles.PREMIUM, ButtonStyles.LINK)) url else customId
    val jdaStyle = JdaButtonStyle.fromKey(style)
    requireNotNull(idOrUrl) { "Custom ID or URL is required for this button style" }
    return if (label != null) JdaButton.of(jdaStyle, idOrUrl, label!!)
    else {
        requireNotNull(emoji) { "Emoji is required for this button style" }
        JdaButton.of(jdaStyle, idOrUrl, emoji!!.toJda())
    }
    
}