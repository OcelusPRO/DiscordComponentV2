package fr.ftnl.tools.messageBuilder.core.dto.components.layout

import fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import kotlinx.serialization.Serializable

@Serializable
data class Separator(
    override val id: Int? = null,
    val divider: Boolean = true,
    val spacing: Int = 1 // 1 (small) ou 2 (large)
) : DiscordComponent, ContainerChildComponent {
    override val type: Int = 14
}