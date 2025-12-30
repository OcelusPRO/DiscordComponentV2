package fr.ftnl.tools.messageBuilder.core.dsl.components

import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import fr.ftnl.tools.messageBuilder.core.dto.components.content.Thumbnail
import fr.ftnl.tools.messageBuilder.core.dto.components.content.UnfurledMediaItem
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.ActionRow
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.Container
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.Section
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.Separator

@fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
class ContainerBuilder : fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder(), fr.ftnl.tools.messageBuilder.core.dsl.components.ContainerContext {
    var color: Int? = null
    var spoiler: Boolean = false

    fun text(block: fr.ftnl.tools.messageBuilder.core.dsl.components.TextBuilder.() -> Unit) {
        val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.TextBuilder()
        builder.block()
        _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder.add(builder.build())
    }

    fun container(block: fr.ftnl.tools.messageBuilder.core.dsl.components.ContainerBuilder.() -> Unit) {
        val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.ContainerBuilder()
        builder.block()
        _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder.add(builder.build())
    }

    fun button(style: Int, block: fr.ftnl.tools.messageBuilder.core.dsl.components.ButtonBuilder.() -> Unit) {
        val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.ButtonBuilder(style)
        builder.block()
        _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder.add(builder.build())
    }

    fun stringSelect(customId: String, block: fr.ftnl.tools.messageBuilder.core.dsl.components.StringSelectBuilder.() -> Unit) {
        val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.StringSelectBuilder(customId)
        builder.block()
        _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder.add(builder.build())
    }

    fun userSelect(customId: String, block: fr.ftnl.tools.messageBuilder.core.dsl.components.UserSelectBuilder.() -> Unit = {}) {
        val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.UserSelectBuilder(customId)
        builder.block()
        _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder.add(builder.build())
    }

    fun roleSelect(customId: String, block: fr.ftnl.tools.messageBuilder.core.dsl.components.RoleSelectBuilder.() -> Unit = {}) {
        val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.RoleSelectBuilder(customId)
        builder.block()
        _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder.add(builder.build())
    }

    fun mentionableSelect(customId: String, block: fr.ftnl.tools.messageBuilder.core.dsl.components.MentionableSelectBuilder.() -> Unit = {}) {
        val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.MentionableSelectBuilder(customId)
        builder.block()
        _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder.add(builder.build())
    }

    fun channelSelect(customId: String, block: fr.ftnl.tools.messageBuilder.core.dsl.components.ChannelSelectBuilder.() -> Unit = {}) {
        val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.ChannelSelectBuilder(customId)
        builder.block()
        _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder.add(builder.build())
    }

    fun textInput(customId: String, style: Int, block: fr.ftnl.tools.messageBuilder.core.dsl.components.TextInputBuilder.() -> Unit = {}) {
        val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.TextInputBuilder(customId, style)
        builder.block()
        _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder.add(builder.build())
    }

    fun separator(spacing: Int = 1, divider: Boolean = true) {
        _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder.add(
            _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.layout.Separator(
                divider = divider,
                spacing = spacing
            )
        )
    }

    fun mediaGallery(block: fr.ftnl.tools.messageBuilder.core.dsl.components.MediaGalleryBuilder.() -> Unit) {
        val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.MediaGalleryBuilder()
        builder.block()
        _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder.add(builder.build())
    }

    fun actionRow(block: fr.ftnl.tools.messageBuilder.core.dsl.components.ActionRowBuilder.() -> Unit) {
        val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.ActionRowBuilder()
        builder.block()
        _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder.add(builder.build())
    }

    fun section(accessory: fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent, block: fr.ftnl.tools.messageBuilder.core.dsl.components.SectionBuilder.() -> Unit) {
        val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.SectionBuilder(accessory)
        builder.block()
        _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder.add(builder.build())
    }

    fun section(accessoryBlock: fr.ftnl.tools.messageBuilder.core.dsl.components.SectionAccessoryBuilder.() -> Unit, block: fr.ftnl.tools.messageBuilder.core.dsl.components.SectionBuilder.() -> Unit) {
        val accessoryBuilder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.SectionAccessoryBuilder()
        accessoryBuilder.accessoryBlock()
        val accessory = accessoryBuilder.build() ?: throw IllegalStateException("Section accessory must be defined")
        val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.SectionBuilder(accessory)
        builder.block()
        _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder.add(builder.build())
    }

    fun build(): fr.ftnl.tools.messageBuilder.core.dto.components.layout.Container {
        return _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.layout.Container(
            components = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder.components, accentColor = color, spoiler = spoiler
        )
    }
}

@fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
class ActionRowBuilder : fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder(), fr.ftnl.tools.messageBuilder.core.dsl.components.ActionRowContext {

    fun button(style: Int, block: fr.ftnl.tools.messageBuilder.core.dsl.components.ButtonBuilder.() -> Unit) {
        val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.ButtonBuilder(style)
        builder.block()
        _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder.add(builder.build())
    }

    fun textInput(customId: String, style: Int, block: fr.ftnl.tools.messageBuilder.core.dsl.components.TextInputBuilder.() -> Unit = {}) {
        val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.TextInputBuilder(customId, style)
        builder.block()
        _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder.add(builder.build())
    }

    fun stringSelect(customId: String, block: fr.ftnl.tools.messageBuilder.core.dsl.components.StringSelectBuilder.() -> Unit) {
        val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.StringSelectBuilder(customId)
        builder.block()
        _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder.add(builder.build())
    }

    fun userSelect(customId: String, block: fr.ftnl.tools.messageBuilder.core.dsl.components.UserSelectBuilder.() -> Unit = {}) {
        val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.UserSelectBuilder(customId)
        builder.block()
        _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder.add(builder.build())
    }

    fun roleSelect(customId: String, block: fr.ftnl.tools.messageBuilder.core.dsl.components.RoleSelectBuilder.() -> Unit = {}) {
        val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.RoleSelectBuilder(customId)
        builder.block()
        _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder.add(builder.build())
    }

    fun mentionableSelect(customId: String, block: fr.ftnl.tools.messageBuilder.core.dsl.components.MentionableSelectBuilder.() -> Unit = {}) {
        val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.MentionableSelectBuilder(customId)
        builder.block()
        _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder.add(builder.build())
    }

    fun channelSelect(customId: String, block: fr.ftnl.tools.messageBuilder.core.dsl.components.ChannelSelectBuilder.() -> Unit = {}) {
        val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.ChannelSelectBuilder(customId)
        builder.block()
        _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder.add(builder.build())
    }

    fun build(): fr.ftnl.tools.messageBuilder.core.dto.components.layout.ActionRow {
        return _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.layout.ActionRow(components = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder.components)
    }
}

@fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
class SectionBuilder(private val accessory: fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent) : fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder(),
                                                                                                                        fr.ftnl.tools.messageBuilder.core.dsl.components.SectionContext {

    fun text(block: fr.ftnl.tools.messageBuilder.core.dsl.components.TextBuilder.() -> Unit) {
        val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.TextBuilder()
        builder.block()
        _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder.add(builder.build())
    }

    fun button(style: Int, block: fr.ftnl.tools.messageBuilder.core.dsl.components.ButtonBuilder.() -> Unit) {
        val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.ButtonBuilder(style)
        builder.block()
        _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder.add(builder.build())
    }

    fun textInput(customId: String, style: Int, block: fr.ftnl.tools.messageBuilder.core.dsl.components.TextInputBuilder.() -> Unit = {}) {
        val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.TextInputBuilder(customId, style)
        builder.block()
        _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder.add(builder.build())
    }

    fun thumbnail(media: fr.ftnl.tools.messageBuilder.core.dto.components.content.UnfurledMediaItem, description: String? = null, spoiler: Boolean = false) {
        _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder.add(
            _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.content.Thumbnail(
                media = media,
                description = description,
                spoiler = spoiler
            )
        )
    }

    fun build(): fr.ftnl.tools.messageBuilder.core.dto.components.layout.Section {
        return _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.layout.Section(
            components = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder.components,
            accessory = accessory
        )
    }
}

@fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
class SectionAccessoryBuilder {
    private var component: fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent? = null

    fun button(style: Int, block: fr.ftnl.tools.messageBuilder.core.dsl.components.ButtonBuilder.() -> Unit) {
        val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.ButtonBuilder(style)
        builder.block()
        component = builder.build()
    }

    fun build(): fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent? = component
}

@fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
class LabelComponentBuilder {
    private var component: fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent? = null

    fun stringSelect(customId: String, block: fr.ftnl.tools.messageBuilder.core.dsl.components.StringSelectBuilder.() -> Unit) {
        val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.StringSelectBuilder(customId)
        builder.block()
        component = builder.build()
    }

    fun userSelect(customId: String, block: fr.ftnl.tools.messageBuilder.core.dsl.components.UserSelectBuilder.() -> Unit = {}) {
        val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.UserSelectBuilder(customId)
        builder.block()
        component = builder.build()
    }

    fun roleSelect(customId: String, block: fr.ftnl.tools.messageBuilder.core.dsl.components.RoleSelectBuilder.() -> Unit = {}) {
        val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.RoleSelectBuilder(customId)
        builder.block()
        component = builder.build()
    }

    fun mentionableSelect(customId: String, block: fr.ftnl.tools.messageBuilder.core.dsl.components.MentionableSelectBuilder.() -> Unit = {}) {
        val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.MentionableSelectBuilder(customId)
        builder.block()
        component = builder.build()
    }

    fun channelSelect(customId: String, block: fr.ftnl.tools.messageBuilder.core.dsl.components.ChannelSelectBuilder.() -> Unit = {}) {
        val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.ChannelSelectBuilder(customId)
        builder.block()
        component = builder.build()
    }

    fun textInput(customId: String, style: Int, block: fr.ftnl.tools.messageBuilder.core.dsl.components.TextInputBuilder.() -> Unit = {}) {
        val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.TextInputBuilder(customId, style)
        builder.block()
        component = builder.build()
    }

    fun build(): fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent? = component
}
