package fr.ftnl.tools.messageBuilder.core.dto.components.content

import fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MediaGallery(
    override val id: Int? = null,
    val items: List<fr.ftnl.tools.messageBuilder.core.dto.components.content.MediaGalleryItem>
) : fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent, fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent {
    override val type: Int = 12
}

@Serializable
data class MediaGalleryItem(
    val media: fr.ftnl.tools.messageBuilder.core.dto.components.content.UnfurledMediaItem,
    val description: String? = null,
    val spoiler: Boolean = false
)

@Serializable
data class UnfurledMediaItem(
    val url: String,
    @SerialName("proxy_url") val proxyUrl: String? = null,
    val height: Int? = null,
    val width: Int? = null,
    @SerialName("content_type") val contentType: String? = null,
    @SerialName("attachment_id") val attachmentId: String? = null
)