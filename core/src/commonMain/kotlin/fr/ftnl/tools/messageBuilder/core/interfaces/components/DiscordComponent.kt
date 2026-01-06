@file:JsExport @file:OptIn(ExperimentalJsExport::class)

package fr.ftnl.tools.messageBuilder.core.interfaces.components

import fr.ftnl.tools.messageBuilder.core.serializers.components.ComponentSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

/**
 * Interface de base pour tous les composants.
 */
@Serializable(with = ComponentSerializer::class)
interface DiscordComponent {
    val type: Int
    val id: Int?

    fun toJsonString(pretty: Boolean = false): String {
        val jsonConfig = Json {
            encodeDefaults = true
            ignoreUnknownKeys = true
            prettyPrint = pretty
        }
        
        return jsonConfig.encodeToString(this)
    }
    

}

interface ActionRowChildComponent   : DiscordComponent
interface ContainerChildComponent   : DiscordComponent
interface SectionChildComponent     : DiscordComponent
