@file:JsExport
@file:OptIn(ExperimentalJsExport::class)

package fr.ftnl.tools.messageBuilder.core.dsl.components.builders.layout

import fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
import fr.ftnl.tools.messageBuilder.core.dsl.components.dsl.MessageComponentBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.dsl.ModalComponentBuilder
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.ActionRow
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.Label
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.Section
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.Separator
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.SpacingType
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@ComponentDsl
class SeparatorBuilder: MessageComponentBuilder {
    
    var divider: Boolean = true
    var spacing: SpacingType = SpacingType.SMALL
    
    override fun build(): Separator {
        return Separator(divider = divider, spacing = spacing)
    }
}

