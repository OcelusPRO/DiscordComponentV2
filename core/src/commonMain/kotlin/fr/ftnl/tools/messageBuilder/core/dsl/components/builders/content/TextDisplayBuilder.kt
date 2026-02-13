package fr.ftnl.tools.messageBuilder.core.dsl.components.builders.content

import fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
import fr.ftnl.tools.messageBuilder.core.dsl.components.dsl.MessageComponentBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.dsl.ModalComponentBuilder
import fr.ftnl.tools.messageBuilder.core.dto.components.content.TextDisplay

@ComponentDsl
class TextDisplayBuilder: MessageComponentBuilder, ModalComponentBuilder {
    var content: String = ""
    
    override fun build(): TextDisplay {
        return TextDisplay(content = content)
    }
}
