@file:JsExport @file:OptIn(ExperimentalJsExport::class)

package fr.ftnl.tools.messageBuilder.core.serializers.components

import kotlin.js.JsExport
import fr.ftnl.tools.messageBuilder.core.dto.components.content.*
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.*
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.*
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlin.js.ExperimentalJsExport


/**
 * Sérialiseur polymorphique personnalisé pour choisir la bonne classe
 * en fonction du champ "type" (entier) du JSON Discord.
 */
internal object ComponentSerializer : JsonContentPolymorphicSerializer<DiscordComponent>(DiscordComponent::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<DiscordComponent> {
        val type = element.jsonObject["type"]?.jsonPrimitive?.intOrNull
        return when (type) {
            1 -> ActionRow.serializer()
            2 -> Button.serializer()
            3 -> StringSelect.serializer()
            4 -> TextInput.serializer()
            5 -> UserSelect.serializer()
            6 -> RoleSelect.serializer()
            7 -> MentionableSelect.serializer()
            8 -> ChannelSelect.serializer()
            9 -> Section.serializer()
            10 -> TextDisplay.serializer()
            11 -> Thumbnail.serializer()
            12 -> MediaGallery.serializer()
            13 -> FileComponent.serializer()
            14 -> Separator.serializer()
            17 -> Container.serializer()
            18 -> Label.serializer()
            19 -> FileUpload.serializer()
            else -> UnknownComponent.serializer() // Pour gérer les nouveautés futures sans crash
        }
    }
}



// Fallback pour les types inconnus
@Serializable
data class UnknownComponent(
    override val type: Int,
    override val id: Int? = null
) : DiscordComponent

