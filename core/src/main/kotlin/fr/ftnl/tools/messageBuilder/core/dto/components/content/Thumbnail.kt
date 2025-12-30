package fr.ftnl.tools.messageBuilder.core.dto.components.content

import fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.SectionChildComponent
import kotlinx.serialization.Serializable

@Serializable
data class Thumbnail(
    override val id: Int? = null,
    val media: fr.ftnl.tools.messageBuilder.core.dto.components.content.UnfurledMediaItem,
    val description: String? = null,
    val spoiler: Boolean = false
) : fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent, fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent,
    fr.ftnl.tools.messageBuilder.core.interfaces.components.SectionChildComponent {
    override val type: Int = 11
}
