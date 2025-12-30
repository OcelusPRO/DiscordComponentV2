package fr.ftnl.tools.messageBuilder.core.dsl.components

import fr.ftnl.tools.messageBuilder.core.dto.components.layout.Label
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.Separator

@DslMarker
annotation class ComponentDsl

/**
 * Entry point for the Discord Component DSL.
 * Returns a list of constructed components.
 */
fun discordComponent(block: fr.ftnl.tools.messageBuilder.core.dsl.components.RootComponentBuilder.() -> Unit): List<fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent> {
    val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.RootComponentBuilder()
    builder.block()
    return builder.build()
}

// Interfaces to mark which components can be added to which parent
interface ContainerContext
interface SectionContext
interface ActionRowContext
interface RootContext

@fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
open class BaseComponentBuilder {
    protected val components = mutableListOf<fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent>()

    protected fun <T : fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent> add(component: T) {
        components.add(component)
    }
}

@fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
class RootComponentBuilder : fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder(), fr.ftnl.tools.messageBuilder.core.dsl.components.RootContext,
                             fr.ftnl.tools.messageBuilder.core.dsl.components.ContainerContext {

    fun container(block: fr.ftnl.tools.messageBuilder.core.dsl.components.ContainerBuilder.() -> Unit) {
        val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.ContainerBuilder()
        builder.block()
        _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder.add(builder.build())
    }

    fun text(block: fr.ftnl.tools.messageBuilder.core.dsl.components.TextBuilder.() -> Unit) {
        val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.TextBuilder()
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

    // Overload for section with DSL for accessory
    fun section(accessoryBlock: fr.ftnl.tools.messageBuilder.core.dsl.components.SectionAccessoryBuilder.() -> Unit, block: fr.ftnl.tools.messageBuilder.core.dsl.components.SectionBuilder.() -> Unit) {
        val accessoryBuilder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.SectionAccessoryBuilder()
        accessoryBuilder.accessoryBlock()
        val accessory = accessoryBuilder.build() ?: throw IllegalStateException("Section accessory must be defined")
        val builder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.SectionBuilder(accessory)
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

    fun label(label: String, component: fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent, description: String? = null) {
        _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder.add(
            _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.layout.Label(
                label = label,
                component = component,
                description = description
            )
        )
    }

    // Overload for label with DSL for component
    fun label(label: String, description: String? = null, componentBlock: fr.ftnl.tools.messageBuilder.core.dsl.components.LabelComponentBuilder.() -> Unit) {
        val componentBuilder = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.LabelComponentBuilder()
        componentBuilder.componentBlock()
        val component = componentBuilder.build() ?: throw IllegalStateException("Label component must be defined")
        _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder.add(
            _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.layout.Label(
                label = label,
                component = component,
                description = description
            )
        )
    }

    fun build(): List<fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent> = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder.components
}
