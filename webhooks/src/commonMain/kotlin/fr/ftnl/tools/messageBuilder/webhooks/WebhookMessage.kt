package fr.ftnl.tools.messageBuilder.webhooks

import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WebhookMessage(
    val content: String? = null,
    val username: String? = null,
    @SerialName("avatar_url")
    val avatarUrl: String? = null,
    val tts: Boolean = false,
    val embeds: List<Embed> = emptyList(), // We might need an Embed DTO if not in core, but assuming empty for now or generic map
    @SerialName("allowed_mentions")
    val allowedMentions: AllowedMentions? = null,
    val components: List<DiscordComponent> = emptyList()
)

@Serializable
data class Embed(
    val title: String? = null,
    val description: String? = null,
    val url: String? = null,
    val color: Int? = null,
    // Add other embed fields as necessary, keeping it minimal if not specified
)

@Serializable
data class AllowedMentions(
    val parse: List<String>,
    val roles: List<String> = emptyList(),
    val users: List<String> = emptyList(),
    val replied_user: Boolean = false
)
