package fr.ftnl.tools.messageBuilder.core.interfaces.components

import fr.ftnl.tools.messageBuilder.core.serializers.components.ComponentSerializer
import kotlinx.serialization.Serializable

/**
 * Interface de base pour tous les composants.
 */
@Serializable(with = ComponentSerializer::class)
interface DiscordComponent {
    val type: Int
    val id: Int?
}

interface ActionRowChildComponent   : DiscordComponent
interface ContainerChildComponent   : DiscordComponent
interface SectionChildComponent     : DiscordComponent
