package fr.ftnl.tools.messageBuilder.core.serializers.components

import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.ButtonStyles
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

internal object ButtonStyleSerializer : KSerializer<ButtonStyles> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("ButtonStyles", PrimitiveKind.INT)
    
    override fun deserialize(decoder: Decoder): ButtonStyles {
        val int = decoder.decodeInt()
        return ButtonStyles.values().find { it.value == int } ?: throw IllegalArgumentException("Invalid ButtonStyle value: $int")
    }
    
    @OptIn(ExperimentalSerializationApi::class)
    override fun serialize(encoder: Encoder, style: ButtonStyles) {
        encoder.encodeInt(style.value)
    }
}