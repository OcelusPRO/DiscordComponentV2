@file:JsExport
@file:OptIn(ExperimentalJsExport::class)

package fr.ftnl.tools.messageBuilder.core.dsl.components.builders.content

import fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
import fr.ftnl.tools.messageBuilder.core.dsl.components.dsl.MessageComponentBuilder
import fr.ftnl.tools.messageBuilder.core.dto.components.content.Thumbnail
import fr.ftnl.tools.messageBuilder.core.dto.components.content.UnfurledMediaItem
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@ComponentDsl
class ThumbnailBuilder(val url: String): MessageComponentBuilder {
    
    override fun build(): Thumbnail {
        return Thumbnail(UnfurledMediaItem(url))
    }
    
}