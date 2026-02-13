package fr.ftnl.tools.messageBuilder.core.dsl.components.builders.content

import fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
import fr.ftnl.tools.messageBuilder.core.dsl.components.dsl.MessageComponentBuilder
import fr.ftnl.tools.messageBuilder.core.dto.components.content.FileComponent
import fr.ftnl.tools.messageBuilder.core.dto.components.content.UnfurledMediaItem

@ComponentDsl
class FileComponentBuilder(val url: String): MessageComponentBuilder {
    
    override fun build(): FileComponent {
        return FileComponent(UnfurledMediaItem(url))
    }
    
}