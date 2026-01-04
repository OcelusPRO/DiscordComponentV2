package fr.ftnl.tools.messageBuilder.core.dto.components.content

import fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.SectionChildComponent
import kotlinx.serialization.Serializable


@Serializable
data class TextDisplay(
    override val id: Int? = null,
    val content: String
) : DiscordComponent, ContainerChildComponent, SectionChildComponent {
    override val type: Int = 10
}