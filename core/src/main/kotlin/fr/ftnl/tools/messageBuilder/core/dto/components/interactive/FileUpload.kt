package fr.ftnl.tools.messageBuilder.core.dto.components.interactive

import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FileUpload(
    override val id: Int? = null,
    @SerialName("custom_id") val customId: String,
    @SerialName("min_values") val minValues: Int = 1,
    @SerialName("max_values") val maxValues: Int = 10,
    val required: Boolean = true
) : DiscordComponent {
    override val type: Int = 19
}