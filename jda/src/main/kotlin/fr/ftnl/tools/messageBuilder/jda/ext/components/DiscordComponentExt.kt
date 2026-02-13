package fr.ftnl.tools.messageBuilder.jda.ext.components

import fr.ftnl.tools.messageBuilder.core.dto.components.content.*
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.*
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.*
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.MessageCompatibleComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.ModalCompatibleComponent
import fr.ftnl.tools.messageBuilder.jda.ext.components.content.mediaGallery.toJda
import fr.ftnl.tools.messageBuilder.jda.ext.components.content.textDisplay.toJda
import fr.ftnl.tools.messageBuilder.jda.ext.components.interactive.button.toJda
import fr.ftnl.tools.messageBuilder.jda.ext.components.interactive.entitySelect.toJda
import fr.ftnl.tools.messageBuilder.jda.ext.components.interactive.stringSelect.toJda
import fr.ftnl.tools.messageBuilder.jda.ext.components.interactive.textInput.toJda
import fr.ftnl.tools.messageBuilder.jda.ext.components.layout.actionRow.toJda
import fr.ftnl.tools.messageBuilder.jda.ext.components.layout.container.toJda
import fr.ftnl.tools.messageBuilder.jda.ext.components.layout.section.toJda
import fr.ftnl.tools.messageBuilder.jda.ext.components.layout.separator.toJda

import net.dv8tion.jda.api.components.Component
import net.dv8tion.jda.api.components.MessageTopLevelComponent
import net.dv8tion.jda.api.components.ModalTopLevelComponent

@Deprecated("use toJdaMessageComponents or toJdaModalComponents instead")
fun <T: Component> List<DiscordComponent>.toJdaComponents(): List<T> = mapNotNull { it.toJdaComponents() as? T }

@Deprecated("use toJdaMessageComponents or toJdaModalComponents instead")
fun DiscordComponent.toJdaComponents(): Component {
    return when (this) {
        // --- Interactive Components ---
        is Button               -> this.toJda()
        is StringSelect         -> this.toJda()
        is UserSelect           -> this.toJda()
        is RoleSelect           -> this.toJda()
        is MentionableSelect    -> this.toJda()
        is ChannelSelect        -> this.toJda()
        is TextInput            -> this.toJda()
        
        // --- Layout Components ---
        is ActionRow            -> this.toJda()
        is Container            -> this.toJda()
        is Section              -> this.toJda()
        is TextDisplay          -> this.toJda()
        is MediaGallery         -> this.toJda()
        is Separator            -> this.toJda()
        
        
        else -> throw IllegalArgumentException("Conversion JDA non supportée pour le type: ${this::class.simpleName}")
    }
}



fun <T: MessageTopLevelComponent> List<MessageCompatibleComponent>.toJdaMessageComponents(): List<T> = mapNotNull { it.toJdaMessageComponents() as? T }
fun MessageCompatibleComponent.toJdaMessageComponents(): MessageTopLevelComponent {
    return convertToJdaComponent() as MessageTopLevelComponent
}

fun <T: ModalTopLevelComponent> List<ModalCompatibleComponent>.toJdaModalComponents(): List<T> = mapNotNull { it.toJdaModalComponents() as? T }
fun ModalCompatibleComponent.toJdaModalComponents(): ModalTopLevelComponent {
    return convertToJdaComponent() as ModalTopLevelComponent
}

private fun DiscordComponent.convertToJdaComponent() = when (this) {
    // --- Content Components ---
    is TextDisplay          -> this.toJda()
    is MediaGallery         -> this.toJda()
    // FileComponent
    // Thumbnail component
    
    // --- Interactive Components ---
    is Button               -> this.toJda()
    // checkbox
    // checkboxGroup
    is UserSelect           -> this.toJda()
    is RoleSelect           -> this.toJda()
    is ChannelSelect        -> this.toJda()
    is MentionableSelect    -> this.toJda()
    // fileUpload
    // radioGroup
    is StringSelect         -> this.toJda()
    is TextInput            -> this.toJda()
    
    // --- Layout Components ---
    is ActionRow            -> this.toJda()
    is Container            -> this.toJda()
    // Label
    is Section              -> this.toJda()
    is Separator            -> this.toJda()
    
    else -> throw IllegalArgumentException("Conversion JDA non supportée pour le type: ${this::class.simpleName}")
}