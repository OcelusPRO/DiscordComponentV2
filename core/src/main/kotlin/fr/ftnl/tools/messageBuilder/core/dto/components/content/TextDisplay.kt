package fr.ftnl.tools.messageBuilder.core.dto.components.content

import fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.SectionChildComponent
import kotlinx.serialization.Serializable


@Serializable
data class TextDisplay(
    override val id: Int? = null,
    val content: String
) : fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent, fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent,
    fr.ftnl.tools.messageBuilder.core.interfaces.components.SectionChildComponent {
    override val type: Int = 10
}