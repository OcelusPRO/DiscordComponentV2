package fr.ftnl.tools.messageBuilder.core.serializers.components


import fr.ftnl.tools.messageBuilder.dto.components.content.*
import fr.ftnl.tools.messageBuilder.dto.components.interactive.*
import fr.ftnl.tools.messageBuilder.dto.components.layout.*
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive


/**
 * Sérialiseur polymorphique personnalisé pour choisir la bonne classe
 * en fonction du champ "type" (entier) du JSON Discord.
 */
internal object ComponentSerializer : JsonContentPolymorphicSerializer<fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent>(_root_ide_package_.fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent> {
        val type = element.jsonObject["type"]?.jsonPrimitive?.intOrNull
        return when (type) {
            1 -> _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.layout.ActionRow.serializer()
            2 -> _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.interactive.Button.serializer()
            3 -> _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.interactive.StringSelect.serializer()
            4 -> _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.interactive.TextInput.serializer()
            5 -> _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.interactive.UserSelect.serializer()
            6 -> _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.interactive.RoleSelect.serializer()
            7 -> _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.interactive.MentionableSelect.serializer()
            8 -> _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.interactive.ChannelSelect.serializer()
            9 -> _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.layout.Section.serializer()
            10 -> _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.content.TextDisplay.serializer()
            11 -> _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.content.Thumbnail.serializer()
            12 -> _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.content.MediaGallery.serializer()
            13 -> _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.content.FileComponent.serializer()
            14 -> _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.layout.Separator.serializer()
            17 -> _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.layout.Container.serializer()
            18 -> _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.layout.Label.serializer()
            19 -> _root_ide_package_.fr.ftnl.tools.messageBuilder.core.dto.components.interactive.FileUpload.serializer()
            else -> _root_ide_package_.fr.ftnl.tools.messageBuilder.core.serializers.components.UnknownComponent.serializer() // Pour gérer les nouveautés futures sans crash
        }
    }
}



// Fallback pour les types inconnus
@Serializable
data class UnknownComponent(
    override val type: Int,
    override val id: Int? = null
) : fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent

