package fr.ftnl.tools.messageBuilder.core.dto.components.interactive

import fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import fr.ftnl.tools.messageBuilder.core.dto.components.utils.DiscordEmoji
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StringSelect(
    override val id: Int? = null,
    @SerialName("custom_id") val customId: String,
    val options: List<SelectOption>,
    val placeholder: String? = null,
    @SerialName("min_values") val minValues: Int = 1,
    @SerialName("max_values") val maxValues: Int = 1,
    val required: Boolean = true, // Modal uniquement
    val disabled: Boolean = false // Message uniquement
) : DiscordComponent, ContainerChildComponent {
    override val type: Int = 3
}

@Serializable
data class SelectOption(
    val label: String,
    val value: String,
    val description: String? = null,
    val emoji: DiscordEmoji? = null,
    val default: Boolean = false
)