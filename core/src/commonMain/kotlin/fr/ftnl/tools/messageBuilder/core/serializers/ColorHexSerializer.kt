@file:JsExport @file:OptIn(ExperimentalJsExport::class)

package fr.ftnl.tools.messageBuilder.core.serializers

import kotlin.js.JsExport
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.js.ExperimentalJsExport

internal fun formatHexColor(value: Int): String {
    val colorValue = 0xFFFFFF and value
    var hexString = colorValue.toString(16)
    while (hexString.length < 6) { hexString = "0$hexString" }
    return "#${hexString.uppercase()}"
}

internal object ColorHexSerializer : KSerializer<Int?> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("ColorHex", PrimitiveKind.STRING)
    
    override fun deserialize(decoder: Decoder): Int? {
        val string = decoder.decodeString()
        if (string.startsWith("#")) return string.substring(1).toIntOrNull(16)
        return string.toIntOrNull(16)
    }
    
    @OptIn(ExperimentalSerializationApi::class)
    override fun serialize(encoder: Encoder, value: Int?) {
        if (value == null) encoder.encodeNull()
        else {
            val formattedString = formatHexColor(value)
            encoder.encodeString(formattedString)
        }
    }
}