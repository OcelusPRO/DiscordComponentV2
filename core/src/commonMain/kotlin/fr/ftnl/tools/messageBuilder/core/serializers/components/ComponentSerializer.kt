package fr.ftnl.tools.messageBuilder.core.serializers.components

import fr.ftnl.tools.messageBuilder.core.dto.components.content.FileComponent
import fr.ftnl.tools.messageBuilder.core.dto.components.content.MediaGallery
import fr.ftnl.tools.messageBuilder.core.dto.components.content.TextDisplay
import fr.ftnl.tools.messageBuilder.core.dto.components.content.Thumbnail
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.*
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.*
import fr.ftnl.tools.messageBuilder.core.interfaces.components.ActionRowChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.ContainerChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.LabelChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.MessageCompatibleComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.ModalCompatibleComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.SectionAccessoryComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.SectionChildComponent
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive


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
            // 15 dont exist
            // 16 dont exist
            17 -> Container.serializer()
            18 -> Label.serializer()
            19 -> FileUpload.serializer()
            // 20 dont exist
            21 -> RadioGroup.serializer()
            22 -> CheckboxGroup.serializer()
            23 -> Checkbox.serializer()
            
            else -> UnknownComponent.serializer()
        }
    }
}

internal object ActionRowChildSerializer : KSerializer<ActionRowChildComponent> {
    override val descriptor: SerialDescriptor = ComponentSerializer.descriptor
    override fun serialize(encoder: Encoder, value: ActionRowChildComponent) = ComponentSerializer.serialize(encoder, value)
    override fun deserialize(decoder: Decoder): ActionRowChildComponent {
        val component = ComponentSerializer.deserialize(decoder)
        if (component is ActionRowChildComponent) return component // Smart cast !
        throw SerializationException("Type inattendu : attendu ActionRowChildComponent, reçu ${component::class.simpleName}")
    }
}
internal object ContainerChildSerializer : KSerializer<ContainerChildComponent> {
    override val descriptor: SerialDescriptor = ComponentSerializer.descriptor
    override fun serialize(encoder: Encoder, value: ContainerChildComponent) = ComponentSerializer.serialize(encoder, value)
    override fun deserialize(decoder: Decoder): ContainerChildComponent {
        val component = ComponentSerializer.deserialize(decoder)
        if (component is ContainerChildComponent) return component
        throw SerializationException("Type inattendu : attendu ContainerChildComponent, reçu ${component::class.simpleName}")
    }
}
internal object SectionChildSerializer : KSerializer<SectionChildComponent> {
    override val descriptor: SerialDescriptor = ComponentSerializer.descriptor
    override fun serialize(encoder: Encoder, value: SectionChildComponent) = ComponentSerializer.serialize(encoder, value)
    override fun deserialize(decoder: Decoder): SectionChildComponent {
        val component = ComponentSerializer.deserialize(decoder)
        if (component is SectionChildComponent) return component
        throw SerializationException("Type inattendu : attendu SectionChildComponent, reçu ${component::class.simpleName}")
    }
}
internal object SectionAccessorySerializer : KSerializer<SectionAccessoryComponent> {
    override val descriptor: SerialDescriptor = ComponentSerializer.descriptor
    override fun serialize(encoder: Encoder, value: SectionAccessoryComponent) = ComponentSerializer.serialize(encoder, value)
    override fun deserialize(decoder: Decoder): SectionAccessoryComponent {
        val component = ComponentSerializer.deserialize(decoder)
        if (component is SectionAccessoryComponent) return component
        throw SerializationException("Type inattendu : attendu SectionAccessoryComponent, reçu ${component::class.simpleName}")
    }
}
internal object MessageCompatibleSerializer : KSerializer<MessageCompatibleComponent> {
    override val descriptor: SerialDescriptor = ComponentSerializer.descriptor
    override fun serialize(encoder: Encoder, value: MessageCompatibleComponent) = ComponentSerializer.serialize(encoder, value)
    override fun deserialize(decoder: Decoder): MessageCompatibleComponent {
        val component = ComponentSerializer.deserialize(decoder)
        if (component is MessageCompatibleComponent) return component
        throw SerializationException("Type inattendu : attendu MessageCompatibleComponent, reçu ${component::class.simpleName}")
    }
}
internal object ModalCompatibleSerializer : KSerializer<ModalCompatibleComponent> {
    override val descriptor: SerialDescriptor = ComponentSerializer.descriptor
    override fun serialize(encoder: Encoder, value: ModalCompatibleComponent) = ComponentSerializer.serialize(encoder, value)
    override fun deserialize(decoder: Decoder): ModalCompatibleComponent {
        val component = ComponentSerializer.deserialize(decoder)
        if (component is ModalCompatibleComponent) return component
        throw SerializationException("Type inattendu : attendu ModalCompatibleComponent, reçu ${component::class.simpleName}")
    }
}
internal object LabelChildSerializer : KSerializer<LabelChildComponent> {
    override val descriptor: SerialDescriptor = ComponentSerializer.descriptor
    override fun serialize(encoder: Encoder, value: LabelChildComponent) = ComponentSerializer.serialize(encoder, value)
    override fun deserialize(decoder: Decoder): LabelChildComponent {
        val component = ComponentSerializer.deserialize(decoder)
        if (component is LabelChildComponent) return component
        throw SerializationException("Type inattendu : attendu LabelChildComponent, reçu ${component::class.simpleName}")
    }
}


internal object ComponentListSerializer : KSerializer<List<DiscordComponent>> by ListSerializer(ComponentSerializer)
internal object ActionRowChildListSerializer : KSerializer<List<ActionRowChildComponent>> by ListSerializer(ActionRowChildSerializer)
internal object ContainerChildListSerializer : KSerializer<List<ContainerChildComponent>> by ListSerializer(ContainerChildSerializer)
internal object SectionChildListSerializer : KSerializer<List<SectionChildComponent>> by ListSerializer(SectionChildSerializer)
internal object MessageCompatibleListSerializer : KSerializer<List<MessageCompatibleComponent>> by ListSerializer(MessageCompatibleSerializer)
internal object ModalCompatibleListSerializer : KSerializer<List<ModalCompatibleComponent>> by ListSerializer(ModalCompatibleSerializer)


// Fallback pour les types inconnus
@Serializable
data class UnknownComponent(
    override val type: Int,
    override val id: Int? = null
) : DiscordComponent,
    ActionRowChildComponent,
    ContainerChildComponent,
    SectionChildComponent,
    SectionAccessoryComponent,
    MessageCompatibleComponent,
    ModalCompatibleComponent,
    LabelChildComponent

