package fr.ftnl.tools.messageBuilder.core.dto.components.layout

import fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import kotlinx.serialization.Serializable

@Serializable
data class ActionRow(
    override val id: Int? = null,
    val components: List<DiscordComponent> // Boutons ou un seul Select
) : DiscordComponent, ContainerChildComponent {
    override val type: Int = 1
}
