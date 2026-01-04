package fr.ftnl.tools.messageBuilder.core.serializers

import kotlinx.serialization.json.Json

internal val JsonSerializer = Json {
    ignoreUnknownKeys = true
    isLenient = true
    encodeDefaults = true
    explicitNulls = false
    prettyPrint = false
}