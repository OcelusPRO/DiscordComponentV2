package fr.ftnl.tools.messageBuilder.core.dto.components.layout

import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import kotlinx.serialization.Serializable

@Serializable
data class Label(
    override val id: Int? = null,
    val label: String,
    val description: String? = null,
    val component: fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent // Input, Select, etc.
) : fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent {
    override val type: Int = 18
}