package fr.ftnl.tools.messageBuilder.core.dto.components.interactive

import fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
abstract class BaseEntitySelect : DiscordComponent, ContainerChildComponent {
    abstract val customId: String
    abstract val placeholder: String?
    abstract val minValues: Int
    abstract val maxValues: Int
    abstract val disabled: Boolean
    abstract val required: Boolean
    @SerialName("default_values") abstract val defaultValues: List<SelectDefaultValue>?
    
}

@Serializable
data class UserSelect(
    override val id: Int? = null,
    @SerialName("custom_id") override val customId: String,
    override val placeholder: String? = null,
    @SerialName("min_values") override val minValues: Int = 1,
    @SerialName("max_values") override val maxValues: Int = 1,
    override val disabled: Boolean = false,
    override val required: Boolean = true,
    @SerialName("default_values") override val defaultValues: List<SelectDefaultValue>? = null
) : BaseEntitySelect() {
    override val type: Int = 5
}

@Serializable
data class RoleSelect(
    override val id: Int? = null,
    @SerialName("custom_id") override val customId: String,
    override val placeholder: String? = null,
    @SerialName("min_values") override val minValues: Int = 1,
    @SerialName("max_values") override val maxValues: Int = 1,
    override val disabled: Boolean = false,
    override val required: Boolean = true,
    @SerialName("default_values") override val defaultValues: List<SelectDefaultValue>? = null
) : BaseEntitySelect() {
    override val type: Int = 6
}

@Serializable
data class MentionableSelect(
    override val id: Int? = null,
    @SerialName("custom_id") override val customId: String,
    override val placeholder: String? = null,
    @SerialName("min_values") override val minValues: Int = 1,
    @SerialName("max_values") override val maxValues: Int = 1,
    override val disabled: Boolean = false,
    override val required: Boolean = true,
    @SerialName("default_values") override val defaultValues: List<SelectDefaultValue>? = null
) : BaseEntitySelect() {
    override val type: Int = 7
}

@Serializable
data class ChannelSelect(
    override val id: Int? = null,
    @SerialName("custom_id") override val customId: String,
    @SerialName("channel_types") val channelTypes: List<Int>? = null, // Voir ChannelType enum
    override val placeholder: String? = null,
    @SerialName("min_values") override val minValues: Int = 1,
    @SerialName("max_values") override val maxValues: Int = 1,
    override val disabled: Boolean = false,
    override val required: Boolean = true,
    @SerialName("default_values") override val defaultValues: List<SelectDefaultValue>? = null
) : BaseEntitySelect() {
    override val type: Int = 8
}

@Serializable
data class SelectDefaultValue(
    val id: String, // Snowflake
    val type: String // "user", "role", "channel"
)

