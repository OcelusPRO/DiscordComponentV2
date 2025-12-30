package fr.ftnl.tools.messageBuilder.core.dto.components.content

import fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import kotlinx.serialization.Serializable

@Serializable
data class FileComponent(
    override val id: Int? = null,
    val file: UnfurledMediaItem,
    val spoiler: Boolean = false,
    val name: String? = null,
    val size: Int? = null
) : DiscordComponent, ContainerChildComponent {
    override val type: Int = 13
}