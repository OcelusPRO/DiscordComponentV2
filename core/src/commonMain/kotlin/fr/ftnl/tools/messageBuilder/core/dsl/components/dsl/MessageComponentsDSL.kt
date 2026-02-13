package fr.ftnl.tools.messageBuilder.core.dsl.components.dsl


import fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.content.FileComponentBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.content.MediaGalleryBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.content.TextDisplayBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.content.ThumbnailBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.interactive.ButtonBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.interactive.ChannelSelectBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.interactive.MentionableSelectBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.interactive.RoleSelectBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.interactive.StringSelectBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.interactive.UserSelectBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.layout.ActionRowBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.layout.ContainerBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.layout.SectionBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.layout.SeparatorBuilder
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.ButtonStyles
import fr.ftnl.tools.messageBuilder.core.interfaces.components.MessageCompatibleComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.MessageCompatibleList

interface MessageComponentBuilder: ComponentBuilder {
    override fun build(): MessageCompatibleComponent
}

@ComponentDsl
class MessageComponentsDSL: BaseComponentBuilder() {
    
    // Contents
    fun file(url: String, block: FileComponentBuilder.() -> Unit) {
        val builder = FileComponentBuilder(url)
        builder.block()
        add(builder)
    }
    fun mediaGallery(block: MediaGalleryBuilder.() -> Unit) {
        val builder = MediaGalleryBuilder()
        builder.block()
        add(builder)
    }
    fun textDisplay(block: TextDisplayBuilder.() -> Unit) {
        val builder = TextDisplayBuilder()
        builder.block()
        add(builder)
    }
    fun thumbnail(url: String, block: ThumbnailBuilder.() -> Unit) {
        val builder = ThumbnailBuilder(url)
        builder.block()
        add(builder)
    }
    
    // interactive
    fun button(customId: String, style: ButtonStyles, block: ButtonBuilder.() -> Unit) {
        val builder = ButtonBuilder(customId, style)
        builder.block()
        add(builder.build())
    }
    fun stringSelect(customId: String, block: StringSelectBuilder.() -> Unit) {
        val builder = StringSelectBuilder(customId)
        builder.block()
        add(builder.build())
    }
    fun userSelect(customId: String, block: UserSelectBuilder.() -> Unit) {
        val builder = UserSelectBuilder(customId)
        builder.block()
        add(builder.build())
    }
    fun roleSelect(customId: String, block: RoleSelectBuilder.() -> Unit) {
        val builder = RoleSelectBuilder(customId)
        builder.block()
        add(builder.build())
    }
    fun mentionableSelect(customId: String, block: MentionableSelectBuilder.() -> Unit) {
        val builder = MentionableSelectBuilder(customId)
        builder.block()
        add(builder.build())
    }
    fun channelSelect(customId: String, block: ChannelSelectBuilder.() -> Unit) {
        val builder = ChannelSelectBuilder(customId)
        builder.block()
        add(builder.build())
    }
    
    // layout
    fun actionRow(block: ActionRowBuilder.() -> Unit) {
        val builder = ActionRowBuilder()
        builder.block()
        add(builder.build())
    }
    fun container(block: ContainerBuilder.() -> Unit) {
        val builder = ContainerBuilder()
        builder.block()
        add(builder.build())
    }
    fun section(block: SectionBuilder.() -> Unit) {
        val builder = SectionBuilder()
        builder.block()
        add(builder.build())
    }
    fun separator(block: SeparatorBuilder.() -> Unit) {
        val builder = SeparatorBuilder()
        builder.block()
        add(builder.build())
    }
    
    
    internal fun build(): MessageCompatibleList {
        val filteredList = components.elements.filterIsInstance<MessageCompatibleComponent>()
        return MessageCompatibleList(filteredList)
    }
}

/**
 * Entry point for the Message Component DSL.
 * Returns a list of constructed components.
 */
fun messageComponents(block: MessageComponentsDSL.() -> Unit): MessageCompatibleList {
    val builder = MessageComponentsDSL()
    builder.block()
    return builder.build()
}
