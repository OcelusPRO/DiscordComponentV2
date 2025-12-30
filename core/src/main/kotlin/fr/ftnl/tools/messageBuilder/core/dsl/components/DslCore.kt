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
fun discordComponent(block: RootComponentBuilder.() -> Unit): List<DiscordComponent> {
    val builder = RootComponentBuilder()
    builder.block()
    return builder.build()
}

// Interfaces to mark which components can be added to which parent
interface ContainerContext
interface SectionContext
interface ActionRowContext
interface RootContext

@ComponentDsl
open class BaseComponentBuilder {
    protected val components = mutableListOf<DiscordComponent>()

    protected fun <T : DiscordComponent> add(component: T) {
        components.add(component)
    }
}

@ComponentDsl
class RootComponentBuilder : BaseComponentBuilder(), RootContext, ContainerContext {

    fun container(block: ContainerBuilder.() -> Unit) {
        val builder = ContainerBuilder()
        builder.block()
        add(builder.build())
    }

    fun text(block: TextBuilder.() -> Unit) {
        val builder = TextBuilder()
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

    // Overload for section with DSL for accessory
    fun section(accessoryBlock: SectionAccessoryBuilder.() -> Unit, block: SectionBuilder.() -> Unit) {
        val accessoryBuilder = SectionAccessoryBuilder()
        accessoryBuilder.accessoryBlock()
        val accessory = accessoryBuilder.build() ?: throw IllegalStateException("Section accessory must be defined")
        val builder = SectionBuilder(accessory)
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

    fun label(label: String, component: DiscordComponent, description: String? = null) {
        add(
            Label(
                label = label,
                component = component,
                description = description
            )
        )
    }

    // Overload for label with DSL for component
    fun label(label: String, description: String? = null, componentBlock: LabelComponentBuilder.() -> Unit) {
        val componentBuilder = LabelComponentBuilder()
        componentBuilder.componentBlock()
        val component = componentBuilder.build() ?: throw IllegalStateException("Label component must be defined")
        add(
            Label(
                label = label,
                component = component,
                description = description
            )
        )
    }

    fun build(): List<DiscordComponent> = components
}
