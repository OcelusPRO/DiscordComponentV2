@file:JsExport @file:OptIn(ExperimentalJsExport::class)

package fr.ftnl.tools.messageBuilder.core.dsl.components

import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import fr.ftnl.tools.messageBuilder.core.dto.components.content.Thumbnail
import fr.ftnl.tools.messageBuilder.core.dto.components.content.UnfurledMediaItem
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.ActionRow
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.Container
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.Section
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.Separator
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName

@ComponentDsl
class ContainerBuilder : BaseComponentBuilder(), ContainerContext {
    var color: Int? = null
    var spoiler: Boolean = false

    fun text(block: TextBuilder.() -> Unit) {
        val builder = TextBuilder()
        builder.block()
        add(builder.build())
    }

    fun container(block: ContainerBuilder.() -> Unit) {
        val builder = ContainerBuilder()
        builder.block()
        add(builder.build())
    }

    fun button(style: Int, block: ButtonBuilder.() -> Unit) {
        val builder = ButtonBuilder(style)
        builder.block()
        add(builder.build())
    }

    fun stringSelect(customId: String, block: StringSelectBuilder.() -> Unit) {
        val builder = StringSelectBuilder(customId)
        builder.block()
        add(builder.build())
    }

    fun userSelect(customId: String, block: UserSelectBuilder.() -> Unit = {}) {
        val builder = UserSelectBuilder(customId)
        builder.block()
        add(builder.build())
    }

    fun roleSelect(customId: String, block: RoleSelectBuilder.() -> Unit = {}) {
        val builder = RoleSelectBuilder(customId)
        builder.block()
        add(builder.build())
    }

    fun mentionableSelect(customId: String, block: MentionableSelectBuilder.() -> Unit = {}) {
        val builder = MentionableSelectBuilder(customId)
        builder.block()
        add(builder.build())
    }

    fun channelSelect(customId: String, block: ChannelSelectBuilder.() -> Unit = {}) {
        val builder = ChannelSelectBuilder(customId)
        builder.block()
        add(builder.build())
    }

    fun textInput(customId: String, style: Int, block: TextInputBuilder.() -> Unit = {}) {
        val builder = TextInputBuilder(customId, style)
        builder.block()
        add(builder.build())
    }

    fun separator(spacing: Int = 1, divider: Boolean = true) {
        add(
            Separator(
                divider = divider,
                spacing = spacing
            )
        )
    }

    fun mediaGallery(block: MediaGalleryBuilder.() -> Unit) {
        val builder = MediaGalleryBuilder()
        builder.block()
        add(builder.build())
    }

    fun actionRow(block: ActionRowBuilder.() -> Unit) {
        val builder = ActionRowBuilder()
        builder.block()
        add(builder.build())
    }

    fun section(accessory: DiscordComponent, block: SectionBuilder.() -> Unit) {
        val builder = SectionBuilder(accessory)
        builder.block()
        add(builder.build())
    }


    @JsName("sectionBlock")
    fun section(accessoryBlock: SectionAccessoryBuilder.() -> Unit, block: SectionBuilder.() -> Unit) {
        val accessoryBuilder = SectionAccessoryBuilder()
        accessoryBuilder.accessoryBlock()
        val accessory = accessoryBuilder.build() ?: throw IllegalStateException("Section accessory must be defined")
        val builder = SectionBuilder(accessory)
        builder.block()
        add(builder.build())
    }

    fun build(): Container {
        return Container(
            components = components, accentColor = color, spoiler = spoiler
        )
    }
}

@ComponentDsl
class ActionRowBuilder : BaseComponentBuilder(), ActionRowContext {

    fun button(style: Int, block: ButtonBuilder.() -> Unit) {
        val builder = ButtonBuilder(style)
        builder.block()
        add(builder.build())
    }

    fun textInput(customId: String, style: Int, block: TextInputBuilder.() -> Unit = {}) {
        val builder = TextInputBuilder(customId, style)
        builder.block()
        add(builder.build())
    }

    fun stringSelect(customId: String, block: StringSelectBuilder.() -> Unit) {
        val builder = StringSelectBuilder(customId)
        builder.block()
        add(builder.build())
    }

    fun userSelect(customId: String, block: UserSelectBuilder.() -> Unit = {}) {
        val builder = UserSelectBuilder(customId)
        builder.block()
        add(builder.build())
    }

    fun roleSelect(customId: String, block: RoleSelectBuilder.() -> Unit = {}) {
        val builder = RoleSelectBuilder(customId)
        builder.block()
        add(builder.build())
    }

    fun mentionableSelect(customId: String, block: MentionableSelectBuilder.() -> Unit = {}) {
        val builder = MentionableSelectBuilder(customId)
        builder.block()
        add(builder.build())
    }

    fun channelSelect(customId: String, block: ChannelSelectBuilder.() -> Unit = {}) {
        val builder = ChannelSelectBuilder(customId)
        builder.block()
        add(builder.build())
    }

    fun build(): ActionRow {
        return ActionRow(components = components)
    }
}

@ComponentDsl
class SectionBuilder(private val accessory: DiscordComponent) : BaseComponentBuilder(), SectionContext {

    fun text(block: TextBuilder.() -> Unit) {
        val builder = TextBuilder()
        builder.block()
        add(builder.build())
    }

    fun button(style: Int, block: ButtonBuilder.() -> Unit) {
        val builder = ButtonBuilder(style)
        builder.block()
        add(builder.build())
    }

    fun textInput(customId: String, style: Int, block: TextInputBuilder.() -> Unit = {}) {
        val builder = TextInputBuilder(customId, style)
        builder.block()
        add(builder.build())
    }

    fun thumbnail(media: UnfurledMediaItem, description: String? = null, spoiler: Boolean = false) {
        add(
            Thumbnail(
                media = media,
                description = description,
                spoiler = spoiler
            )
        )
    }

    fun build(): Section {
        return Section(
            components = components,
            accessory = accessory
        )
    }
}

@ComponentDsl
class SectionAccessoryBuilder {
    private var component: DiscordComponent? = null

    fun button(style: Int, block: ButtonBuilder.() -> Unit) {
        val builder = ButtonBuilder(style)
        builder.block()
        component = builder.build()
    }

    fun build(): DiscordComponent? = component
}

@ComponentDsl
class LabelComponentBuilder {
    private var component: DiscordComponent? = null

    fun stringSelect(customId: String, block: StringSelectBuilder.() -> Unit) {
        val builder = StringSelectBuilder(customId)
        builder.block()
        component = builder.build()
    }

    fun userSelect(customId: String, block: UserSelectBuilder.() -> Unit = {}) {
        val builder = UserSelectBuilder(customId)
        builder.block()
        component = builder.build()
    }

    fun roleSelect(customId: String, block: RoleSelectBuilder.() -> Unit = {}) {
        val builder = RoleSelectBuilder(customId)
        builder.block()
        component = builder.build()
    }

    fun mentionableSelect(customId: String, block: MentionableSelectBuilder.() -> Unit = {}) {
        val builder = MentionableSelectBuilder(customId)
        builder.block()
        component = builder.build()
    }

    fun channelSelect(customId: String, block: ChannelSelectBuilder.() -> Unit = {}) {
        val builder = ChannelSelectBuilder(customId)
        builder.block()
        component = builder.build()
    }

    fun textInput(customId: String, style: Int, block: TextInputBuilder.() -> Unit = {}) {
        val builder = TextInputBuilder(customId, style)
        builder.block()
        component = builder.build()
    }

    fun build(): DiscordComponent? = component
}
