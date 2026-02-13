@file:JsExport
@file:OptIn(ExperimentalJsExport::class)

package fr.ftnl.tools.messageBuilder.core.dsl.components.builders.layout

import fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.interactive.ButtonBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.interactive.ChannelSelectBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.interactive.MentionableSelectBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.interactive.RoleSelectBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.interactive.StringSelectBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.interactive.UserSelectBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.dsl.MessageComponentBuilder
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.ButtonStyles
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.ActionRow
import fr.ftnl.tools.messageBuilder.core.interfaces.components.ActionRowChildComponent
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@ComponentDsl
class ActionRowBuilder : MessageComponentBuilder {
    
    private val rowComponents = mutableListOf<ActionRowChildComponent>()
    
    fun button(customId: String, style: ButtonStyles, block: ButtonBuilder.() -> Unit) {
        val builder = ButtonBuilder(customId, style)
        builder.block()
        rowComponents.add(builder.build())
    }
    
    fun stringSelect(customId: String, block: StringSelectBuilder.() -> Unit) {
        val builder = StringSelectBuilder(customId)
        builder.block()
        rowComponents.add(builder.build())
    }
    
    fun userSelect(customId: String, block: UserSelectBuilder.() -> Unit) {
        val builder = UserSelectBuilder(customId)
        builder.block()
        rowComponents.add(builder.build())
    }
    
    fun roleSelect(customId: String, block: RoleSelectBuilder.() -> Unit) {
        val builder = RoleSelectBuilder(customId)
        builder.block()
        rowComponents.add(builder.build())
    }
    
    fun mentionableSelect(customId: String, block: MentionableSelectBuilder.() -> Unit) {
        val builder = MentionableSelectBuilder(customId)
        builder.block()
        rowComponents.add(builder.build())
    }
    
    fun channelSelect(customId: String, block: ChannelSelectBuilder.() -> Unit) {
        val builder = ChannelSelectBuilder(customId)
        builder.block()
        rowComponents.add(builder.build())
    }
    
    override fun build(): ActionRow {
        return ActionRow(components = rowComponents)
    }
}