package fr.ftnl.tools.messageBuilder.core.dto.components.interactive

import fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.SectionChildComponent
import fr.ftnl.tools.messageBuilder.core.dto.components.utils.DiscordEmoji
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Button(
    override val id: Int? = null,
    val style: Int,
    val label: String? = null,
    val emoji: fr.ftnl.tools.messageBuilder.core.dto.components.utils.DiscordEmoji? = null,
    @SerialName("custom_id") val customId: String? = null, // Requis sauf pour Link/Premium
    val url: String? = null, // Requis pour Link (Style 5)
    @SerialName("sku_id") val skuId: String? = null, // Requis pour Premium (Style 6)
    val disabled: Boolean = false
) : fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent, fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent,
    fr.ftnl.tools.messageBuilder.core.interfaces.components.SectionChildComponent {
    override val type: Int = 2
}

object ButtonStyles {
    const val PRIMARY = 1
    const val SECONDARY = 2
    const val SUCCESS = 3
    const val DANGER = 4
    const val LINK = 5
    const val PREMIUM = 6
}