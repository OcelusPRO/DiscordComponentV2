package fr.ftnl.tools.messageBuilder.core.dto.components.layout

import fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import fr.ftnl.tools.messageBuilder.core.serializers.ColorHexSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Container(
    override val id: Int? = null,
    val components: List<fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent>,
    @Serializable(with = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.serializers.ColorHexSerializer::class)
    @SerialName("accent_color") val accentColor: Int? = null, // RGB int 0x000000
    val spoiler: Boolean = false
) : fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent, fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent {
    override val type: Int = 17
}