package fr.ftnl.tools.messageBuilder.jda.ext.components.content.mediaGallery

import fr.ftnl.tools.messageBuilder.core.dto.components.content.MediaGallery
import fr.ftnl.tools.messageBuilder.core.dto.components.content.MediaGalleryItem
import net.dv8tion.jda.api.components.mediagallery.MediaGalleryItem as JdaMediaGalleryItem
import net.dv8tion.jda.api.components.mediagallery.MediaGallery as JdaMediaGallery


fun MediaGallery.toJda(): JdaMediaGallery = JdaMediaGallery.of(items.map { it.toJdaItem() })
fun MediaGalleryItem.toJdaItem(): JdaMediaGalleryItem = JdaMediaGalleryItem.fromUrl(media.url)
