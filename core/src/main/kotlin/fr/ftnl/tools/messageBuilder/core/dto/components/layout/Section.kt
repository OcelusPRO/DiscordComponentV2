package fr.ftnl.tools.messageBuilder.core.dto.components.layout

import fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import kotlinx.serialization.Serializable

@Serializable
data class Section(
    override val id: Int? = null,
    val components: List<DiscordComponent>,
    val accessory: DiscordComponent
) : DiscordComponent, ContainerChildComponent {
    override val type: Int = 9
}