package fr.ftnl.tools.messageBuilder.core.dsl.components.builders.layout

import fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.content.TextDisplayBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.interactive.ButtonBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.dsl.MessageComponentBuilder
import fr.ftnl.tools.messageBuilder.core.dto.components.content.Thumbnail
import fr.ftnl.tools.messageBuilder.core.dto.components.content.UnfurledMediaItem
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.ButtonStyles
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.Section
import fr.ftnl.tools.messageBuilder.core.interfaces.components.SectionAccessoryComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.SectionChildComponent

@ComponentDsl
class SectionBuilder : MessageComponentBuilder {
    
    private val sectionComponents = mutableListOf<SectionChildComponent>()
    private var sectionAccessory: SectionAccessoryComponent? = null
    
    fun text(block: TextDisplayBuilder.() -> Unit) {
        val builder = TextDisplayBuilder()
        builder.block()
        if (sectionComponents.size >= 3) throw IllegalStateException("Section can have a maximum of 3 child components")
        sectionComponents.add(builder.build())
    }
    
    fun accessory(block: SectionAccessoryBuilder.() -> Unit) {
        val builder = SectionAccessoryBuilder()
        builder.block()
        sectionAccessory = builder.build()
    }

    
    override fun build(): Section {
        require(sectionComponents.isNotEmpty()) { "Section must have at least one child component" }
        require(sectionComponents.size <= 3) { "Section can have a maximum of 3 child components" }
        require(sectionAccessory != null) { "Section must have an accessory" }
        
        return Section(
            components = sectionComponents,
            accessory = sectionAccessory!!
        )
    }
}


@ComponentDsl
class SectionAccessoryBuilder {
    private var component: SectionAccessoryComponent? = null
    
    fun button(customId: String, style: ButtonStyles, block: ButtonBuilder.() -> Unit) {
        val builder = ButtonBuilder(customId, style)
        builder.block()
        component = builder.build()
    }
    
    fun thumbnail(media: UnfurledMediaItem, description: String? = null, spoiler: Boolean = false) {
        component = Thumbnail(media = media, description = description, spoiler = spoiler)
    }
    
    fun build(): SectionAccessoryComponent{
        requireNotNull(component) { "Section accessory must have a child component" }
        return component!!
    }
}