@file:JsExport @file:OptIn(ExperimentalJsExport::class)

package fr.ftnl.tools.messageBuilder.core.interfaces.components

import fr.ftnl.tools.messageBuilder.core.serializers.components.ActionRowChildListSerializer
import fr.ftnl.tools.messageBuilder.core.serializers.components.ActionRowChildSerializer
import fr.ftnl.tools.messageBuilder.core.serializers.components.ComponentListSerializer
import fr.ftnl.tools.messageBuilder.core.serializers.components.ComponentSerializer
import fr.ftnl.tools.messageBuilder.core.serializers.components.ContainerChildListSerializer
import fr.ftnl.tools.messageBuilder.core.serializers.components.ContainerChildSerializer
import fr.ftnl.tools.messageBuilder.core.serializers.components.LabelChildSerializer
import fr.ftnl.tools.messageBuilder.core.serializers.components.MessageCompatibleListSerializer
import fr.ftnl.tools.messageBuilder.core.serializers.components.MessageCompatibleSerializer
import fr.ftnl.tools.messageBuilder.core.serializers.components.ModalCompatibleListSerializer
import fr.ftnl.tools.messageBuilder.core.serializers.components.ModalCompatibleSerializer
import fr.ftnl.tools.messageBuilder.core.serializers.components.SectionAccessorySerializer
import fr.ftnl.tools.messageBuilder.core.serializers.components.SectionChildListSerializer
import fr.ftnl.tools.messageBuilder.core.serializers.components.SectionChildSerializer
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.jvm.JvmInline

/**
 * Interface de base pour tous les composants.
 */
@Serializable(with = ComponentSerializer::class)
interface DiscordComponent {
    @EncodeDefault val type: Int
    @EncodeDefault val id: Int?

    fun toJsonString(pretty: Boolean = false): String {
        val jsonConfig = Json {
            encodeDefaults = true
            ignoreUnknownKeys = true
            prettyPrint = pretty
        }
        
        return jsonConfig.encodeToString(this)
    }
    

}

@Serializable(with = ActionRowChildSerializer::class)
interface ActionRowChildComponent       : DiscordComponent
@Serializable(with = ContainerChildSerializer::class)
interface ContainerChildComponent       : DiscordComponent
@Serializable(with = SectionChildSerializer::class)
interface SectionChildComponent         : DiscordComponent
@Serializable(with = SectionAccessorySerializer::class)
interface SectionAccessoryComponent     : DiscordComponent

@Serializable(with = MessageCompatibleSerializer::class)
interface MessageCompatibleComponent    : DiscordComponent
@Serializable(with = ModalCompatibleSerializer::class)
interface ModalCompatibleComponent      : DiscordComponent
@Serializable(with = LabelChildSerializer::class)
interface LabelChildComponent           : ModalCompatibleComponent

@JvmInline
@Serializable
value class ComponentList(
    @Serializable(with = ComponentListSerializer::class) val elements: List<DiscordComponent> = emptyList()
)
@JvmInline
@Serializable
value class ActionRowChildList(
    @Serializable(with = ActionRowChildListSerializer::class) val elements: List<ActionRowChildComponent> = emptyList()
)
@JvmInline
@Serializable
value class MessageCompatibleList(
    @Serializable(with = MessageCompatibleListSerializer::class) val elements: List<MessageCompatibleComponent> = emptyList()
)
@JvmInline
@Serializable
value class ModalCompatibleList(
    @Serializable(with = ModalCompatibleListSerializer::class) val elements: List<ModalCompatibleComponent> = emptyList()
)
@JvmInline
@Serializable
value class ContainerChildList(
    @Serializable(with = ContainerChildListSerializer::class) val elements: List<ContainerChildComponent> = emptyList()
)
@JvmInline
@Serializable
value class SectionChildList(
    @Serializable(with = SectionChildListSerializer::class) val elements: List<SectionChildComponent> = emptyList()
)
