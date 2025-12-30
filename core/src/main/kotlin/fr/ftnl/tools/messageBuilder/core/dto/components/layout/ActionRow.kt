package fr.ftnl.tools.messageBuilder.core.dto.components.layout

import fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import kotlinx.serialization.Serializable

@Serializable
data class ActionRow(
    override val id: Int? = null,
    val components: List<fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent> // Boutons ou un seul Select
) : fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent, fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent {
    override val type: Int = 1
}
