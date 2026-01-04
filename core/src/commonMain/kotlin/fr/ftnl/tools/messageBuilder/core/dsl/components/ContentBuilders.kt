package fr.ftnl.tools.messageBuilder.core.dsl.components

import fr.ftnl.tools.messageBuilder.core.dto.components.content.MediaGallery
import fr.ftnl.tools.messageBuilder.core.dto.components.content.MediaGalleryItem
import fr.ftnl.tools.messageBuilder.core.dto.components.content.TextDisplay
import fr.ftnl.tools.messageBuilder.core.dto.components.content.UnfurledMediaItem


@ComponentDsl
class TextBuilder {
    var content: String = ""

    fun build(): TextDisplay {
        return TextDisplay(content = content)
    }
}

@ComponentDsl
class MediaGalleryBuilder {
    private val items = mutableListOf<MediaGalleryItem>()

    fun item(url: String, block: MediaGalleryItemBuilder.() -> Unit = {}) {
        val builder = MediaGalleryItemBuilder(url)
        builder.block()
        items.add(builder.build())
    }

    fun build(): MediaGallery {
        return MediaGallery(items = items)
    }
}

@ComponentDsl
class MediaGalleryItemBuilder(private val url: String) {
    var description: String? = null
    var spoiler: Boolean = false

    fun build(): MediaGalleryItem {
        return MediaGalleryItem(
            media = UnfurledMediaItem(url = url), description = description, spoiler = spoiler
        )
    }
}
