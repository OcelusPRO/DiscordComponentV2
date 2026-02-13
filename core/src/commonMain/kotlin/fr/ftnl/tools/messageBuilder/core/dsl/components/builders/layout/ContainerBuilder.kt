package fr.ftnl.tools.messageBuilder.core.dsl.components.builders.layout

import fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.content.MediaGalleryBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.content.TextDisplayBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.dsl.MessageComponentBuilder
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.Container
import fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent


@ComponentDsl
class ContainerBuilder : MessageComponentBuilder {
    private val containerComponents = mutableListOf<ContainerChildComponent>()
    
    var color: Int? = null
    var spoiler: Boolean = false
    
    fun text(block: TextDisplayBuilder.() -> Unit) {
        val builder = TextDisplayBuilder()
        builder.block()
        containerComponents.add(builder.build())
    }
    
    fun separator(block: SeparatorBuilder.() -> Unit) {
        val builder = SeparatorBuilder()
        builder.block()
        containerComponents.add(builder.build())
    }

    
    fun mediaGallery(block: MediaGalleryBuilder.() -> Unit) {
        val builder = MediaGalleryBuilder()
        builder.block()
        containerComponents.add(builder.build())
    }
    
    fun actionRow(block: ActionRowBuilder.() -> Unit) {
        val builder = ActionRowBuilder()
        builder.block()
        containerComponents.add(builder.build())
    }
    
    fun section(block: SectionBuilder.() -> Unit) {
        val builder = SectionBuilder()
        builder.block()
        containerComponents.add(builder.build())
    }
    
    override fun build(): Container {
        return Container(components = containerComponents, accentColor = color, spoiler = spoiler)
    }
}