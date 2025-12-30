package fr.ftnl.tools.messageBuilder.core.dto.components.layout

import fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import fr.ftnl.tools.messageBuilder.core.serializers.ColorHexSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Container(
    override val id: Int? = null,
    val components: List<DiscordComponent>,
    @Serializable(with = ColorHexSerializer::class)
    @SerialName("accent_color") val accentColor: Int? = null, // RGB int 0x000000
    val spoiler: Boolean = false
) : DiscordComponent, ContainerChildComponent {
    override val type: Int = 17
}