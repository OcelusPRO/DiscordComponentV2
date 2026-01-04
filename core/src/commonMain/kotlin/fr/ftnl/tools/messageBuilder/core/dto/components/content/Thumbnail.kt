package fr.ftnl.tools.messageBuilder.core.dto.components.content

import fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.SectionChildComponent
import kotlinx.serialization.Serializable

@Serializable
data class Thumbnail(
    override val id: Int? = null,
    val media: UnfurledMediaItem,
    val description: String? = null,
    val spoiler: Boolean = false
) : DiscordComponent, ContainerChildComponent, SectionChildComponent {
    override val type: Int = 11
}
