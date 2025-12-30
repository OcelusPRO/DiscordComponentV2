package fr.ftnl.tools.messageBuilder.core.dto.components.utils

import kotlinx.serialization.Serializable


@Serializable
data class DiscordEmoji(
    val id: Long? = null,
    val name: String? = null,
    val animated: Boolean? = null
)