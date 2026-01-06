@file:JsExport
@file:OptIn(ExperimentalJsExport::class)

package fr.ftnl.tools.messageBuilder.core.dto.components.content

import fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
class MediaGallery() : DiscordComponent, ContainerChildComponent {
    
    @JsName("createFull") constructor(
        id: Int? = null,
        items: List<MediaGalleryItem> = emptyList()
    ) : this() {
        this.id = id
        this.items = items.toMutableList()
    }
    
    override var id: Int? = null
    var items: MutableList<MediaGalleryItem> = mutableListOf()
    override val type: Int = 12
    
    fun setId(id: Int?): MediaGallery {
        this.id = id
        return this
    }
    
    fun addItem(item: MediaGalleryItem): MediaGallery {
        this.items.add(item)
        return this
    }
    
    fun setItems(items: Array<MediaGalleryItem>): MediaGallery {
        this.items = items.toMutableList()
        return this
    }
}

@Serializable
class MediaGalleryItem(
    var media: UnfurledMediaItem
) {
    @JsName("createFull") constructor(
        media: UnfurledMediaItem,
        description: String? = null,
        spoiler: Boolean = false
    ) : this(media) {
        this.description = description
        this.spoiler = spoiler
    }
    
    var description: String? = null
    var spoiler: Boolean = false
    
    fun setDescription(description: String?): MediaGalleryItem {
        this.description = description
        return this
    }
    
    fun setSpoiler(spoiler: Boolean): MediaGalleryItem {
        this.spoiler = spoiler
        return this
    }
}

@Serializable
class UnfurledMediaItem(
    var url: String
) {
    @JsName("createFull") constructor(
        url: String,
        proxyUrl: String? = null,
        height: Int? = null,
        width: Int? = null,
        contentType: String? = null,
        attachmentId: String? = null
    ) : this(url) {
        this.proxyUrl = proxyUrl
        this.height = height
        this.width = width
        this.contentType = contentType
        this.attachmentId = attachmentId
    }
    
    @SerialName("proxy_url")
    var proxyUrl: String? = null
    
    var height: Int? = null
    var width: Int? = null
    
    @SerialName("content_type")
    var contentType: String? = null
    
    @SerialName("attachment_id")
    var attachmentId: String? = null
    
    fun setProxyUrl(proxyUrl: String?): UnfurledMediaItem {
        this.proxyUrl = proxyUrl
        return this
    }
    
    fun setHeight(height: Int?): UnfurledMediaItem {
        this.height = height
        return this
    }
    
    fun setWidth(width: Int?): UnfurledMediaItem {
        this.width = width
        return this
    }
    
    fun setContentType(contentType: String?): UnfurledMediaItem {
        this.contentType = contentType
        return this
    }
    
    fun setAttachmentId(attachmentId: String?): UnfurledMediaItem {
        this.attachmentId = attachmentId
        return this
    }
}