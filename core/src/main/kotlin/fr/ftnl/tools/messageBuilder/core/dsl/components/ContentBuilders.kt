package fr.ftnl.tools.messageBuilder.core.dsl.components

import fr.ftnl.tools.messageBuilder.core.dto.components.content.MediaGallery
import fr.ftnl.tools.messageBuilder.core.dto.components.content.MediaGalleryItem
import fr.ftnl.tools.messageBuilder.core.dto.components.content.TextDisplay
import fr.ftnl.tools.messageBuilder.core.dto.components.content.UnfurledMediaItem


@fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
class TextBuilder {
    var content: String = ""

    fun build(): fr.ftnl.tools.messageBuilder.core.dto.components.content.TextDisplay {
        return _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.content.TextDisplay(content = content)
    }
}

@fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
class MediaGalleryBuilder {
    private val items = mutableListOf<fr.ftnl.tools.messageBuilder.core.dto.components.content.MediaGalleryItem>()

    fun item(url: String, block: fr.ftnl.tools.messageBuilder.core.dsl.components.MediaGalleryItemBuilder.() -> Unit = {}) {
        val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.MediaGalleryItemBuilder(url)
        builder.block()
        items.add(builder.build())
    }

    fun build(): fr.ftnl.tools.messageBuilder.core.dto.components.content.MediaGallery {
        return _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.content.MediaGallery(items = items)
    }
}

@fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
class MediaGalleryItemBuilder(private val url: String) {
    var description: String? = null
    var spoiler: Boolean = false

    fun build(): fr.ftnl.tools.messageBuilder.core.dto.components.content.MediaGalleryItem {
        return _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.content.MediaGalleryItem(
            media = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.content.UnfurledMediaItem(url = url), description = description, spoiler = spoiler
        )
    }
}
