package fr.ftnl.tools.messageBuilder.jda.ext.components.content.thumbnail

import fr.ftnl.tools.messageBuilder.core.dto.components.content.Thumbnail
import net.dv8tion.jda.api.components.thumbnail.Thumbnail as JdaThumbnail

fun Thumbnail.toJda(): JdaThumbnail {
    val thumb = JdaThumbnail.fromUrl(this.media.url)
    thumb.withSpoiler(this.spoiler)
    thumb.withDescription(this.description)
    this.id?.let { thumb.withUniqueId(it) }
    return thumb
}