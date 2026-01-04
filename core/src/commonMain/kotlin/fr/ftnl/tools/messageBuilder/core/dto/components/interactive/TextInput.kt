package fr.ftnl.tools.messageBuilder.core.dto.components.interactive

import fr.ftnl.tools.messageBuilder.core.interfaces.components.ActionRowChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.SectionChildComponent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TextInput(
    override val id: Int? = null,
    @SerialName("custom_id") val customId: String,
    val style: Int, // 1 = Short, 2 = Paragraph
    @SerialName("min_length") val minLength: Int? = null,
    @SerialName("max_length") val maxLength: Int? = null,
    val required: Boolean = true,
    val value: String? = null,
    val placeholder: String? = null
) : DiscordComponent, ContainerChildComponent, ActionRowChildComponent, SectionChildComponent {
    override val type: Int = 4
}